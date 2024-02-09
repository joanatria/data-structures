/**
 * Implementation of the Stack interface using 
 * linked list.
 * 
 * @author Richard Bryann Chua
 * 
 * Adapted from the book 
 * "Data Structures and Algorithms in Java"
 * by Michael Goodrich and Robert Tamassia
 */
public class LinkedStack implements Stack {
	private Node top;
	private int size;
	
	public LinkedStack() {
		top = null;
		size = 0;
	}

	/* (non-Javadoc)
	 * @see Stack#isEmpty()
	 */
	@Override
	public boolean isEmpty() {		
		return (top == null);
	}

	/* (non-Javadoc)
	 * @see Stack#pop()
	 */
	@Override
	public Object pop() throws StackEmptyException {
		if (isEmpty())
			throw new StackEmptyException("Stack is empty!");
		Object temp = top.getElement();
		top = top.getNext();
		size--;
		return temp;
	}

	/* (non-Javadoc)
	 * @see Stack#push(java.lang.Object)
	 */
	@Override
	public void push(Object element) {
		Node v = new Node(element, top);
		top = v;
		size++;
	}

	/* (non-Javadoc)
	 * @see Stack#size()
	 */
	@Override
	public int size() {		
		return size;
	}

	/* (non-Javadoc)
	 * @see Stack#top()
	 */
	@Override
	public Object top() throws StackEmptyException {
		if (isEmpty())
			throw new StackEmptyException("Stack is empty!");
		
		return top.getElement();
	}

}
