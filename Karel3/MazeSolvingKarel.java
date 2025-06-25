/*
 * File: MazeSolvingKarel.java
 * -----------------------------
 * The MazeSolvingKarelFinal class extends the basic Karel class
 * so that Karel solves a maze.
 * 
 * @author: Ryan Shen
 * @since: 8/30/23
 * 
 */

import stanford.karel.*;

public class MazeSolvingKarel extends SuperKarel {

	public void run() {
		while (noBeepersPresent()) {
			if (rightIsBlocked()) {
				if (leftIsBlocked()) {
					if (frontIsBlocked()) {
						turnAround();
						move();
					}
					else
					move();
				}
				else if (frontIsBlocked()) {
					turnLeft();
					move();
				}
				else {
					move();
				}
			}
			else {
				turnRight();
				move();
			}	
						
			
		}
		pickBeeper();
	}
	    
}
