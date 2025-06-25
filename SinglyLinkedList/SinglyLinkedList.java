import java.util.NoSuchElementException;

/**
 *	SinglyLinkedList - Creates a singly linked list
 *
 *	@author	Ryan Shen
 *	@since	4/29/24
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() {}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {
		head = oldList.head;
		ListNode<E> cur = head;
		while (cur != null) {
			cur = cur.getNext();
			
		}
	}
	
	/**	Clears the list of elements */
	public void clear() {
		head = null;
		tail = null;
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		if (head == null) {
			head = new ListNode<E>(obj);
			tail = head;
		}
		else {
			ListNode<E> bob = new ListNode<E>(obj);
			tail.setNext(bob);
			ListNode<E> joe = bob;
			bob = tail;
			tail = joe;
		}
		return true;
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		if(index == 0) {
			head = new ListNode<E>(obj,head);
			return true;
		}
		if(index < 0 || index > size()) {
			throw new NoSuchElementException();
		}
		ListNode<E> node = get(index-1);
		node.setNext(new ListNode<E>(obj,node.getNext()));
		return true;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		ListNode<E> cur = head;
		int i =0;
		while (cur != tail) {
			i++;
			cur = cur.getNext();
		}
		return i+1;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		ListNode<E> cur = head;
		for (int i = 0;i<index;i++) {
			cur = cur.getNext();
			if (cur == null)
			throw new NoSuchElementException();
		}
		return cur;
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		ListNode<E> cur = head;
		for (int i = 0;i<index;i++) {
			cur = cur.getNext();
			if (cur == null)
			throw new NoSuchElementException();
		}
		E bob = cur.getValue();
		cur.setValue(obj);
		return bob;
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		ListNode<E> cur = head;
		for (int i = 0;i<index-1;i++) {
			cur = cur.getNext();
			if (cur == null)
			throw new NoSuchElementException();
		}
		if (cur.getNext().getNext() == null) {
			ListNode<E> bob = cur.getNext();
			cur.setNext(null);
			tail = cur;
			return bob.getValue();
		}
		cur.setNext(cur.getNext().getNext());
		return cur.getNext().getValue();
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		return (head == null);
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		ListNode<E> cur = head;
		while (cur != null) {
			if (cur.getValue().equals(object))
			return true;
			cur = cur.getNext();
		}
		return false;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		ListNode<E> cur = head;
		int index = 0;
		while (cur != null) {
			if (cur.getValue().equals(element))
			return index;
			cur = cur.getNext();
			index++;
		}
		return -1;
	}
	
	/**	Prints the list of elements */
	public void printList()
	{
		ListNode<E> ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}
	

}

	