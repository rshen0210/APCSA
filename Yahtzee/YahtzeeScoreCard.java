/**
 *	This class contains methods for all of the scoring options
 *	including small straight, large straight, full house, etc.
 *	It also includes a method determining whether or not a slot is already 
 *	taken. This class will also contain the methods that print out the score
 *
 *	@author	Ryan Shen
 *	@since	10/1/23
 */
public class YahtzeeScoreCard {
	private int [] points; // keeps track of all the points for each category
	/**
	 * This is the constructor that just instantiates variables
	 * @param none
	 * @return none
	 */
	public YahtzeeScoreCard() {
		points = new int[13];
		for (int i = 0;i<13;i++) {
			points[i] = -1;
		}
	}
	/**
	 *	Get a category score on the score card.
	 *	@param category		the category number (1 to 13)
	 *	@return				the score of that category
	 */
	public int getScore(int category) {
		return points[category-1];
	}
	/**
	 * gets the final score on the game for each player
	 * @param none
	 * @return finalNum
	 */
	public int finalScore() {
		int finalNum = 0; // is the final score of the game for a player
		for (int i = 0;i<12;i+=2) {
			finalNum += points[i]+points[i+1];
		}
		return finalNum;
	}
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}


	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		boolean takes = true; // determines whether or not the spot can be taken
		if (getScore(choice) == -1) {
			takes = true;
		}
		else
		takes = false;
		return takes;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		points[choice-1]= 0;
		for (int i = 0;i<5;i++) {
			if (dg.getDie(i).getLastRollValue() == choice) 
			points[choice-1]+= choice;
		}
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {
		points[6] = 0;
		int [] diePoints = new int [5]; // holds the values for each die
		for (int i = 0;i<diePoints.length;i++) {
			diePoints[i]=dg.getDie(i).getLastRollValue();
		}
		// organizes diePoints from least to greatest using a bubble sort
		for (int i = 0; i < diePoints.length; i++) {
			for (int x = i + 1; x < diePoints.length; x++) {
				int temp = 0;
				if (diePoints[i] > diePoints[x]) {
					temp = diePoints[i];
					diePoints[i] = diePoints[x];
					diePoints[x] = temp;
				}
			}
		}
		for (int i = 0;i<=2;i++) {
			if (diePoints[i] == diePoints[i+1] && 
			diePoints[i] == diePoints[i+2]) {
				points[6] = dg.getDie(0).getLastRollValue()+dg.getDie(1).getLastRollValue()+
				dg.getDie(2).getLastRollValue()+dg.getDie(3).getLastRollValue()+
				dg.getDie(4).getLastRollValue();
			}
		}
	}
	
	public void fourOfAKind(DiceGroup dg) {
		points[7] = 0;
		int [] diePoints = new int [5]; // holds the values for each die
		for (int i = 0;i<diePoints.length;i++) {
			diePoints[i]=dg.getDie(i).getLastRollValue();
		}
		// organizes diePoints from least to greatest using a bubble sort
		for (int i = 0; i < diePoints.length; i++) {
			for (int x = i + 1; x < diePoints.length; x++) {
				int temp = 0;
				if (diePoints[i] > diePoints[x]) {
					temp = diePoints[i];
					diePoints[i] = diePoints[x];
					diePoints[x] = temp;
				}
			}
		}
		for (int i = 0;i<=1;i++) {
			if (diePoints[i] == diePoints[i+1] && 
			diePoints[i] == diePoints[i+2] && 
			diePoints[i] == diePoints[i+3]) {
				points[7] = dg.getDie(0).getLastRollValue()+dg.getDie(1).getLastRollValue()+
				dg.getDie(2).getLastRollValue()+dg.getDie(3).getLastRollValue()+
				dg.getDie(4).getLastRollValue();
			}
		}
	}
	
	public void fullHouse(DiceGroup dg) {
		//how many times each number appears in die
		points[8] = 0;
        int freq[] = new int[7]; 
        for(int i = 0 ; i < 5; i ++) {
            freq[dg.getDie(i).getLastRollValue()] ++ ; 
        }
        //check for full house scenario
        for(int i = 1; i <= 6; i ++) {
            for(int j = 1 ; j <= 6; j ++) {
                if(freq[i] == 2 && freq[j] == 3) {
                    points[8] = 25; 
                }
            }
        }
	}
	
	public void smallStraight(DiceGroup dg) {
		points[9] = 0;
		int [] diePoints = new int [5]; // holds the values for each die
		for (int i = 0;i<diePoints.length;i++) {
			diePoints[i]=dg.getDie(i).getLastRollValue();
		}
		// organizes diePoints from least to greatest using a bubble sort
		for (int i = 0; i < diePoints.length; i++) {
			for (int x = i + 1; x < diePoints.length; x++) {
				int temp = 0;
				if (diePoints[i] > diePoints[x]) {
					temp = diePoints[i];
					diePoints[i] = diePoints[x];
					diePoints[x] = temp;
				}
			}
		}
		for (int i = 0;i<diePoints.length-1;i++) {
			if (diePoints[i] == diePoints[i+1])
			diePoints[i] = -2;
		}
		// organizes diePoints from least to greatest using a bubble sort
		for (int i = 0; i < diePoints.length; i++) {
			for (int x = i + 1; x < diePoints.length; x++) {
				int temp = 0;
				if (diePoints[i] > diePoints[x]) {
					temp = diePoints[i];
					diePoints[i] = diePoints[x];
					diePoints[x] = temp;
				}
			}
		}
		if (diePoints[0]+1 == diePoints[1] &&
			(diePoints[0]+2 == diePoints[2]) &&
			(diePoints[0]+3 == diePoints[3])) {
				points[9] = 30;
		}
		else if (diePoints[1]+1 == diePoints[2] &&
			(diePoints[1]+2 == diePoints[3]) &&
			(diePoints[1]+3 == diePoints[4])) {
				points[9] = 30;
		}
	}
	
	public void largeStraight(DiceGroup dg) {
		points[10] = 0;
		int [] diePoints = new int [5]; // holds the values for each die
		for (int i = 0;i<diePoints.length;i++) {
			diePoints[i]=dg.getDie(i).getLastRollValue();
		}
		// organizes diePoints from least to greatest using a bubble sort
		for (int i = 0; i < diePoints.length; i++) {
			for (int x = i + 1; x < diePoints.length; x++) {
				int temp = 0;
				if (diePoints[i] > diePoints[x]) {
					temp = diePoints[i];
					diePoints[i] = diePoints[x];
					diePoints[x] = temp;
				}
			}
		}
		if (diePoints[0]+1 == diePoints[1] &&
			(diePoints[0]+2 == diePoints[2]) &&
			(diePoints[0]+3 == diePoints[3]) &&
			(diePoints[0]+4 == diePoints[4])) {
				points[10] = 40;
		}
		
	}
	
	public void chance(DiceGroup dg) {
		points[11] = dg.getDie(0).getLastRollValue()+dg.getDie(1).getLastRollValue()+
		dg.getDie(2).getLastRollValue()+dg.getDie(3).getLastRollValue()+
		dg.getDie(4).getLastRollValue();
	}
	
	public void yahtzeeScore(DiceGroup dg) {
		for (int i = 0;i<4;i++) {
			if (dg.getDie(i).getLastRollValue() == dg.getDie(i+1).getLastRollValue()) {
				points[12] = 50;
			}
			else {
				points[12] = 0;
				break;
			}
		}
	}

}
