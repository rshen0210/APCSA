import java.util.Scanner;

/**
 * Binary Tree for State Objects
 *
 * @author Ryan Shen
 * @version 5/29/24
 */
public class BinaryTree {

	private final String DEFAULT_FILE_NAME = "states2.txt"; // Default input file
	private Scanner keyboard;
	
	private TreeNode<State> root;
	
	public BinaryTree() {
	}
	/**
	 * inserts a new state value in the list
	 * @param value
	 */
	public void add(State value) {
		TreeNode<State> newPlacer = new TreeNode<State>(value);
		if (root == null) {
			root = newPlacer;
		}
		else {
			TreeNode<State> cur = root;
			TreeNode<State> temp = cur;
			boolean right = false;
			while (cur != null) {
				temp = cur;
				if (value.compareTo(cur.getValue()) > 0) {
					cur = cur.getRight();
					right = true;
				}
				else {
					cur = cur.getLeft();
					right = false;
				}
			}
			if (right)
			temp.setRight(newPlacer);
			else
			temp.setLeft(newPlacer);
		}
	}
	/**
	 *	Load data from a text file
	 */
	public void loadData()
	{
		keyboard = FileUtils.openToRead(DEFAULT_FILE_NAME);
		State joe = new State(keyboard.next(), keyboard.next(), Integer.parseInt(keyboard.next()), 
		Integer.parseInt(keyboard.next()), Integer.parseInt(keyboard.next()), 
		keyboard.next(), Integer.parseInt(keyboard.next()), Integer.parseInt(keyboard.next()), 
		Integer.parseInt(keyboard.next()));
		root = new TreeNode<State>(joe);
		while (keyboard.hasNextLine()) {
			State tempState = new State(keyboard.next(), keyboard.next(), Integer.parseInt(keyboard.next()), 
			Integer.parseInt(keyboard.next()), Integer.parseInt(keyboard.next()), 
			keyboard.next(), Integer.parseInt(keyboard.next()), Integer.parseInt(keyboard.next()), 
			Integer.parseInt(keyboard.next()));
			add(tempState);
		}
		System.out.println("Loading file "+ DEFAULT_FILE_NAME); 
		System.out.println();
	}
	
	/**
	 * Insert State into tree
	 * @param next  State to insert
	 */
	public void insert(State next) {
		add(next);
	}
	

	/**
	 * Prints the tree as a list in ascending order by state name
	 */
	public void printList() {
		printInOrder(root);
	}
	/**
	 * prints the values of the tree in ascending order by state name
	 * @param node
	 */
	private void printInOrder(TreeNode<State> node) {
        if (node == null)
            return;
		printInOrder(node.getLeft());
		System.out.println(node.getValue());
        printInOrder(node.getRight());
    }
	
	
	/**
	 * Prompts user for State name to find, then starts search
	 */
	public void testFind() {
		boolean quit = false;
		while (!quit) {
			String in = Prompt.getString("Enter state name to search for (Q to quit) -");
			if (in.equalsIgnoreCase("Q")) {
				quit = true;
				continue;
			}
			if (searchForValue(root, in)) {

			}
			else {
				System.out.println("Name = "+in+" No such state name");
				System.out.println();
			}
			
		}
		
	}
	/**
	 * recursively searches for a target state value
	 */
	public boolean searchForValue(TreeNode<State> node, String target) {
		if (node == null) {
			return false;
		}
		if (node.getValue().getName().equalsIgnoreCase(target)) {
			System.out.println(node.getValue());
			return true;
		}
		return searchForValue(node.getLeft(), target) || searchForValue(node.getRight(), target);
	}
	/**
	 * Prompts user for State name to delete
	 * OPTIONAL: Not included in your grade!
	 */
	public void testDelete() {
		boolean quit = false;
		while (!quit) {
			String in = Prompt.getString("Enter state name to delete for (Q to quit) -");
			System.out.println();
			if (in.equalsIgnoreCase(("q"))) {
				quit = true;
				continue;
			}
			if (contains(root, in.toLowerCase())) {
				remove(root, in.toLowerCase());
				System.out.println("Deleted "+ in);
			}
		}
	}
	/**
	 * Check if the tree contains a certain value
	 * @param node the root node
	 * @param value the value to search for 
	 * @return 
	 */
	private boolean contains(TreeNode<State> node, String value) {
		if (node == null) {
			return false;
		}

		if (value.compareTo(node.getValue().getName().toLowerCase()) < 0) {
			return contains(node.getLeft(), value);
		} 
		else if (value.compareTo(node.getValue().getName().toLowerCase()) > 0) {
			return contains(node.getRight(), value);
		} 
		else {
			return true; // Found the value
		}
	}
	/**
	 *	Remove value from Binary Tree
	 *	@param node			the root of the subtree
	 *	@param value		the value to remove from the subtree
	 *	@return				TreeNode that connects to parent
	 */
	public TreeNode<State> remove(TreeNode<State> node, String value) {
		if (node == null) {
			return null;
		}
	
		if (value.compareTo(node.getValue().getName().toLowerCase()) < 0) {
			node.setLeft(remove(node.getLeft(), value));
		} 
		else if (value.compareTo(node.getValue().getName().toLowerCase()) > 0) {
			node.setRight(remove(node.getRight(), value));
		} 
		else {
			if (node.getLeft() == null) {
				return node.getRight();
			} 
			else if (node.getRight() == null) {
				return node.getLeft();
			} 
			else {
				TreeNode<State> minNode = findMin(node.getRight());
				node.setRight(removeMin(node.getRight()));
				minNode.setLeft(node.getLeft());
				minNode.setRight(node.getRight());
				return minNode;
			}
		}
		return node;
	}
	
	/**
	 * used to find the minimum value of the tree
	 * used for replacing the removed value
	 * @param node
	 * @return
	 */
	private TreeNode<State> findMin(TreeNode<State> node) {
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}
	/**
	 * used to remove the minimum value
	 * @param node
	 * @return
	 */
	private TreeNode<State> removeMin(TreeNode<State> node) {
		if (node.getLeft() == null) {
			return node.getRight();
		}
		node.setLeft(removeMin(node.getLeft()));
		return node;
	}
	/**
	 * Finds the number of nodes starting at the root of the tree
	 * @return  the number of nodes in the tree
	 */
	public int size() {
		int size = sizeOfBinaryTree(root);
		return size;
	}
	/**
	 * gets the size of the binary tree by counting every node
	 * @param node
	 * @return size of the binary tree
	 */
	private int sizeOfBinaryTree(TreeNode<State> node) {
		if (node == null) {
			return 0;
		}
		else {
			return 1 + sizeOfBinaryTree(node.getLeft())+sizeOfBinaryTree(node.getRight());
		}
	}
	/**
	 * Clears the tree of all nodes
	 */
	public void clear() {
		root = null;
	}
	
	/**
	 * Prompts user for level of tree to print.
	 * The top level (root node) is level 0.
	 */
	public void printLevel() {
		boolean quit = false;
		while (!quit) {
			String strLevel = Prompt.getString("Enter level value to print (-1 to quit)-");
			if (strLevel.equals("-1")) {
				quit = true;
				return;
			}
			int level = Integer.parseInt(strLevel);
			findLevel(root, level, 0);
			System.out.println();
		}
		
	}
	/**
	 * finds the level that is equal to the target level
	 * @param node
	 * @param level
	 * @param counter
	 */
	private void findLevel(TreeNode<State> node, int level, int counter) {
		if (node == null) {
			return;
		}
		if (counter == level) {
			System.out.print(node.getValue().getName()+" ");
		}
		
		findLevel(node.getLeft(), level, counter+1);
		findLevel(node.getRight(), level, counter+1);
	}
	
	
	/**
	 * Prints the highest level of the tree (root is level 0),
	 * prints "Tree empty" if empty tree
	 */
	public void testDepth() {
		if (root != null)
		System.out.println("Depth of the tree = "+findLowestLevel(root));
		else
		System.out.println("Tree Empty");
		System.out.println();
	}
	/**
	 * finds the lowest level of the binary tree
	 * @param node
	 * @return increments the count of levels each time
	 */
	private int findLowestLevel(TreeNode<State> node) {
		if (node == null) {
			return -1;
		}
		else {
			int leftHeight = findLowestLevel(node.getLeft());
			int rightHeight = findLowestLevel(node.getRight());
			return Math.max(leftHeight, rightHeight)+1;
		}
	}

}