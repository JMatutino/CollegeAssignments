import com.jhlabs.image.InvertFilter;
import com.jhlabs.image.OilFilter;
import com.jhlabs.image.SmearFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class ConcurrentImageProcessorTaskParallel {
    static JProgressBar pb;
    static String errorMsg = "Not alive anymore";

    static BufferedImage output;
    static BufferedImageOp filter;
    static String newFile;
    static String directoryName;
    static String filterUse;
    static int numFiles;
    static volatile int processedSoFar = 0;
    static int picCount = 0;

    static Semaphore freeRead = new Semaphore(8);
    static Semaphore occupiedRead = new Semaphore(0);

    static Semaphore freeProcess;
    static Semaphore occupiedProcess = new Semaphore(0);


    static LinkedList<BufferedImage> inImages = new LinkedList<BufferedImage>();
    static LinkedList<BufferedImage> outImages = new LinkedList<BufferedImage>();
    static LinkedList<String> names = new LinkedList<String>();
    static LinkedList<Process> processThreads = new LinkedList<Process>();

    static List<File> inputfiles;


    private static void saveImage(BufferedImage image, String filename, String path){
        try {
            ImageIO.write(image, "jpg", new File(path + "/" + filename));
            System.out.print("w");
        } catch (IOException e) {
            System.out.println("Cannot write file "+filename);
            System.exit(1);
        }
    }

    private static void progressUpdate(final int percent) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                pb.setValue(percent);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        int argumentNum = 0;
        int threadNum = 1;

        pb = new JProgressBar(0,100);
        pb.setValue(0);

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        BufferedImage input = null;

        newFile = "";

        long overallStart = System.currentTimeMillis();

        try {
            if (args.length == 3) {
                threadNum = Integer.parseInt(args[argumentNum]);
                /*System.out.println(threadNum);*/
                argumentNum++;
            }
        } catch (NumberFormatException e) {
            System.out.print("Number of threads invalid");
            System.exit(1);
        }

        freeProcess = new Semaphore(threadNum);

        filterUse = args[argumentNum];
        argumentNum++;
        directoryName = args[argumentNum];

        panel.add(pb);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        pb.setStringPainted(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Path path = Paths.get(directoryName);

        inputfiles = new ArrayList<File>();

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, "image_*.jpg")) {
            for (Path f : ds) {
                inputfiles.add(f.toFile());
            }
            numFiles = inputfiles.size();
        } catch (IOException e) {
            System.err.println(e);
        }

        //Instantiate threads
        Read readthread = new Read();
        for(int i = 0; i < threadNum; i++) {
            /*System.out.println("Create process");*/
            processThreads.add(new Process());

        }
        Write writeThread = new Write();



        readthread.start();

        /*processThread.start();*/
        for(Process process : processThreads) {
            process.start();
        }

        writeThread.start();

        readthread.join();
        for(Process process : processThreads) {
            process.join();
        }
        writeThread.join();

        System.out.println();
        System.out.println("Overall time: " + ((System.currentTimeMillis()-overallStart)/1000.) + " sec.");

        Toolkit.getDefaultToolkit().beep();
    }

    public static class Read extends Thread {

        @Override
        public void run() {
            try {
                long readStart = System.currentTimeMillis();
                for (File f : inputfiles) {
                    freeRead.P();
                    inImages.push(ImageIO.read(f));
                    names.push(f.getName());
                    System.out.print("r");
                    occupiedRead.V();
                }
                long readEnd = System.currentTimeMillis();
                long readTotal = readEnd - readStart;
                System.out.println();
                System.out.println("Time Spent reading: " + (readTotal/1000.) + " sec.");
            } catch (java.io.IOException e) {
                System.out.println(errorMsg);
            }

        }
    }

    public static class Process extends Thread {

        @Override
        public void run() {
            long processStart;
            long processEnd;
            long processTotal=0;
            /*int numProcessed = 0;*/
            BufferedImageOp filter = null;

            if(filterUse.equalsIgnoreCase("oil1")){
                filter = new OilFilter();
                ((OilFilter)filter).setRange(1);
                newFile = "./oil1_image";
            }
            else if(filterUse.equalsIgnoreCase("oil6")) {
                filter = new OilFilter();
                ((OilFilter)filter).setRange(6);
                newFile = "./oil6_image";
            }
            else if(filterUse.equalsIgnoreCase("invert")) {
                filter = new InvertFilter();
                newFile = "./invert_image";
            }
            else if(filterUse.equalsIgnoreCase("smear")) {
                filter = new SmearFilter();
                ((SmearFilter)filter).setShape(0);
                newFile = "./smear_image";
            }
            else if(filterUse.equalsIgnoreCase("weird")) {
                filter = new WeirdFilter();
                newFile = "./weird_image";
            }
            else {
                System.out.println("Cannot recognize filter");
                System.exit(1);
            }

            while(processedSoFar < numFiles) {
                occupiedRead.P();
                freeProcess.P();
                processStart = System.currentTimeMillis();
                BufferedImage input = inImages.pop();
                BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
                processedSoFar++;
                filter.filter(input, output);
                outImages.push(output);
                System.out.print("p");
                processEnd = System.currentTimeMillis();
                processTotal = processTotal + (processEnd - processStart);
                freeRead.V();
                occupiedProcess.V();
            }
            System.out.println();
            System.out.println("Time Spent processing: " + (processTotal/1000.) + " sec.");
        }
    }


    public static class Write extends Thread {

        @Override
        public void run() {
            long writeStart;
            long writeEnd;
            long writeTotal=0;
            int numProcessed = 0;
            while(numProcessed < numFiles) {
                occupiedProcess.P();
                writeStart = System.currentTimeMillis();
                saveImage(outImages.pop(),filterUse + "_" + names.pop() , directoryName);
                numProcessed++;
                writeEnd = System.currentTimeMillis();
                freeProcess.V();
                writeTotal = writeTotal + (writeEnd - writeStart);
                picCount++;
                progressUpdate((int)((float)picCount / (float)numFiles * 100));
            }
            System.out.println();
            System.out.println("Time Spent writing: " + (writeTotal/1000.) + " sec.");
        }
    }
}
