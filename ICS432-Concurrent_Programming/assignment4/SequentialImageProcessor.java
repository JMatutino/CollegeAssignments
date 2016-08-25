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
import java.util.ArrayList;
import java.util.List;

public class SequentialImageProcessor {
    static JProgressBar pb;

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
        pb = new JProgressBar(0,100);
        pb.setValue(0);
        int picCount = 0;
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        BufferedImage input=null;
        BufferedImage output;
        BufferedImageOp filter;
        String newFile = "";

        long overallStart = System.currentTimeMillis();

        long readStart;
        long readEnd;
        long readTotal = 0;

        long processStart;
        long processEnd;
        long processTotal = 0;

        long writeStart;
        long writeEnd;
        long writeTotal = 0;

        String filterUse = args[0];
        String directoryName = args[1];

        panel.add(pb);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        pb.setStringPainted(true);

        Path path = Paths.get(directoryName);
        List<File> inputfiles = new ArrayList<File>();

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, "image_*.jpg")) {
            for (Path f : ds) {
                inputfiles.add(f.toFile());
            }
        } catch (IOException e) {
            System.err.println(e);
        }

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
        else {
            throw new Exception("Cannot recognize filter");
        }

        int fileCount =1;

        for(File f: inputfiles) {
            readStart = System.currentTimeMillis();
            input = ImageIO.read(f);
            readEnd = System.currentTimeMillis();
            readTotal = readTotal + (readEnd - readStart);
            System.out.print("r");
            output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
            processStart = System.currentTimeMillis();
            filter.filter(input,output);
            processEnd = System.currentTimeMillis();
            processTotal = processTotal + (processEnd - processStart);
            System.out.print("p");
            writeStart = System.currentTimeMillis();
            saveImage(output, newFile + fileCount + ".jpg", directoryName);
            picCount++;
            progressUpdate((int)((float)picCount / (float)inputfiles.size() * 100));
            writeEnd = System.currentTimeMillis();
            writeTotal = writeTotal + (writeEnd - writeStart);
            fileCount++;
        }

        System.out.println();
        System.out.println("Time Spent reading: " + (readTotal/1000.) + " sec.");
        System.out.println("Time Spent processing: " + (processTotal/1000.) + " sec.");
        System.out.println("Time Spent writing: " + (writeTotal/1000.) + " sec.");
        System.out.println("Overall time: " + ((System.currentTimeMillis()-overallStart)/1000.) + "sec.");

        Toolkit.getDefaultToolkit().beep();
    }
}
