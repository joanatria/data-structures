package ph.edu.upm.cas.dpsm.rbchua;
/**
 * Node for an AVL tree
 * 
 * @author Richard Bryann Chua
 *
 * @param <E> Data type that is to be stored in the AVL node
 */
public class AVLNode<E extends Comparable<E>> extends BSTNode<E> {
	private int bf;			// balance factor of the AVL node
	
	public AVLNode() {
		super();
		bf = 0;
	}
	
	public AVLNode(E key) {
		super(key);
		bf = 0;
	}
	
	public AVLNode(E key, AVLNode<E> parent, AVLNode<E> lChild, AVLNode<E> rChild) {
		super(key, parent, lChild, rChild);
		bf = 0;
	}
	
	/**
	 * Sets the balance factor of the AVL node
	 * 
	 * @param bf new balance factor of the AVL node
	 */
	public void setBalanceFactor(int bf) {
		this.bf = bf;
	} // end setBalanceFactor
	
	/**
	 * Returns the balance factor of the AVL node
	 * 
	 * @return balance factor of the AVL node
	 */
	public int getBalanceFactor() {
		return bf;
	} // end getBalanceFactor
	
	@Override
	public String toString() {
		return "(key = " + getKey() + " BF = " + getBalanceFactor() + ")";
	}
} // end class
