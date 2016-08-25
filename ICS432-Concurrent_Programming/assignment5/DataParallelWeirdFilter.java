import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.util.LinkedList;


public class DataParallelWeirdFilter implements BufferedImageOp{
    private int threadNum;
    private LinkedList<FilterThread> threads = new LinkedList<FilterThread>();

    public DataParallelWeirdFilter(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public BufferedImage filter(BufferedImage inputImage, BufferedImage outputImage) {
        int partitionSize = inputImage.getHeight()/threadNum;

        for(int i = 0;i<threadNum-1; i++){
            threads.add(new FilterThread(i*partitionSize,(i+1)*partitionSize,inputImage,outputImage));
        }
        //Last partition including the remaining rows
        threads.add(new FilterThread((threadNum-1)*partitionSize,inputImage.getHeight()-1,inputImage,outputImage));

        for(FilterThread thread : threads) {
            thread.start();
        }

        try {
            for (FilterThread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
            System.exit(1);
        }

        return outputImage;
    }

    private static int processPixel(BufferedImage image, int x, int y) {
        /*System.out.println(rgb);
        int rgb = image.getRGB(x,y);
        byte[] bytes = RGB.intToBytes(rgb);*/

        byte[] bytes = {0,0,0};

        int neighborNum = 0;
        int neighborCount = 0;

        while (neighborNum < 8) {
                /*System.out.println("Started inside");*/
            byte[] neighborByte = new byte[3];
            try {
                switch (neighborNum) {
                    //TopLeft
                    case 0:
                        neighborByte = RGB.intToBytes(image.getRGB(x - 1, y - 1));
                        break;

                    //TopMid
                    case 1:
                        neighborByte = RGB.intToBytes(image.getRGB(x, y - 1));
                        break;

                    //TopRight
                    case 2:
                        neighborByte = RGB.intToBytes(image.getRGB(x + 1, y - 1));
                        break;

                    //MidRight
                    case 3:
                        neighborByte = RGB.intToBytes(image.getRGB(x + 1, y));
                        break;

                    //BotRight
                    case 4:
                        neighborByte = RGB.intToBytes(image.getRGB(x + 1, y + 1));
                        break;

                    //BotMid
                    case 5:
                        neighborByte = RGB.intToBytes(image.getRGB(x, y + 1));
                        break;

                    //BotLeft
                    case 6:
                        neighborByte = RGB.intToBytes(image.getRGB(x - 1, y + 1));
                        break;

                    //MidLeft
                    case 7:
                        neighborByte = RGB.intToBytes(image.getRGB(x - 1, y));
                        break;
                }
                neighborCount++;
                doPixelMath(bytes,neighborByte);

            } catch (ArrayIndexOutOfBoundsException e) {
                neighborCount--;
            }
            neighborNum++;
        }
        /*System.out.println(neighborCount);*/

        bytes[0] /= neighborCount;
        bytes[1] /= neighborCount;
        bytes[2] /= neighborCount;


        return RGB.bytesToInt(bytes);
    }

    private static byte[] doPixelMath(byte[] bytes, byte[] neighborByte) {
        // Red
        bytes[0] += Math.max(Math.exp(neighborByte[0]), 20) + 10 * Math.cos(neighborByte[0]);

        // Green
        bytes[1] += Math.min(Math.exp(neighborByte[1]), 50);

        // Blue
        bytes[2] += Math.min(Math.exp(neighborByte[2]), 20);

        return bytes;
    }

    @Override
    public Rectangle2D getBounds2D(BufferedImage bufferedImage) {
        return null;
    }

    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage bufferedImage, ColorModel colorModel) {
        return null;
    }

    @Override
    public Point2D getPoint2D(Point2D point2D, Point2D point2D1) {
        return null;
    }

    @Override
    public RenderingHints getRenderingHints() {
        return null;
    }

    public class FilterThread extends Thread {
        private int startY;
        private int endY;
        private BufferedImage inputImage;
        private BufferedImage outputImage;

        public FilterThread(int startY, int endY, BufferedImage inputImage, BufferedImage outputImage) {
            this.startY = startY;
            this.endY = endY;
            this.inputImage = inputImage;
            this.outputImage = outputImage;
        }
        public void run() {
            for(int y=startY; y<endY; y++) {
                for (int x=0; x<inputImage.getWidth();x++) {
                    outputImage.setRGB(x,y,processPixel(inputImage, x, y));
                }
            }

        }
    }

}
