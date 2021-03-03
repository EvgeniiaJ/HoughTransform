import java.util.Scanner;

public class ImageProcessing {
	
	private int imageRows;
	private int imageCols;
	private int imageMin;
	private int imageMax;
	
	public int[][] imageArray;
	
	public ImageProcessing(Scanner input) {

		if (input.hasNext()) {
			imageRows = input.nextInt();
		}
		if (input.hasNext()) {
			imageCols = input.nextInt();
		}
		if (input.hasNext()) {
			imageMax = input.nextInt();
		}
		if (input.hasNext()) {
			imageMin = input.nextInt();
		}

		imageArray = new int[imageRows][imageCols];
	}
	
	public void loadImage(Scanner input) {
		
		while (input.hasNext()) {
			for (int i = 0; i < imageRows; i++) {
				for (int j = 0; j < imageCols; j++) {
					imageArray[i][j] = input.nextInt();
				}
			}
		}
		
	}

	public int getImageRows() {
		return imageRows;
	}

	public void setImageRows(int imageRows) {
		this.imageRows = imageRows;
	}

	public int getImageCols() {
		return imageCols;
	}

	public void setImageCols(int imageCols) {
		this.imageCols = imageCols;
	}

	public int getImageMin() {
		return imageMin;
	}

	public void setImageMin(int imageMin) {
		this.imageMin = imageMin;
	}

	public int getImageMax() {
		return imageMax;
	}

	public void setImageMax(int imageMax) {
		this.imageMax = imageMax;
	}

}
