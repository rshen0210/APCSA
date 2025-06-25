/*
 * File: DoubleBeeperKarel.java
 * -----------------------------
 * The DoubleBeeperKarel class extends the basic Karel class
 * so that Karel doubles the number of beepers on a corner.
 * 
 * @author: Ryan Shen
 * @since: 8/30/23
 * 
 */

import stanford.karel.*;

public class DoubleBeeperKarel extends SuperKarel {

	public void run() {
		move();
		doubleBeepers();
		singleBeepers();
	}
	
	public void doubleBeepers() {
		pickBeeper();
		move();
		putBeeper();
		putBeeper();
		turnAround();
		move();
		while (beepersPresent()) {
			pickBeeper();
			turnAround();
			move();
			putBeeper();
			putBeeper();
			turnAround();
			move();
		}
	}
	public void singleBeepers() {
		while (beepersInBag()) {
			turnAround();
			move();
			if (beepersPresent())
			pickBeeper();
			else
			break;
			turnAround();
			move();
			putBeeper();
		}
	}
}
