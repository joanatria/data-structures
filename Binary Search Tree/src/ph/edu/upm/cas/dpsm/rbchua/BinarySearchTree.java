package ph.edu.upm.cas.dpsm.rbchua;

import java.util.*;
import java.util.concurrent.*;
/**
 * An implementation of the binary search tree (BST).
 * 
 * @author Richard Bryann Chua
 *
 * @param <E> Data type to be stored in the binary search tree
 */
public class BinarySearchTree<E extends Comparable<E>> {
	private BSTNode<E> root;
	
	/**
	 * Constructs an empty binary search tree
	 */
	public BinarySearchTree() {
		root = null;
	} // end constructor
	
	/**
	 * Constructs a binary search tree with a predefined root
	 * 
	 * @param root predefined root of the binary search tree
	 */
	public BinarySearchTree(BSTNode<E> root) {
		this.root = root;
	}
	
	/**
	 * Search for the node that contains <code>key</code>
	 * 
	 * @param key key to be searched in the BST
	 * @return node containing <code>key</code>. If the BST does not contain
	 * <code>key</code>, <code>null</code> is returned.
	 */
	public BSTNode<E> search(E key) {
		BSTNode<E> currentNode = root;
		while ((currentNode != null) && (currentNode.getKey().compareTo(key) != 0)) {
			if (key.compareTo(currentNode.getKey()) < 0) {
				currentNode = currentNode.getLeftChild();
			} // end if
			else {
				currentNode = currentNode.getRightChild();
			} // end else
		} // end while
		
		return currentNode;
	} // end search
	
	/**
	 * Return the node that contains the minimum key in the binary search tree
	 * 
	 * @return node that contains the minimum key
	 */
	public BSTNode<E> minimum() throws EmptyTreeException {
		BSTNode<E> b = root;
		if (b == null) {
			throw new EmptyTreeException("Binary search tree is empty");
		} // end if 
		
		while (b.getLeftChild() != null) {
			b = b.getLeftChild();
		} // end while
		return b;
	} // end minimum
	
	/**
	 * Return the node that contains the maximum key in the binary search tree
	 * 
	 * @return node that contains the maximum key
	 */
	public BSTNode<E> maximum() throws EmptyTreeException{
		BSTNode<E> b = root;
		if (b == null) {
			throw new EmptyTreeException("Binary search tree is empty");
		} // end if
		
		while (b.getRightChild() != null) {
			b = b.getRightChild();
		} // end while
		return b;
	} // end minimum
	
	/**
	 * Find the node that is a successor of the node that contains key
	 * 
	 * @param key successor of this <code>key</code> is to be obtained
	 * @return successor of <code>key</code>
	 */
	public BSTNode<E> successor(E key) {
		BSTNode<E> x = search(key);
		
		if (x.getRightChild() != null) {
			BinarySearchTree<E> rightSubtree = new BinarySearchTree<E>(x.getRightChild());
			return rightSubtree.minimum();
		} // end if
		
		BSTNode<E> y = x.getParent();
		while((y != null) && (x == y.getRightChild())) {
			x = y;
			y = y.getParent();
		} // end while
		return y;
	} // end successor
	
	/**
	 * Find the node that is a predecessor of the node that contains key
	 * 
	 * @param key predecessor of this <code>key</code> is to be obtained
	 * @return predecessor of <code>key</code>
	 */
	public BSTNode<E> predecessor(E key) {
		BSTNode<E> x = search(key);
		
		if (x.getLeftChild() != null) {
			BinarySearchTree<E> leftSubtree = new BinarySearchTree<E>(x.getLeftChild());
			return leftSubtree.maximum();
		} // end if
		
		BSTNode<E> y = x.getParent();
		while((y != null) && (x == y.getLeftChild())) {
			x = y;
			y = y.getParent();
		} // end while
		return y;
	} // end predecessor
	
	/**
	 * Inserts a key into the binary search tree
	 * 
	 * @param key the new key that is to be inserted into the binary search tree
	 */
	public void insert(E key) {
		BSTNode<E> y = null;
		BSTNode<E> x = root;
		BSTNode<E> z = new BSTNode<E>(key);
		while (x != null) {
			y = x;
			if (key.compareTo(x.getKey()) < 0) {
				x = x.getLeftChild();
			} // end if
			else {
				x = x.getRightChild();
			} // end else
		} // end while
		z.setParent(y);
		if (y == null) {
			root = z;
		} // end if
		else if (key.compareTo(y.getKey()) < 0) {
			y.setLeftChild(z);
		} // end else if
		else {
			y.setRightChild(z);
		} // end else
	} // end insert
	
	private void transplant(BSTNode<E> u, BSTNode<E> v) {
		if(u.getParent() == null) {
			root = v;
		} // end if
		else if (u == u.getParent().getLeftChild()) {
			u.getParent().setLeftChild(v);
		} // end else if
		else {
			u.getParent().setRightChild(v);
		} // end else
		
		if (v != null) {
			v.setParent(u.getParent());
		} // end if		
	} // end transplant
	
	/**
	 * Deletes <code>key</code> from the binary search tree
	 * 
	 * @param key key the is to be deleted from the binary search tree 
	 */
	public void delete(E key) {
		BSTNode<E> z = search(key);
		if (z == null) {
			throw new InvalidKeyException("Key not found in the binary search tree.");
		} // end if
		
		if (z.getLeftChild() == null) {
			transplant(z, z.getRightChild());
		} // end if
		else if (z.getRightChild() == null) {
			transplant(z, z.getLeftChild());
		} // end else if
		else {
			BinarySearchTree<E> rightSubtree = new BinarySearchTree<E>(z.getRightChild());
			BSTNode<E> y = rightSubtree.minimum();
			if (y.getParent() != z) {
				transplant(y, y.getRightChild());
				y.setRightChild(z.getRightChild());
				y.getRightChild().setParent(y);
			} // end if
			transplant(z, y);
			y.setLeftChild(z.getLeftChild());
			y.getLeftChild().setParent(y);
		} // end else
	} // end delete
	
	/**
	 * Returns a preorder representation of the binary search tree
	 * 
	 * @return preorder string of the binary search tree
	 */
	public String preorder() {
		StringBuilder preorderTraversal = new StringBuilder();
		preorderSupport(root, preorderTraversal);
		return preorderTraversal.toString();
	} // end preorder
	
	private void preorderSupport(BSTNode<E> root, StringBuilder preorder) {
		if (root != null) {
			preorder.append(root + " ");
			preorderSupport(root.getLeftChild(), preorder);
			preorderSupport(root.getRightChild(), preorder);
		} // end if		
	} // end preorderSupport
	
	/**
	 * Returns an inorder representation of the binary search tree
	 * 
	 * @return inorder string of the binary search tree
	 */
	//Complete this method.
	public String inorder() {
		StringBuilder inorderTraversal = new StringBuilder();
		inorderSupport(root, inorderTraversal);
		return inorderTraversal.toString();
	} // end inorder
	
	private void inorderSupport(BSTNode<E> root, StringBuilder inorder) {
		if (root != null) {
			inorderSupport(root.getLeftChild(), inorder);
			inorder.append(root + " ");
			inorderSupport(root.getRightChild(), inorder);
		} // end if		
	} // end inorderSupport

	/**
	 * Returns a postorder representation of the binary search tree
	 * 
	 * @return postorder string of the binary search tree
	 */
	//Complete this method
	public String postorder() {
		StringBuilder postorderTraversal = new StringBuilder();
		postorderSupport(root, postorderTraversal);
		return postorderTraversal.toString();
 	} // end postorder

	private void postorderSupport(BSTNode<E> root, StringBuilder postorder) {
		if (root != null) {
			postorderSupport(root.getLeftChild(), postorder);
			postorderSupport(root.getRightChild(), postorder);
			postorder.append(root + " ");
		} // end if		
	} // end postorderSupport
	
	/**
	 * Returns a level order representation of the binary search tree
	 * 
	 * @return level order string of the binary search tree
	 */
	//Complete this method
	public String levelorder() {
		StringBuilder levelorderTraversal = new StringBuilder();
		int h = height(root);
        for (int i = 1; i <= h; i++)
            levelorderSupport(root, i, levelorderTraversal);
		return levelorderTraversal.toString();
 	} // end levelorder
	
	int height(BSTNode<E> root) {
		if (root == null)
			return 0;
		else {
			int lheight = height(root.getLeftChild());
	        int rheight = height(root.getRightChild());
	        if (lheight > rheight)
	        	return (lheight + 1);
	        else
	        	return (rheight + 1);
	        }
	}
	private void levelorderSupport(BSTNode<E> root, int level, StringBuilder levelorder) {
		if (root == null)
			return;
		if (level == 1)
			levelorder.append(root + " ");
		else if (level > 1) {
			levelorderSupport(root.getLeftChild(), level - 1, levelorder);
			levelorderSupport(root.getRightChild(), level - 1, levelorder);
			}
	}
} // end class