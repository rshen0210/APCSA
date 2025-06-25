/**
 *	SudokuMaker - Creates a Sudoku puzzle using recursion and backtracking
 *
 *	@author	Ryan Shen
 *	@version
 * 	@since 1/16/24
 *
 */


public class SudokuMaker {

	private int[][] puzzle;
	/**
	 * constructor - initializes all of the field variables
	 * 
	 */
	public SudokuMaker() {
		puzzle = new int[9][9];
		for (int row = 0; row < puzzle.length;row++) {
			for (int col = 0;col < puzzle[0].length;col++) {
				puzzle[row][col] = 0;
			}
		}
	}
	/**
	 *	printPuzzle - prints the Sudoku puzzle with borders
	 *	If the value is 0, then print an empty space; otherwise, print the number.
	 */
	public void printPuzzle() {
		System.out.print("  +-----------+-----------+-----------+\n");
		String value = "";
		for (int row = 0; row < puzzle.length; row++) {
			for (int col = 0; col < puzzle[0].length; col++) {
				// if number is 0, print a blank
				if (puzzle[row][col] == 0) value = " ";
				else value = "" + puzzle[row][col];
				if (col % 3 == 0)
					System.out.print("  |  " + value);
				else
					System.out.print("  " + value);
			}
			if ((row + 1) % 3 == 0)
				System.out.print("  |\n  +-----------+-----------+-----------+\n");
			else
				System.out.print("  |\n");
		}
	}
	/**
	 * this method shuffles the numbers 1-9
	 * the array will be shuffled because it is pass 
	 * by reference
	 * @param array
	 */
	private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = (int) (Math.random() * (i + 1));
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
	/**
	 * This is the createMethod method that fills in the 
	 * grid with values
	 * @param row
	 * @param col
	 * @return true if valid number is found
	 * false if no valid number is found
	 */
    public boolean createMethod(int row, int col) {
        if (row == 9) {
            return true; // base case
        } else {
            int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            shuffleArray(numbers);

            for (int num : numbers) {
                if (isValid(num, row, col)) {
                    puzzle[row][col] = num;

                    int nextRow = row;
                    int nextCol = col + 1;

                    if (nextCol == 9) {
                        nextRow++;
                        nextCol = 0;
                    }

                    if (createMethod(nextRow, nextCol)) {
                        return true; // Successful placement, return true
                    }

                    // If the placement is unsuccessful, backtrack to the previous cell
                    puzzle[row][col] = 0;
                }
            }

            // If no valid numbers are found, backtrack
            return false;
        }
    }
	/**
	 * checks if the column is valid
	 * @param num
	 * @param row
	 * @return true if the column is valid
	 * 
	 */
	private boolean isValidColumn(int num, int row) {
		for (int i = 0;i<puzzle[row].length;i++) {
			if (puzzle[row][i] == num) {
				return false;
			}		
		}
		return true;
	}
	/**
	 * checks if the row is valid
	 * @param num
	 * @param column
	 * @return true if the row is valid
	 */
	private boolean isValidRow(int num, int column) {
		for (int i = 0;i<puzzle.length;i++) {
			if (num == puzzle[i][column]) {
				return false;
			}
		}
		return true;
	}
	/**
	 * checks if the grid is valid
	 * @param num
	 * @param column
	 * @param row
	 * @return true if the grid is valid
	 */
	private boolean isValidGrid(int num, int column, int row) {
		int col = column - (column % 3);
		int ro = row - (row % 3);
		for (int x = ro;x<ro+3;x++) {
			for (int i = col;i<col+3;i++) {
				if (puzzle[x][i] == num) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * checks if the number is valid
	 * @param num
	 * @param row
	 * @param col
	 * @return true if number is valid in column, row, grid
	 */
	private boolean isValid(int num, int row, int col) {
		if (isValidColumn(num, row) && isValidRow(num, col) &&
		 isValidGrid(num, col, row))
		return true;
		return false;
	}
	/**
	 * run method that runs the createMethod and prints it
	 */
	public void run() {
		System.out.println();
		System.out.println("Sudoku Puzzle");
		createMethod(0,0);
		printPuzzle();
		System.out.println();
	}
	public static void main (String [] args) {
		SudokuMaker sm = new SudokuMaker();
		sm.run();
	}
}