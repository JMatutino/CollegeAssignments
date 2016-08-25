import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class WeirdFilterExample {

	private static void saveImage(BufferedImage image, String filename){
		try {
			ImageIO.write(image, "jpg", new File(filename));
		} catch (IOException e) {
			System.out.println("Cannot write file "+filename);
			System.exit(1);
		}
	}

	public static void main(String args[]) {

		BufferedImage input=null;
		BufferedImage output;
		BufferedImageOp filter;

		try {
			input = ImageIO.read(new File("./image.jpg"));
		} catch (IOException e) {
			System.out.println("Cannot read file ./image.jpg");
			System.exit(1);
		}

		output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());  

		// Apply Weird filter
		filter = new WeirdFilter();
		filter.filter(input,output);
		saveImage(output, "./weird_image.jpg");

	}
}

