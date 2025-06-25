/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 * @author: Ryan Shen
 * @since: 9/3/23
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		turnLeft();
		while (beepersInBag()) {
			if (noBeepersPresent()) {
				putBeeper();
			}
			if (frontIsClear()) {
				move();
			}
			else if (facingNorth()) {
				turnAround();
			}
			else {
				turnLeft();
				if (frontIsClear()) {
					for (int i = 0;i<4;i++)
					move();
				}
				else
				break;
				turnLeft();
				
			}
			
		}
	}
	
}
