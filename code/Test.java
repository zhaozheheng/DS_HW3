package zzh;

class AVLNode {	//build the node class
	AVLNode left, right;
	int key;
	int height;
	
	public AVLNode() {	//construct node without a value
		left = null;
		right = null;
		key = 0;
		height = 0;
	}
	
	public AVLNode(int val) {	//construct node with a value
		left = null;
		right = null;
		key = val;
		height = 0;
	}
}
class AVLTree {	//construct the AVL tree class
	private AVLNode root;
	
	public AVLTree() {	//initial the tree as null
		root = null;
	}
	
	public void insert(int val) {	//public insert operation that can be used by other class
		root = insert(root, val);
	}
	
	private int height(AVLNode node) {	//get the height of the node
		return node == null ? -1 : node.height;
	}
	
	private int max(int a, int b) {	//get the max value of 2 numbers
		return a > b ? a : b;
	}
	
	private AVLNode insert(AVLNode node, int val) {	//insert operation that is private, and it can only be used by the AVLTree class
		// TODO Auto-generated method stub
		if (node == null) {	//if the root is null, create a new root, and make its value equal to val
			node = new AVLNode(val);
		}
		else if (val < node.key) {	//check left or right position of the new node
			node.left = insert(node.left, val);	//if is less than root, add to the left child position
			if (height(node.left) - height(node.right) == 2) {	//check the difference of heights of 2 subtrees, if balanced, do nothing; if unbalanced, do rotations
				if (val < node.left.key) {	//no zigzag
					node = llRotation(node);	//just rotate with left child
				}
				else {	//zigzag
					node = rlRotation(node);	//rotate right, then left
				}
			}
		}
		else if (val > node.key) {
			node.right = insert(node.right, val);
			if (height(node.right) - height(node.left) == 2) {	//check the difference of heights of 2 subtrees, if balanced, do nothing; if unbalanced, do rotations
				if (val > node.right.key) {	//no zigzag
					node = rrRotation(node);	//just rotate with right child
				}
				else {	//zigzag
					node = lrRotation(node);	//rotate left, then right
				}
			}
		}
		else
			;
		node.height = max(height(node.left), height(node.right)) + 1;	//update the new height
		return node;
	}	
	
	private void preOrder(AVLNode node) {	//preOrder traverse
		if (node != null) {
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	public void preOrder() {
		preOrder(root);
	}
	
	private void inOrder(AVLNode node) {	//inOrder traverse
		if (node != null) {
			inOrder(node.left);
			System.out.print(node.key + " ");
			inOrder(node.right);
		}
	}
	
	public void inOrder() {
		inOrder(root);
	}
	
	private void postOrder(AVLNode node) {	//postOrder traverse
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.key + " ");
		}
	}
	
	public void postOrder() {
		postOrder(root);
	}
	
	private AVLNode llRotation(AVLNode node) {	//rotate tree with left child
		AVLNode tmp;
		
		tmp = node.left;
		node.left = tmp.right;
		tmp.right = node;
		//update the height of 2 subtrees
		node.height = max(height(node.left), height(node.right)) + 1;
		tmp.height = max(height(tmp.left), node.height) + 1;
		
		return tmp;
	}
	
	private AVLNode rrRotation(AVLNode node) {	//rotate tree with right child
		AVLNode tmp;
		
		tmp = node.right;
		node.right = tmp.left;
		tmp.left = node;
		//update the height of 2 subtrees
		node.height = max(height(node.left), height(node.right)) + 1;
		tmp.height = max(height(tmp.right), node.height) + 1;
		
		return tmp;
	}
	
	private AVLNode rlRotation(AVLNode node) {
		node.left = rrRotation(node.left);	//first, rotate with right child
		
		return llRotation(node);	//then, rotate with left child
	}
	
	private AVLNode lrRotation(AVLNode node) {
		node.right = llRotation(node.right);	//first, rotate with left child
		
		return rrRotation(node);	//then rotate with right child
	}
	
}

public class Test {
	
	private static int arr[] = {40, 20, 10, 15, 13, 60, 80, 100, 90, 120, 85, 12, 14, 11, 4, 1, 3, 2};	//node value provided by the description of this assignment
	
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		
		for (int i = 0; i < arr.length; i++) {	//build the AVL tree
			tree.insert(arr[i]);
		}
		
		System.out.println("inorder traverse:	");	//output the inOrder traverse
		tree.inOrder();
	}
}
