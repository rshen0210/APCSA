/**
 * USMap plots all the cities in the United States as well as the radius of bigger cities
 * @author: Ryan Shen
 * @since: 9/5/23
 * 
 */
import java.util.Scanner;

public class USMap {
	String cityCord[][];
	public USMap() {
		cityCord = new String [1112][3];
	}
	public static void main(String [] args) {
		USMap usmap = new USMap();
		usmap.run();
	}
	public void run() {
		setUpCanvas();
		drawCities();
		bigCities();
	}
	public void drawCities() {
		Scanner infile = FileUtils.openToRead("cities.txt");
		int count = 0;
		while (infile.hasNextLine()) {
			double ycoord = infile.nextDouble();
			double xcoord = infile.nextDouble();
			cityCord[count][0] = Double.toString(ycoord); // ycoord
			cityCord[count][1] = Double.toString(xcoord); // xcoord
			cityCord[count][2] = infile.nextLine().trim(); // name
			StdDraw.point(xcoord,ycoord);
			count++;
		}
	}
	public void setUpCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900,512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0,52.0);
		StdDraw.setPenRadius(0.006);
		StdDraw.setPenColor(StdDraw.GRAY);
	}
	public void bigCities() {
		Scanner infile = FileUtils.openToRead("bigCities.txt");
		double population, x, y, penRad;
		population = 0.0;
		String str1 = new String("");
		String name = "";
		x = 0.0;
		y = 0.0;
		penRad = 0.0;
		while (infile.hasNextLine()) {
			String str = infile.nextLine();
			int nameIndex = str.indexOf(' ')+1;
			int nameIndexF = str.indexOf(',')+4;
			int popIndex = str.indexOf(',')+5;
			int popIndexF = str.length();


			name = str.substring(nameIndex, nameIndexF); // city name
			str1 = str.substring(popIndex, popIndexF); // population
			for (int i = 0;i<cityCord.length;i++) {
				if (name.equals(cityCord[i][2])) {
					population = Double.parseDouble(str1);
					y = Double.parseDouble(cityCord[i][0]);
					x = Double.parseDouble(cityCord[i][1]);
					break;
				}
			}

			
			
			penRad = 0.6*(Math.sqrt(population)/18500);
			StdDraw.setPenRadius(penRad);

			if(population > 910000) {
				StdDraw.setPenColor(StdDraw.RED);
			}
			else {
				StdDraw.setPenColor(StdDraw.BLUE);
			}
			StdDraw.point(x,y);
		}
	}
}