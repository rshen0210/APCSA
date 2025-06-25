/**
 *	<Describe the SnakeBoard here>
 *
 *	@author	Ryan Shen
 *	@since	3/12/24
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	private int height, width;
	
	/*	Constructor	*/
	public SnakeBoard(int height1, int width2) {
		height = height1;
		width =width2;
		board = new char[height][width];
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {
		fillBoard(snake, target);
		System.out.println("\n");
		System.out.print("+ ");
		for (int i = 0;i<width;i++) {
			System.out.print("- ");
		}
		System.out.println("+");
		
		for (int row = 0;row<height;row++) {
			System.out.print("| ");
			for (int col = 0;col<width;col++) {
				System.out.print(""+ board[row][col] + " ");
			}
			System.out.println("| ");
		}

		System.out.print("+ ");
		for (int i = 0;i<width;i++) {
			System.out.print("- ");
		}
		System.out.println("+");
		System.out.println();
	}
	
	/* Helper methods go here	*/
	/**
	 * this  method fills the board with chars appropriate for each item
	 * @param snake
	 * @param target
	 */
	public void fillBoard(Snake snake, Coordinate target) {
		Coordinate head = snake.get(0).getValue();
		for (int row = 0;row<height;row++) {
			for (int col = 0;col<width;col++) {
				Coordinate cur = new Coordinate(row, col);
				for (int i = 0;i<snake.size();i++) {
					if (cur.equals(head)) {
						board[row][col] = '@';
						break;
					}
					else if (cur.equals(snake.get(i).getValue())) {
						board[row][col] = '*';
						break;
					}
					else if (cur.equals(snake.get(i).getValue()) && cur.equals(target)) {
						board[row][col] = '*';
						break;
					}
					else if (cur.equals(target)) {
						board[row][col] = '+';
						break;
					}
					else {
						board[row][col] = ' ';
					}

				}
			}
		}
	}
	/*	Accessor methods	*/
	/**
	 * accessor method to get the board
	 * @return board
	 */
	public char[][] getBoard() {
		return board;
	}
	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	/* 
	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(3, 3);
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}*/
}