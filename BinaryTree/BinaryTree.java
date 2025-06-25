import java.util.ArrayList;
/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	
 *	@author	Ryan Shen
 *	@since	5/13/24
 */
public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root, cur;		// the root of the tree=
	private ArrayList<TreeNode<E>> inOrderValues;
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
										// used by printTree()
	
	/**	constructor for BinaryTree */
	public BinaryTree() { 
		inOrderValues = new ArrayList<TreeNode<E>>();
		cur = root;
	}
	
	/**	Field accessors and modifiers */
	
	/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public void add(E value) {
	 	TreeNode<E> newPlacer = new TreeNode<E>(value);
		
		if (root == null) {
			root = newPlacer;
			cur = root;
		}
		else {
			
			TreeNode<E> temp = cur;
			boolean right = false;
			if (value.compareTo(cur.getValue()) > 0) {
				cur = cur.getRight();
				right = true;
			}
			else {
				cur = cur.getLeft();
				right = false;
			}
			if (cur == null) {
				if (right)
				temp.setRight(newPlacer);
				else
				temp.setLeft(newPlacer);
				cur = root;
			}
			else {
				add(value);
				cur = root;
			}
		}
		/*
		TreeNode<E> newPlacer = new TreeNode<E>(value);
		if (root == null) {
			root = newPlacer;
		}
		else {
			TreeNode<E> cur = root;
			TreeNode<E> temp = cur;
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
		}*/
	}
	/**
	 *	Print Binary Tree Inorder
	 */
	public void printInorder() {
		if (cur != null) {
			TreeNode<E> temp = new TreeNode<E>(cur.getValue(), cur.getLeft(), cur.getRight());
			if (cur.getLeft() != null) {
				cur = cur.getLeft();
				printInorder();
			}
			System.out.print(temp.getValue() + " ");
			cur = temp;
			if (cur.getRight() != null) {
				cur = cur.getRight();
				printInorder();
			}
		}
		cur = root;
	}
	
	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() { 
		if (cur != null) {
			TreeNode<E> temp = new TreeNode<E>(cur.getValue(), cur.getLeft(), cur.getRight());
			System.out.print(temp.getValue() + " ");
			if (cur.getLeft() != null) {
				cur = cur.getLeft();
				printPreorder();
			}
			
			cur = temp;
			if (cur.getRight() != null) {
				cur = cur.getRight();
				printPreorder();
			}
		}
		cur = root;
	}
	
	/**
	 *	Print Binary Tree Postorder
	 */
	public void printPostorder() {
		printPostorder(root);
	}
	private void printPostorder(TreeNode<E> node) {
		if (node == null) {
			return;
		}
		printPostorder(node.getLeft());
	
		printPostorder(node.getRight());
	
		System.out.print(node.getValue() + " ");
	}
		
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree<E> makeBalancedTree() {
        BinaryTree<E> balancedTree = new BinaryTree<>();
        putInOrder(root);
        balancedTree.root = buildBalancedTree(0, inOrderValues.size() - 1);
        return balancedTree;
    }
	/**
	 * puts the list in order 
	 * @param node
	 */
    private void putInOrder(TreeNode<E> node) {
        if (node == null)
            return;
        putInOrder(node.getLeft());
        inOrderValues.add(node);
        putInOrder(node.getRight());
    }
	/**
	 * builds the balanced tree using recursion
	 * @param lower	the lower value 
	 * @param upper	the upper value
	 * @return the node to set left or right
	 */
    private TreeNode<E> buildBalancedTree(int lower, int upper) {
        if (lower > upper)
            return null;
        int mid = (lower + upper) / 2;
        TreeNode<E> node = inOrderValues.get(mid);
        node.setLeft(buildBalancedTree(lower, mid - 1));
        node.setRight(buildBalancedTree(mid + 1, upper));
        return node;
    }
	
	/**
	 *	Remove value from Binary Tree
	 *	@param value		the value to remove from the tree
	 *	Precondition: value exists in the tree
	 */
	public void remove(E value) {
		root = remove(root, value);
	}
	/**
	 *	Remove value from Binary Tree
	 *	@param node			the root of the subtree
	 *	@param value		the value to remove from the subtree
	 *	@return				TreeNode that connects to parent
	 */
	public TreeNode<E> remove(TreeNode<E> node, E value) {
		if (node == null) {
			return null;
		}
	
		if (value.compareTo(node.getValue()) < 0) {
			node.setLeft(remove(node.getLeft(), value));
		} 
		else if (value.compareTo(node.getValue()) > 0) {
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
				TreeNode<E> minNode = findMin(node.getRight());
				node.setRight(removeMin(node.getRight()));
				minNode.setLeft(node.getLeft());
				minNode.setRight(node.getRight());
				return minNode;
			}
		}
		return node;
	}
	
	
	private TreeNode<E> findMin(TreeNode<E> node) {
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}
	private TreeNode<E> removeMin(TreeNode<E> node) {
		if (node.getLeft() == null) {
			return node.getRight();
		}
		node.setLeft(removeMin(node.getLeft()));
		return node;
	}
	/*******************************************************************************/	
	/********************************* Utilities ***********************************/	
	/*******************************************************************************/	
	/**
	 *	Print binary tree
	 *	@param root		root node of binary tree
	 *
	 *	Prints in vertical order, top of output is right-side of tree,
	 *			bottom is left side of tree,
	 *			left side of output is root, right side is deepest leaf
	 *	Example Integer tree:
	 *			  11
	 *			/	 \
	 *		  /		   \
	 *		5			20
	 *				  /	  \
	 *				14	   32
	 *
	 *	would be output as:
	 *
	 *				 32
	 *			20
	 *				 14
	 *		11
	 *			5
	 ***********************************************************************/
	public void printTree() {
		printLevel(root, 0);
	}
	
	/**
	 *	Recursive node printing method
	 *	Prints reverse order: right subtree, node, left subtree
	 *	Prints the node spaced to the right by level number
	 *	@param node		root of subtree
	 *	@param level	level down from root (root level = 0)
	 */
	private void printLevel(TreeNode<E> node, int level) {
		if (node == null) return;
		// print right subtree
		printLevel(node.getRight(), level + 1);
		// print node: print spaces for level, then print value in node
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue());
		// print left subtree
		printLevel(node.getLeft(), level + 1);
	}
	
	
}