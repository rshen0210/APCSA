/**
 *	Yahtzee is a two player game involving rolling 5 die for 13 rounds straight
 *	depending on the number faces on each of the die, the player will be scored points
 *	which will be put into a scorecard whoever has the most 
 *	points at the end wins the game
 *	@author	Ryan Shen
 *	@since	10/2/23
 */
 
public class Yahtzee {
	private YahtzeePlayer pl1; // this is player 1's player info 
	private YahtzeePlayer pl2; // this is player 2's player info
	private YahtzeeScoreCard ysc; // this is player 1's score card
	private YahtzeeScoreCard ysc1; // this is player 2's score card
	private DiceGroup dg1; // this is player 1's dice group
	private DiceGroup dg2; // this is player 2's dice group
	private boolean player1IsFirst; // this boolean checks if player1 is first or not
	/**
	 * This is the constructor that instanciates variables
	 * @param none
	 * @return none
	 */
	public Yahtzee() {
		pl1 = new YahtzeePlayer(); // this is player 1's player info 
		pl2 = new YahtzeePlayer(); // this is player 2's player info
		dg1 = new DiceGroup(); // this is player 1's dice group
		dg2 = new DiceGroup(); // this is player 2's dice group
		ysc = new YahtzeeScoreCard(); // this is player 1's score card
		ysc1 = new YahtzeeScoreCard(); // this is player 2's score card
		player1IsFirst = true;
	}
	
	/**
	 * This method just prints the header of the game
	 * @param none
	 * @return none
	 */
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
	/**
	 * this is the main run method, that runs each of the methods
	 * @param none
	 * @return none
	 */
	public void run() {
		printHeader();
		askForPlayersName();
		testForFirst();
		mainGame();
		reportScore();
	}
	/** 
	 * This method asks for the players' name and sets the players' name in YahtzeePlayer class
	 * @param none
	 * @return none
	 */
	public void askForPlayersName() {
		String name = Prompt.getString("Player 1, Please enter your "+ 
		"first name : "); // gets player 1's name.
		System.out.println("");
		String name2 = Prompt.getString("Player 2, Please enter your "+ 
		"first name : "); // gets player 2's name.
		System.out.println("");
		pl1.setName(name);
		pl2.setName(name2);
	}
	/**
	 * This method makes both users roll their dices and figures out who
	 * goes first
	 * @param none
	 * @return none
	 */
	public void testForFirst() {
		int player1Sum; // this is player1's dice sum on their first roll
		int player2Sum; // this is player2's dice sum on their second roll
		String moveOn1; // these are used for the prompts
		String moveOn2; // these are used for the prompts
		moveOn1 = Prompt.getString("Lets see who will go first,"+
		" "+pl1.getName()+" please hit enter to roll the dice : ");
		dg1.rollDice();
		dg1.printDice();
		moveOn2 = Prompt.getString("Lets see who will go first,"+
		" "+pl2.getName()+" please hit enter to roll the dice : ");
		dg2.rollDice();
		dg2.printDice();
		player1Sum = dg1.getTotal();
		player2Sum = dg2.getTotal();
		System.out.println(pl1.getName()+", you rolled a sum of "+
		player1Sum+", and "+pl2.getName()+", you rolled a sum of "+
		player2Sum+".");
		if (player1Sum > player2Sum) {
			System.out.println(pl1.getName()+" since your sum was "+
			"higher, you'll roll first.");
			player1IsFirst = true;
		}
		else if (player1Sum < player2Sum) {
			System.out.println(pl2.getName()+" since your sum was "+
			"higher, you'll roll first.");
			player1IsFirst = false;
		}
		else {
			System.out.println("Whoops, we have a tie (both rolled "+ 
			player1Sum+"). Looks like we'll have to try that again . . .");
			System.out.println("");
			testForFirst();
		}
	}
	/**
	 * This method controls the main part of the game
	 * @param none
	 * @return none
	 */
	public void mainGame() {
		String hitEnter, keepDice; // hitEnter is just prompting for the enter key, keepDice is what dice to keep
		int scoreSpot; // scoreSpot is choosing an integer to score
		boolean validLoop; // determines whether or not user picks a valid choice
		System.out.println("");
		for (int x = 1;x<=13;x++) { 
			ysc.printCardHeader();
			ysc.printPlayerScore(pl1);
			ysc1.printPlayerScore(pl2);
			System.out.println("Round "+x+" of 13 rounds");
			if (player1IsFirst) {
				hitEnter = Prompt.getString(pl1.getName()+", it's your turn to play."+
				" Please hit enter to roll the dice :");
				dg1.rollDice();
				dg1.printDice();
				for (int i = 0;i<2;i++) {
					System.out.println("Which di(c)e would you like to keep?"+
					"  Enter the values you'd like to 'hold' without\n"+
					"spaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
					keepDice = Prompt.getString("(enter -1 if you'd like to end the turn) : ");
					if (keepDice.equals("-1")) {
						break;
					}
					else {
						dg1.rollDice(keepDice);
						dg1.printDice();
					}
				}
				ysc.printCardHeader();
				ysc.printPlayerScore(pl1);
				ysc1.printPlayerScore(pl2);
				System.out.printf("      \t\t  1    2    3    4    5    6    7    8    9   " +
							" 10   11   12   13\n");
				validLoop = true;
				while (validLoop) {
					int i = 0;
					validLoop = false;
					scoreSpot = 0;
					if (i == 0)
					scoreSpot = Prompt.getInt(pl1.getName()+", now you need to make a choice. "+
					"Pick a valid integer from the list above : ");
					if (i!=0)
					scoreSpot = Prompt.getInt("Pick a valid integer from the list above : ");
					if (scoreSpot >= 1 && scoreSpot <= 6 && ysc.changeScore(scoreSpot, dg1))
					ysc.numberScore(scoreSpot, dg1);
					else if (scoreSpot == 7 && ysc.changeScore(scoreSpot, dg1))
					ysc.threeOfAKind(dg1);
					else if (scoreSpot == 8 && ysc.changeScore(scoreSpot, dg1))
					ysc.fourOfAKind(dg1);
					else if (scoreSpot == 9 && ysc.changeScore(scoreSpot, dg1))
					ysc.fullHouse(dg1);
					else if (scoreSpot == 10 && ysc.changeScore(scoreSpot, dg1))
					ysc.smallStraight(dg1);
					else if (scoreSpot == 11 && ysc.changeScore(scoreSpot, dg1))
					ysc.largeStraight(dg1);
					else if (scoreSpot == 12 && ysc.changeScore(scoreSpot, dg1))
					ysc.chance(dg1);
					else if (scoreSpot == 13 && ysc.changeScore(scoreSpot, dg1))
					ysc.yahtzeeScore(dg1);
					else {
						validLoop = true;
						i++;
					}
					
				}
				if (x != 0) {
					ysc.printCardHeader();
					ysc.printPlayerScore(pl1);
					ysc1.printPlayerScore(pl2);
				}
				hitEnter = Prompt.getString(pl2.getName()+", it's your turn to play."+
				" Please hit enter to roll the dice :");
				dg2.rollDice();
				dg2.printDice();
				for (int i = 0;i<2;i++) {
					System.out.println("Which di(c)e would you like to keep?"+
					"  Enter the values you'd like to 'hold' without\n"+
					"spaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
					keepDice = Prompt.getString("(enter -1 if you'd like to end the turn) : ");
					if (keepDice.equals("-1")) {
						break;
					}
					else {
						dg2.rollDice(keepDice);
						dg2.printDice();
					}
				}
				ysc.printCardHeader();
				ysc.printPlayerScore(pl1);
				ysc1.printPlayerScore(pl2);
				System.out.printf("      \t\t  1    2    3    4    5    6    7    8    9  " +
							" 10   11   12   13\n");
				validLoop = true;
				while (validLoop) {
					int i = 0;
					validLoop = false;
					scoreSpot = 0;
					if (i == 0)
					scoreSpot = Prompt.getInt(pl2.getName()+", now you need to make a choice. "+
					"Pick a valid integer from the list above : ");
					if (i!=0)
					scoreSpot = Prompt.getInt("Pick a valid integer from the list above : ");
					if (scoreSpot >= 1 && scoreSpot <= 6 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.numberScore(scoreSpot, dg2);
					else if (scoreSpot == 7 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.threeOfAKind(dg2);
					else if (scoreSpot == 8 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.fourOfAKind(dg2);
					else if (scoreSpot == 9 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.fullHouse(dg2);
					else if (scoreSpot == 10 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.smallStraight(dg2);
					else if (scoreSpot == 11 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.largeStraight(dg2);
					else if (scoreSpot == 12 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.chance(dg2);
					else if (scoreSpot == 13 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.yahtzeeScore(dg2);
					else {
						validLoop = true;
						i++;
					}
					
				}
			}
			else {
				ysc.printCardHeader();
				ysc.printPlayerScore(pl1);
				ysc1.printPlayerScore(pl2);
				hitEnter = Prompt.getString(pl2.getName()+", it's your turn to play."+
				" Please hit enter to roll the dice :");
				dg2.rollDice();
				dg2.printDice();
				// this loop prints the actual dice groups that will be displayed for a player
				// each player get three turns to roll the dice and have options as to if they 
				// want to "hold" dice
				for (int i = 0;i<2;i++) {
					System.out.println("Which di(c)e would you like to keep?"+
					"  Enter the values you'd like to 'hold' without\n"+
					"spaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
					keepDice = Prompt.getString("(enter -1 if you'd like to end the turn) : ");
					if (keepDice.equals("-1")) {
						break;
					}
					else {
						dg2.rollDice(keepDice);
						dg2.printDice();
					}
				}
				ysc.printCardHeader();
				ysc.printPlayerScore(pl1);
				ysc1.printPlayerScore(pl2);
				System.out.printf("      \t\t  1    2    3    4    5    6    7    8    9  " +
							" 10   11   12   13\n");
				validLoop = true;
				// this while loop prompts for the users scoring option and changes the score
				// depending on whether or not the user have successfully gotten what they feel 
				// that they have gotten and whether or not the slot is already taken
				while (validLoop) {
					int i = 0;
					validLoop = false;
					scoreSpot = 0;
					if (i == 0)
					scoreSpot = Prompt.getInt(pl2.getName()+", now you need to make a choice. "+
					"Pick a valid integer from the list above : ");
					if (i!=0)
					scoreSpot = Prompt.getInt("Pick a valid integer from the list above : ");
					if (scoreSpot >= 1 && scoreSpot <= 6 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.numberScore(scoreSpot, dg2);
					else if (scoreSpot == 7 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.threeOfAKind(dg2);
					else if (scoreSpot == 8 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.fourOfAKind(dg2);
					else if (scoreSpot == 9 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.fullHouse(dg2);
					else if (scoreSpot == 10 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.smallStraight(dg2);
					else if (scoreSpot == 11 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.largeStraight(dg2);
					else if (scoreSpot == 12 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.chance(dg2);
					else if (scoreSpot == 13 && ysc1.changeScore(scoreSpot, dg2))
					ysc1.yahtzeeScore(dg2);
					else {
						validLoop = true;
						i++;
					}
					
				}
				if (x != 0) {
					ysc.printCardHeader();
					ysc.printPlayerScore(pl1);
					ysc1.printPlayerScore(pl2);
				}
				hitEnter = Prompt.getString(pl1.getName()+", it's your turn to play."+
				" Please hit enter to roll the dice :");
				dg1.rollDice();
				dg1.printDice();
				// this loop prints the actual dice groups that will be displayed for a player
				// each player get three turns to roll the dice and have options as to if they 
				// want to "hold" dice
				for (int i = 0;i<2;i++) {
					System.out.println("Which di(c)e would you like to keep?"+
					"  Enter the values you'd like to 'hold' without\n"+
					"spaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
					keepDice = Prompt.getString("(enter -1 if you'd like to end the turn) : ");
					if (keepDice.equals("-1")) {
						break;
					}
					else {
						dg1.rollDice(keepDice);
						dg1.printDice();
					}
				}
				ysc.printCardHeader();
				ysc.printPlayerScore(pl1);
				ysc1.printPlayerScore(pl2);
				System.out.printf("      \t\t  1    2    3    4    5    6    7    8    9   " +
							" 10   11   12   13\n");
				validLoop = true;
				// this while loop prompts for the users scoring option and changes the score
				// depending on whether or not the user have successfully gotten what they feel 
				// that they have gotten and whether or not the slot is already taken
				while (validLoop) {
					int i = 0;
					validLoop = false;
					scoreSpot = 0;
					if (i == 0)
					scoreSpot = Prompt.getInt(pl1.getName()+", now you need to make a choice. "+
					"Pick a valid integer from the list above : ");
					if (i!=0)
					scoreSpot = Prompt.getInt("Pick a valid integer from the list above : ");
					if (scoreSpot >= 1 && scoreSpot <= 6 && ysc.changeScore(scoreSpot, dg1))
					ysc.numberScore(scoreSpot, dg1);
					else if (scoreSpot == 7 && ysc.changeScore(scoreSpot, dg1))
					ysc.threeOfAKind(dg1);
					else if (scoreSpot == 8 && ysc.changeScore(scoreSpot, dg1))
					ysc.fourOfAKind(dg1);
					else if (scoreSpot == 9 && ysc.changeScore(scoreSpot, dg1))
					ysc.fullHouse(dg1);
					else if (scoreSpot == 10 && ysc.changeScore(scoreSpot, dg1))
					ysc.smallStraight(dg1);
					else if (scoreSpot == 11 && ysc.changeScore(scoreSpot, dg1))
					ysc.largeStraight(dg1);
					else if (scoreSpot == 12 && ysc.changeScore(scoreSpot, dg1))
					ysc.chance(dg1);
					else if (scoreSpot == 13 && ysc.changeScore(scoreSpot, dg1))
					ysc.yahtzeeScore(dg1);
					else {
						validLoop = true;
						i++;
					}
					
				}
			}
		}
		ysc.printCardHeader();
		ysc.printPlayerScore(pl1);
		ysc1.printPlayerScore(pl2);
	}
	/**
	 * This method reports the final score and who won the match
	 * @param none
	 * @return none
	 */
	public void reportScore() {
		System.out.println(pl1.getName()+"'s final score: "+ysc.finalScore());
		System.out.println(pl2.getName()+"'s final score: "+ysc1.finalScore());
		if (ysc.finalScore()>ysc1.finalScore())
		System.out.println(pl1.getName() + " won the game!");
		else if (ysc.finalScore()<ysc1.finalScore())
		System.out.println(pl2.getName() + " won the game!");
		else
		System.out.println("It was a tie");
	}
	/**
	 * This is the main method that creates many instances of classes
	 * @param args
	 * @return none
	 */
	public static void main(String [] args) {
		Yahtzee yz = new Yahtzee();
		yz.run();
	}
}
