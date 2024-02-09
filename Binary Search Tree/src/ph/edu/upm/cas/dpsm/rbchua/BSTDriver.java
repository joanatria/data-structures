package ph.edu.upm.cas.dpsm.rbchua;

public class BSTDriver {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(21);
		bst.insert(10);
		bst.insert(40);
		bst.insert(42);
		bst.insert(31);
		bst.insert(22);
		bst.insert(35);
		
		System.out.println("Searching for 31 " + bst.search(31));
		System.out.println("Minimum key is " + bst.minimum());
		System.out.println("Maximum key is " + bst.maximum());
		System.out.println("Successor of 10 is " + bst.successor(10));
		System.out.println("Predecessor of 10 is " + bst.predecessor(10));
		System.out.println("Successor of 22 is " + bst.successor(22));
		System.out.println("Predecessor of 22 is " + bst.predecessor(22));
		System.out.println("Preorder: " + bst.preorder());
		System.out.println("Inorder: " + bst.inorder());
		System.out.println("Postorder: " + bst.postorder());
		System.out.println("Level order: " + bst.levelorder());
		
		bst.delete(31);
		bst.delete(10);
		bst.delete(35);
		//bst.delete(8);
		System.out.println("Searching for 31 " + bst.search(31));
		System.out.println("Searching for 10 " + bst.search(10));
	}

}
