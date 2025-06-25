/**
 *	SimpleGraphics.java
 *
 *	To compile Linux:	javac -cp .:acm.jar SimpleGraphics.java
 *	To execute Linux:	java -cp .:acm.jar SimpleGraphics
 *	To compile MS Powershell:	javac -cp ".;acm.jar" SimpleGraphics.java
 *	To execute MS Powershell:	java -cp ".;acm.jar" SimpleGraphics
 *
 *	@author	Ryan Shen
 *	@since	9/19/23
 */
 
/*	All package classes should be imported before the class definition.
 *	"java.awt.Color" means package java.awt contains class Color. */
import java.awt.Color;

/*	The following libraries are in the acm.jar file. */
import acm.program.GraphicsProgram;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class SimpleGraphics extends GraphicsProgram {
	
	/*	All fields and constants should be declared here.
	 *	Only constants (final) are initialized here. */
	
	/**	The init() method is executed before the run() method.
	 *	All initialization steps should be performed here.
	 */
	public void init() {

	}
	
	/**	The run() method is executed after init().
	 *	The bulk of the program should be performed here.
	 *	Exercise hint: Use one-dimensional arrays for the GOval's and GRect's.
	 */
	public void run() {
		//circle = new GOval(100, 100, RADIUS * 2, RADIUS * 2);
		
		for (int i = 0;i<5;i++) {
			GOval circle = new GOval(250+(20*i), 360+(20*i), 280-(i*40),280-(i*40));
			circle.setFilled(true);
			if (i%2 == 0)
			circle.setFillColor(Color.RED);
			else
			circle.setFillColor(Color.WHITE);
			add(circle);
		}
		

		for (int i = 0;i<10;i++) {
			for (int j = 0;j<=i;j++) {
				GRect square = new GRect(110+(25*(10-i))+(50*j), 0+(20*9-(20*i)), 50, 20);
				add(square);
			}
			
		}

		/*square = new GRect(300, 100, SIDE, SIDE);
		square.setFilled(true);
		square.setFillColor(Color.BLUE);*/
		
		
		
	}
}