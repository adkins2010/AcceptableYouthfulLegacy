package michael.adkins.legacy.valid.bst;
import java.util.Stack;

/**
 * @author mike.adkins
 *
 */
public class ValidBinarySearchTree {
	static class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			super();
			this.value = value;
		}

		public Node(int value, ValidBinarySearchTree.Node left, ValidBinarySearchTree.Node right) {
			super();
			this.value = value;
			this.left = left;
			this.right = right;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
	}

	public static boolean isValidBST1(Node root) {
		return isValidBSTRecursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public static boolean isValidBST2(Node root) {
		return isValidBSTIterative(root);
	}

	public static boolean isValidBSTRecursive(Node n, int low, int high) {
		if (n == null) {
			return true;
		}
		int val = n.getValue();
		return (val > low && val < high) && isValidBSTRecursive(n.getLeft(), low, n.getValue())
				&& isValidBSTRecursive(n.getRight(), n.getValue(), high);
	}

	public static boolean isValidBSTIterative(Node root) {
		Stack<Node> leftST = new Stack<>();
		Node prev = null;
		while (!leftST.isEmpty() || root != null) {
			// traverses left
			while (root != null) {
				leftST.add(root);
				root = root.getLeft();
			}
			root = leftST.pop();
			if (prev != null && root.getValue() <= prev.getValue()) {
				return false;
			}
			prev = root;
			root = root.getRight();
		}
		return true;
	}
	
	public static void main(String[] args) {
		Node t1 = new Node(5, new Node(4), new Node(7));
		Node t2 = new Node(5, new Node(4), new Node(7, new Node(2), null));
		Node t3 = new Node(9, new Node(6, new Node(4, new Node(3), new Node(5)), new Node(7, null, new Node(8))), new Node(10, null, new Node(11)));
		
		if(!isValidBSTRecursive(t1, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
			System.err.println("t1 failed.");
			System.exit(-1);
		} else {
			System.out.println("t1 is valid");
		}
		if(!isValidBSTRecursive(t2, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
			System.out.println("t2 invalid.");
		} else {
			System.err.println("t2 failed.");
			System.exit(-1);
		}
		if(!isValidBSTRecursive(t3, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
			System.err.println("t3 failed.");
			System.exit(-1);
		} else {
			System.out.println("t3 is valid");
		}
		
		if(!isValidBSTIterative(t1)) {
			System.err.println("t1 failed.");
			System.exit(-1);
		} else {
			System.out.println("t1 is valid");
		}
		if(!isValidBSTIterative(t2)) {
			System.out.println("t2 invalid.");
		} else {
			System.err.println("t2 failed.");
			System.exit(-1);
		}
		if(!isValidBSTIterative(t3)) {
			System.err.println("t3 failed.");
			System.exit(-1);
		} else {
			System.out.println("t3 is valid");
		}
	}
}
