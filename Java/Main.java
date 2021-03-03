import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {

		String imageFileName;
		String printFileName;
		String arrayFileName;

		if (args.length != 3) {
			System.out.println("Invalid Number of Arguments.");
			System.out.println("Enter names of image file, file for printing, and file for array content.");
			System.exit(0);
		}

		try {
			imageFileName = args[0];
			printFileName = args[1];
			arrayFileName = args[2];

			Scanner image = new Scanner(new File(imageFileName));
			FileWriter print = new FileWriter(new File(printFileName));
			FileWriter array = new FileWriter(new File(arrayFileName));

			ImageProcessing processing = new ImageProcessing(image);
			processing.loadImage(image);
			
			HoughTransform transform = new HoughTransform(processing.getImageRows(), processing.getImageCols());
			transform.buildHoughSpace(processing.imageArray);
			transform.prettyPrint(transform.houghArray, print);
			transform.determineMinAndMax(transform.houghArray);
			transform.arrayToFile(transform.houghArray, array);

			image.close();
			print.close();
			array.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
