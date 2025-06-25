/**
 *	A SinglyLinkedList of Coordinate objects representing
 *	a snake on a two-dimensional grid.
 *
 *	@author	Ryan Shen
 *	@since	5/12/24
 */
public class Snake extends SinglyLinkedList<Coordinate> {
	/**	Constructor for making a Snake that is 5 grids high facing north.
	 *	Places the snake head at Coordinate location and the tail below.
	 *	Precondition: To place the Snake, the board must have at
	 *				least location.getRow() + 4 more rows.
	 */
	public Snake(Coordinate location) {
		add(location);
		int row = location.getX();
		int col = location.getY();
		for (int i =0;i<4;i++) {
			add(new Coordinate(row+i+1, col));
		}
	}
	/**	Constructor for making a Snake that is 5 grids high facing north.
	 *	Places the snake head at Coordinate location and the tail below.
	 *	Precondition: To place the Snake, the board must have at
	 *				least location.getRow() + 4 more rows.
	 */
	public Snake(int x,int y) { 
		Coordinate location = new Coordinate(x, y);
		add(location);
		int row = location.getX();
		int col = location.getY();
		for (int i =0;i<4;i++) {
			add(new Coordinate(row+i+1, col));
		}
	}
	
}