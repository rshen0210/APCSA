/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment Karel4.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 * @author: Ryan Shen
 * @since: 9/1/23
 * 
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		if (frontIsBlocked()) {
			turnLeft();
			while (frontIsClear()) {
				putBeeper();
				if (frontIsClear()) {
					move();
				}
				else
				break;
				if (frontIsClear()) {
					move();
				}
				else
				break;
			}
		}
		else {
			while (beepersInBag()) {
				putBeeper();
				if (frontIsClear()) {
					move();
					if (frontIsClear()) {
						move();
					}
					else if (facingEast()) { // on the case of even columns (facing right)
						turnLeft();
						if (frontIsClear()) {
							move();
							turnLeft();
						}
						else
						break;
					}
					else if (facingWest()) { // on the case of even columns (facing left) (same as even code above)
						turnRight();
						if (frontIsClear()) {
							move();
							turnRight();
						}
						else
						break;
					}
				}
				else if (facingWest()) { // on the case of odd columns (facing left) (code same as even columns below)
					turnRight();
					if (frontIsClear()) {
						move();
						turnRight();
					}
					else // if front isn't clear after turning to the right (you've hit top of the box)
					break;
				}
				else if (facingEast()) { // on the case of odd columns (facing right)
					turnLeft();
					if (frontIsClear()) {
						move();
						turnLeft();
						if (frontIsClear()) {
							move();
						}
					}
					else // if front isn't clear whilst facing east (you've hit top of the box)
					break;
				}
				
				
			}
		}
	}

}

