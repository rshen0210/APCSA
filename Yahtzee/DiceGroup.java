/**
 *  Create a roll of 5 dice and display them.
 *
 *  @author Mr Greenstein and 
 *  @since	10/1/23
*/

public class DiceGroup {
	private Dice [] die;
	// Create the seven different line images of a die
	String [] line = {	" _______ ",
						"|       |",
						"| O   O |",
						"|   O   |",
						"|     O |",
						"| O     |",
						"|_______|" };
	
	/**
	 *  Creates a group of 5 dice
	 */
	public DiceGroup() {
		die = new Dice[5];
		for (int i = 0;i<5;i++) {
			die[i]= new Dice();
		}
	}
		
	/**
	 *  Roll all dice.
	 */
	public void rollDice() {
		for (int i = 0;i<5;i++) {
			die[i].roll();
		}
	}
	
	/**
	 *  Roll only the dice not in the string "rawHold".
	 *  e.g. If rawHold="24", only roll dice 1, 3, and 5
	 *  @param rawHold	the numbered dice to hold
	 */
	public void rollDice(String rawHold) {
		boolean keepFirst, keepSecond, keepThird, keepFourth, keepFifth;
		keepFirst = keepSecond = keepThird = keepFourth = keepFifth = false;
		for (int i = 0;i<rawHold.length();i++) {
			switch (rawHold.charAt(i)) {
				case '1':	keepFirst = true;
							break;
				case '2':	keepSecond = true;
							break;	
				case '3':	keepThird = true;
							break;
				case '4':	keepFourth = true;
							break;
				case '5':	keepFifth = true;
							break;
			}
		}
		if (!keepFirst)
		getDie(0).roll();
		if (!keepSecond)
		getDie(1).roll();
		if (!keepThird)
		getDie(2).roll();
		if (!keepFourth)
		getDie(3).roll();
		if (!keepFifth)
		getDie(4).roll();
	}
	
	/**
	 *  Dice getter method
	 *  @param i	the index into the die array
	 */
	public Dice getDie(int i) { return die[i]; }
	
	/**
	 *  Computes the sum of the dice group.
	 *
	 *  @return  The integer sum of the dice group.
	 */
	public int getTotal() {
		int sum = 0;
		for (int i = 0; i < die.length; i++) sum += die[i].getLastRollValue();
		return sum;
	}
	
	/**
	 *  Prints out the images of the dice
	 */
	public void printDice() {
		printDiceHeaders();
		for (int i = 0; i < 6; i++) {
			System.out.print(" ");
			for (int j = 0; j < die.length; j++) {
				printDiceLine(die[j].getLastRollValue() + 6 * i);
				System.out.print("     ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 *  Prints the first line of the dice.
	 */
	public void printDiceHeaders() {
		System.out.println();
		for (int i = 1; i < 6; i++) {
			System.out.printf("   # %d        ", i);
		}
		System.out.println();
	}
	
	/**
	 *  Prints one line of the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 */
	public void printDiceLine(int value) {
		System.out.print(line[getDiceLine(value)]);
	}
	
	/**
	 *  Gets the index number into the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 *  @return	the index into the line array image of the dice
	 */
	public int getDiceLine(int value) {
		if (value < 7) return 0;
		if (value < 14) return 1;
		switch (value) {
			case 20: case 22: case 25:
				return 1;
			case 16: case 17: case 18: case 24: case 28: case 29: case 30:
				return 2;
			case 19: case 21: case 23:
				return 3;
			case 14: case 15:
				return 4;
			case 26: case 27:
				return 5;
			default:	// value > 30
				return 6;
		}
	}
}
