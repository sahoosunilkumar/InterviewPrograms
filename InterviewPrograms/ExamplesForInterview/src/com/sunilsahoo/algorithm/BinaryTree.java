package com.sunilsahoo.algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * Time Complexity: O(n)
Let us prove it:

Complexity function T(n) — for all problem where tree traversal is involved — can be defined as:

T(n) = T(k) + T(n – k – 1) + c

Where k is the number of nodes on one side of root and n-k-1 on the other side.

Let’s do analysis of boundary conditions

Case 1: Skewed tree (One of the subtrees is empty and other subtree is non-empty )

k is 0 in this case.
T(n) = T(0) + T(n-1) + c
T(n) = 2T(0) + T(n-2) + 2c
T(n) = 3T(0) + T(n-3) + 3c
T(n) = 4T(0) + T(n-4) + 4c

…………………………………………
………………………………………….
T(n) = (n-1)T(0) + T(1) + (n-1)c
T(n) = nT(0) + (n)c

Value of T(0) will be some constant say d. (traversing a empty tree will take some constants time)

T(n) = n(c+d)
T(n) = (-)(n) (Theta of n)

Case 2: Both left and right subtrees have equal number of nodes.

T(n) = 2T(|_n/2_|) + c

This recursive function is in the standard form (T(n) = aT(n/b) + (-)(n) ) 
for master method http://en.wikipedia.org/wiki/Master_theorem. If we solve it by master method we get (-)(n)

Auxiliary Space : If we don’t consider size of stack for function calls then O(1) otherwise O(n).

Unlike linear data structures (Array, Linked List, Queues, Stacks, etc) 
which have only one logical way to traverse them, trees can be traversed in different ways. 
Following are the generally used ways for traversing trees.

Example Tree
Example Tree

Depth First Traversals:
(a) Inorder
(b) Preorder
(c) Postorder

Breadth First or Level Order Traversal
Please see this post for Breadth First Traversal.

Inorder Traversal:

Algorithm Inorder(tree)
   1. Traverse the left subtree, i.e., call Inorder(left-subtree)
   2. Visit the root.
   3. Traverse the right subtree, i.e., call Inorder(right-subtree)
Uses of Inorder
In case of binary search trees (BST), Inorder traversal gives nodes 
in non-decreasing order. To get nodes of BST in non-increasing order, 
a variation of Inorder traversal where Inorder itraversal s reversed, can be used.
Example: Inorder traversal for the above given figure is 4 2 5 1 3.

Preorder Traversal:

Algorithm Preorder(tree)
   1. Visit the root.
   2. Traverse the left subtree, i.e., call Preorder(left-subtree)
   3. Traverse the right subtree, i.e., call Preorder(right-subtree)
Uses of Preorder
Preorder traversal is used to create a copy of the tree. Preorder traversal 
is also used to get prefix expression on of an expression tree. 
Please see http://en.wikipedia.org/wiki/Polish_notation to know why 
prefix expressions are useful.
Example: Preorder traversal for the above given figure is 1 2 4 5 3.

Postorder Traversal:

Algorithm Postorder(tree)
   1. Traverse the left subtree, i.e., call Postorder(left-subtree)
   2. Traverse the right subtree, i.e., call Postorder(right-subtree)
   3. Visit the root.
Uses of Postorder
Postorder traversal is used to delete the tree. Please see the question 
for deletion of tree for details. Postorder traversal is also useful to get the 
postfix expression of an expression tree. 
Please see http://en.wikipedia.org/wiki/Reverse_Polish_notation to 
for the usage of postfix expression.

Example: Postorder traversal for the above given figure is 4 5 2 3 1.

DFS takes O(bh)O(bh) time and O(h)O(h) space

The recursive computation of disk space is emblematic of a postorder traversal, 
as we cannot effectively compute the total space used by a directory until 
after we know the space that is used by its children directories.

Use stack approach for tree traversal rather than recursion as recursion may 
cause stack overflow error in memory when data is very big
 */
public class BinaryTree {
	// Root of Binary Tree
	Node root;
	int maxLevel = -1;

	BinaryTree() {
		root = null;
	}

	/*
	 * Given a binary tree, print its nodes according to the "bottom-up"
	 * postorder traversal.
	 */
	void printPostorder(Node node) {
		if (node == null)
			return;

		// first recur on left subtree
		printPostorder(node.left);

		// then recur on right subtree
		printPostorder(node.right);

		// now deal with the node
		System.out.print(node.key + " ");
	}

	/* Given a binary tree, print its nodes in inorder */
	void printInorder(Node node) {
		if (node == null)
			return;

		/* first recur on left child */
		printInorder(node.left);

		/* then print the data of node */
		System.out.print(node.key + " ");

		/* now recur on right child */
		printInorder(node.right);
	}

	/* Given a binary tree, print its nodes in preorder */
	void printPreorder(Node node) {
		if (node == null)
			return;

		/* first print data of node */
		System.out.print(node.key + " ");

		/* then recur on left sutree */
		printPreorder(node.left);

		/* now recur on right subtree */
		printPreorder(node.right);
	}

	/* Given a binary tree, print its nodes in preorder */
	void printPreorderWoRecurssion(Node node) {
		System.out.println("\nPreorder wo recurssion");
		if (node == null)
			return;

		Stack<Node> stack = new Stack<>();
		while (true) {
			// root

			while (node != null) {
				System.out.print(node.key + " ");
				stack.push(node);
				node = node.left;
			}
			if (stack.isEmpty())
				break;
			node = stack.pop();
			node = node.right;
			// left
			// right
		}
	}

	void printInorderWoRecurssion(Node node) {
		if (node == null) {
			return;
		}

		Stack<Node> stack = new Stack<>();
		while (true) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			if (stack.isEmpty()) {
				break;
			}
			node = stack.pop();
			System.out.print(node.key + " ");
			node = node.right;
		}
	}

	void postorderWoRecurssion(Node root) {
		if (root == null)
			return;

		Stack<Node> leftStack = new Stack<Node>();
		Stack<Node> rightStack = new Stack<Node>();

		if (root.left != null)
			leftStack.push(root.left);
		if (root.right != null)
			rightStack.push(root.right);

		Stack<Integer> valueStack = new Stack<Integer>();
		Stack<Integer> rightvalueStack = new Stack<Integer>();
		// traverse the left sub-tree
		postorderIterativeHelper(leftStack, valueStack);
		// print the left sub-tree
		System.out.print(valueStack);

		// traverse the right sub-tree
		postorderIterativeHelper(rightStack, rightvalueStack);
		// print the left sub-tree
		System.out.print(rightvalueStack);

		// print the root node
		System.out.print(root.key);
	}

	private void postorderIterativeHelper(Stack<Node> stack,
			Stack<Integer> valueStack) {
		// traverse right sub-tree
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			valueStack.push(node.key);
			if (node.left != null)
				stack.push(node.left);
			if (node.right != null)
				stack.push(node.right);
		}
	}

	/**
	 * breadth first search
	 * 
	 * @param node
	 */
	void printBFS(Node node) {
		System.out.println("printBFS");
		if (node == null)
			return;

		List<Node> queue = new LinkedList<Node>();
		queue.add(node);
		System.out.print(node.key + " ");
		while (!queue.isEmpty()) {
			node = queue.remove(0);
			if (node.left != null) {
				queue.add(node.left);
				System.out.print(node.left.key + " ");
			}
			if (node.right != null) {
				queue.add(node.right);
				System.out.print(node.right.key + " ");
			}
		}

	}

	// Wrappers over above recursive functions
	void printPostorder() {
		printPostorder(root);
	}

	void printInorder() {
		printInorder(root);
	}

	void printPreorder() {
		printPreorder(root);
	}

	// Driver method
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.left.left = new Node(8);
		tree.root.left.right = new Node(5);
		tree.root.left.right.right = new Node(9);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);

		System.out.println("Preorder traversal of binary tree is ");
		tree.printPreorder();
		tree.printPreorderWoRecurssion(tree.root);

		System.out.println("\nInorder traversal of binary tree is ");
		tree.printInorder();
		System.out
				.println("\nInorder traversal WO recursion of binary tree is ");
		tree.printInorderWoRecurssion(tree.root);

		System.out.println("\nPostorder traversal of binary tree is ");
		tree.printPostorder();

		System.out.println(
				"\nPostorder traversal WO recursion of binary tree is ");
		tree.postorderWoRecurssion(tree.root);

		System.out.println("\n BFS");
		tree.printBFS(tree.root);

	}

	static Node prev = null;
}

/*
 * Class containing left and right child of current node and key value
 */
class Node {
	int key;
	Node left, right;
	boolean visited;

	public Node(int item) {
		key = item;
		left = right = null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + key;
	}
}

class EntryNode {
	public int level;
	public Node node;

	EntryNode(int level, Node node) {
		this.level = level;
		this.node = node;
	}

}
