/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 * @author: Ryan Shen
 * @since: 9/4/23
 * 
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		if (frontIsBlocked()) {
			putBeeper();
		}
		else {
			while (frontIsClear()) {
				move();
			}
			putBeeper();
			turnAround();
			while (frontIsClear()) {
				move();
			}
			putBeeper();
			turnAround();
			while (beepersInBag()) {
				move();
				if (beepersPresent()) {
					pickBeeper();
					turnAround();
					move();
					if (beepersPresent()) {
						break;
					}
					putBeeper();
				}
						
			}
		}
		
	}


}
