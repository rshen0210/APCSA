/**
 *	FirstAssignment.java
 *	Display a brief description of your summer vacation on the screen.
 *
 *	To compile Linux:	javac -cp .:mvAcm.jar FirstAssignment.java
 *	To execute Linux:	java -cp .:mvAcm.jar FirstAssignment
 *
 *	To compile MS Powershell:	javac -cp ".;mvAcm.jar" FirstAssignment.java
 *	To execute MS Powershell:	java -cp ".;mvAcm.jar" FirstAssignment
 *
 *	@author	Ryan Shen
 *	@since	August 24, 2023
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
    
    public void run() {
    	//	The font to be used
    	Font f = new Font("Serif", Font.BOLD, 18);
    	
    	//	Line 1
    	GLabel s1 = new GLabel("What I did on my summer vacation ...", 10, 20);
    	s1.setFont(f);
    	add(s1);
    	    	
    	//	Continue adding lines until you have 12 to 15 lines
    	GLabel s2 = new GLabel("My family took a trip to the other side of the world" +
								". We went and", 10, 40);
		GLabel s3 = new GLabel(" visited our parents and grandparents in Taiwan." +
								" The trip took 3 ", 10, 60);
		GLabel s4 = new GLabel("weeks. During the trip, my mandarin improved a whole lot!" +
								" I also", 10, 80);
		GLabel s5 = new GLabel(" learned more social skills. I did this through " +
								"playing pick-up basketball", 10, 100);
		GLabel s6 = new GLabel(" at the park. While there, I met up with some classmates " +
								"that also happened to be ", 10, 120);
		GLabel s7 = new GLabel("in Taiwan. We both played basketball together " +
								" and it was so much fun! ", 10, 140);
		GLabel s8 = new GLabel("Another thing I did over the summer was join many volunteer " +
								"organizations. In total", 10, 160);
		GLabel s9 = new GLabel(", I gained over 100 hours as well as many new friends" +
								". I volunteered at ", 10, 180);
		GLabel s10 = new GLabel("food distributions, parks, etc. During the summer" +
								", I also played ", 10, 200);
		GLabel s11 = new GLabel("lots of basketball. I played by myself as well as" +
								" at outside tournaments.", 10, 220);
		GLabel s12 = new GLabel(" Another thing I did was go biking around a lot." +
								" I went with my friends, ", 10, 240);
		GLabel s13 = new GLabel(" typically to go mountain biking or BMX biking. "+
								"These sessions were always ", 10, 260);
		GLabel s14 = new GLabel(" ending at around 8 pm. A typical ride consists"+
								" of us going to ", 10, 280);
		GLabel s15 = new GLabel(" Fremont Older.",10,300);
		
    	s2.setFont(f);
    	add(s2);
    	s3.setFont(f);
    	add(s3);
    	s4.setFont(f);
    	add(s4);
    	s5.setFont(f);
    	add(s5);
    	s6.setFont(f);
    	add(s6);
    	s7.setFont(f);
    	add(s7);
    	s8.setFont(f);
    	add(s8);
    	s9.setFont(f);
    	add(s9);
    	s10.setFont(f);
    	add(s10);
    	s11.setFont(f);
    	add(s11);
    	s12.setFont(f);
    	add(s12);
    	s13.setFont(f);
    	add(s13);
    	s14.setFont(f);
    	add(s14);
    	s15.setFont(f);
    	add(s15);
    }
    
}
