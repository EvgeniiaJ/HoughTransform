import java.io.FileWriter;
import java.io.IOException;

public class HoughTransform {

	private int rowsNumber;
	private int colsNumber;
	private Coordinate2D point;
	private int angleInDegrees;
	private double angleInRadians;
	private int houghDistance;
	private int houghAngle;
	private int offset;
	private int houghMinValue;
	private int houghMaxValue;
	
	public int[][] houghArray;
	
	
	
	public HoughTransform(int rowsNumber, int colsNumber) {
		this.rowsNumber = rowsNumber;
		this.colsNumber = colsNumber;
		
		int diagonal = 2 * ((int) Math.sqrt(Math.pow(rowsNumber, 2) + Math.pow(colsNumber, 2)));
		houghDistance = diagonal;
		offset = houghDistance / 2;
		houghAngle = 180;
		houghArray = new int[houghDistance][houghAngle];
		
		for(int i = 0; i < houghDistance; i++) {
			for(int j = 0; j < houghAngle; j++) {
				houghArray[i][j] = 0;
			}
		}
	
	}
	
	public void buildHoughSpace(int[][] imageArray) {
		double distance = 0.00;
		int distanceInteger = 0;
		
		for(int i = 0; i < rowsNumber; i++) {
			for(int j = 0; j < colsNumber; j++) {
				if(imageArray[i][j] > 0) {
					point = new Coordinate2D(i, j);
					angleInDegrees = 0;
					
					while(angleInDegrees < 180) {
						angleInRadians = (angleInDegrees * Math.PI) / 180.00;
						distance = computeDistance(point, angleInRadians);
						distanceInteger = (int) (distance + offset);
						houghArray[distanceInteger][angleInDegrees]++;
						angleInDegrees++;
						
					}
				}
			}
		}
	}
	
	public double computeDistance(Coordinate2D point, double angle) {
		
		double xValue = (double)point.getXCoordinate();
		double yValue = (double)point.getYCoordinate();
		
		double tangent = Math.atan(yValue/xValue) * (-1) - (Math.PI / 2) + angle;
		double direct = Math.sqrt(Math.pow(xValue, 2) + Math.pow(yValue, 2));
		double distance = direct * Math.cos(tangent);
		
		return Math.abs(distance);
		
		
	}
	
	public void determineMinAndMax(int[][] houghArray) {
		houghMinValue = 9999;
		houghMaxValue = -1;
		
		for(int i = 0; i < houghDistance; i++) {
			for(int j = 0; j < houghAngle; j++) {
				
				if(houghArray[i][j] < houghMinValue) {
					houghMinValue = houghArray[i][j];
				}
				
				if(houghArray[i][j] > houghMaxValue) {
					houghMaxValue = houghArray[i][j];
				}
			}
		}
	}
	
	public void prettyPrint(int[][] houghAry, FileWriter output) {
		try {
			
			for (int i = 0; i < houghDistance; i++) {
				for (int j = 0; j < houghAngle; j++) {
					if (houghAry[i][j] > 0) {
						output.write("1");
					} else {
						output.write(" ");
					}
				}
				output.write("\n");
			}
			
			output.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void arrayToFile(int[][] houghAry, FileWriter output) {
		try {
			output.write(houghDistance + " " + houghAngle + " " + houghMinValue + " " + houghMaxValue + "\n");
			
			for (int i = 0; i < houghDistance; i++) {
				for (int j = 0; j < houghAngle; j++) {
					output.write(houghAry[i][j] + " ");
				}
				output.write("\n");
			}
			
			output.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
