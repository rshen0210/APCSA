import java.io.PrintWriter;
import java.util.Scanner;

/**
 *	Snake Game - This is a copy of the snake game but this is played in terminal
 *	
 *	@author	Ryan Shen
 *	@since	5/12/24
 */
public class SnakeGame {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game

	/*	Constructor	*/
	public SnakeGame() { 
		score = 0;
		board = new SnakeBoard(20, 25);
		int chanceRow = (int)(Math.random() * 15);
		int chanceCol = (int)(Math.random() * 20);

		Coordinate loc = new Coordinate(chanceRow, chanceCol);
		snake = new Snake(loc);
		makeMatch();
	}
	
	/*	Main method	*/
	public static void main(String[] args) {
		SnakeGame sg = new SnakeGame();
		sg.run();
	}
	/**
	 * This run method runs the entire snakegame
	 * @param none
	 * @return void
	 */
	public void run() {
		printIntroduction();
		board.printBoard(snake, target);
		boolean lose = false;
		String in = "wasd";
		String in2 = "wasdhfrq";
		while (!lose) {
			//board.printBoard(snake, target);
			String out = Prompt.getString("Score: "+score+
			" (w - North, s - South, d - East, a - West, h - Help)");
			while (out.length() != 1 || !in2.contains(out)) {
				out = Prompt.getString("Score: "+score+
				" (w - North, s - South, d - East, a - West, h - Help)");
			}
			if (out.equals("h")) {
				helpMenu();
			}
			else if (out.equals("f")) {
				PrintWriter writer = FileUtils.openToWrite("snakeGame.txt");
				writer.println("Score "+score);
				writer.println("Target");
				writer.println(target.getX() + " "+target.getY());
				writer.println("Snake Head");
				writer.println(snake.get(0).getValue().getX()+" "+snake.get(0).getValue().getY());
				writer.println("Snake Body");
				for (int i = 1;i<snake.size();i++) {
					writer.print(snake.get(i).getValue().getX()+" "+snake.get(i).getValue().getY());
					if (i < snake.size()-1)
					writer.println();
				}
				writer.close();
				System.out.println();
				System.out.println("Game saved to snakeGame.txt");
				System.out.println();
				lose = true;
			}
			else if (out.equals("r")) {
				Scanner reader = FileUtils.openToRead("snakeGame.txt");
				reader.next();
				String str = reader.next();
				score = Integer.parseInt(str);
				reader.nextLine();
				reader.nextLine();
				
				String targetX = reader.next();
				String targetY = reader.next();
				Coordinate newCord = new Coordinate(Integer.parseInt(targetX), Integer.parseInt(targetY));
				target = newCord;
				reader.nextLine();
				reader.nextLine();
				String headX = reader.next();
				String headY = reader.next();
				Coordinate newCordForHead = new Coordinate(Integer.parseInt(headX), Integer.parseInt(headY));
				snake.get(0).setValue(newCordForHead);
				reader.nextLine();
				reader.nextLine();
				int i = 1;
				for (int j = 1;j<snake.size();j++) {
					snake.remove(j);
					j--;
				}
				while (reader.hasNextLine()) {
					String snakeBody1 = reader.next();
					String snakeBody2 = reader.next();
					Coordinate bodyCord = new Coordinate(Integer.parseInt(snakeBody1), Integer.parseInt(snakeBody2));
					snake.add(bodyCord);
					i++;
				}
				reader.close();
				board.printBoard(snake, target);
			}
			else if (out.equals("q")) {
				String quittin = Prompt.getString("Do you really want to quit? (y or n)");
				if (quittin.equals("y")) {
					lose = true;
				}
			}
			else if (out.length() == 1 && in.contains(out)) {
				Coordinate headMark = snake.get(0).getValue();
				Coordinate temp = snake.get(0).getValue();
				Coordinate temporary = snake.get(snake.size()-1).getValue();
				for (int i = 1;i<snake.size();i++) {
					Coordinate temp2 = snake.get(i).getValue();
					snake.get(i).setValue(temp);
					temp = temp2;
				}
				
				if (out.equals("w"))
				snake.get(0).setValue(new Coordinate(headMark.getX()-1, headMark.getY()));
				else if (out.equals("a")) 
				snake.get(0).setValue(new Coordinate(headMark.getX(), headMark.getY()-1));
				else if (out.equals("s")) 
				snake.get(0).setValue(new Coordinate(headMark.getX()+1, headMark.getY()));
				else if (out.equals("d")) 
				snake.get(0).setValue(new Coordinate(headMark.getX(), headMark.getY()+1));
				
				for (int i = 1;i<snake.size();i++) {
					if (snake.get(i).getValue().equals(snake.get(0).getValue())) {
						lose = true;
					}
				}
				if (snake.get(0).getValue().equals(target)) {
					score++;
					makeMatch();
					snake.add(temporary);
				}
				if (score == 489 || 
				!outOfBounds(snake.get(0).getValue().getX(), snake.get(0).getValue().getY())) { 
					lose = true;
				}
				if (noValidSpots()) {
					lose = true;
					board.printBoard(snake, target);
				}
				if (!lose)
				board.printBoard(snake, target);
			}
			
		}
		System.out.println("Thanks for playing SnakeGame!!!");
	}
	/**	Print the game introduction	*/
	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board " +
							"eating targets \"+\".");
		System.out.println("Each time the snake eats the target it grows " +
							"another * longer.");
		System.out.println("The objective is to grow the longest it can " +
							"without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}
	
	/**	Print help menu	*/
	public void helpMenu() {
		System.out.println("\nCommands:\n" +
							"  w - move north\n" +
							"  s - move south\n" +
							"  d - move east\n" +
							"  a - move west\n" +
							"  h - help\n" +
							"  f - save game to file\n" +
							"  r - restore game from file\n" +
							"  q - quit");
		Prompt.getString("Press enter to continue");
	}
	/**
	 * finds the next spot to place the target
	 * makes sure that the target is not placed directy on top
	 * of the snake
	 * @param none
	 * @return void
	 */
	public void makeMatch() {
		boolean notMatch = true;
		int chanceRowTarget;
		int chanceColTarget;
		Coordinate posCoordinate = new Coordinate(0, 0);
		while (notMatch) {
			notMatch = false;
			chanceRowTarget = (int)(Math.random() * 20);
			chanceColTarget = (int)(Math.random() * 25);
			posCoordinate = new Coordinate(chanceRowTarget, chanceColTarget);
			for (int i = 0;i<snake.size();i++) {
				if (posCoordinate.equals(snake.get(i).getValue())) {
					notMatch = true;
					break;
				}
			}
		}
		target = posCoordinate;
	}
	/**
	 * noValidSpots
	 * checks to see if there are valid spots left for the snake to move to 
	 * @return false if there are valid spots
	 * @return true if there are no more spots to go to
	 */
	public boolean noValidSpots() {
		board.fillBoard(snake, target);
		int xcoord = snake.get(0).getValue().getX();
		int ycoord = snake.get(0).getValue().getY();
		char [][] tempBoard = board.getBoard();
		if (xcoord - 1 > 0) {
			if (tempBoard[xcoord - 1][ycoord] == ' ') {
				return false;
			}
		}
		if (xcoord +1 < 20) {
			if (tempBoard[xcoord +1][ycoord] == ' ') {
				return false;
			}
		}
		if (ycoord -1 > 0) {
			if (tempBoard[xcoord][ycoord-1] == ' ') {
				return false;
			}
		}
		if (ycoord+1 < 25) {
			if (tempBoard[xcoord][ycoord+1] == ' ') {
				return false;
			}
		}
		return true;

	}
	/**
	 * checks if the snakes head is in bounds
	 * @return true if snakes head is in bounds 
	 * 		   false otherwise
	 * @param none
	 */
	public boolean outOfBounds(int coordx, int coordy) {
		if (coordx >= 0) {
			if (coordx <= 19) {
				if (coordy >= 0) {
					if (coordy <= 24) {
						return true;
					}
				}
			}
		}
		return false;
	}
}