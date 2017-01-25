package com.sunilsahoo.algorithm;

import java.util.LinkedList;

/*
 * Time Complexity is O(logn) Space Complexity is O(1)
 */
public class AVLTree {
	private class QueueNode {
		AVLTreeNode treeNode;
		int level;

		QueueNode(AVLTreeNode treeNode, int level) {
			this.treeNode = treeNode;
			this.level = level;
		}
	}

	private class AVLTreeNode

	{

		int data;
		AVLTreeNode left;
		AVLTreeNode right;
		int height;

		AVLTreeNode(int data) {
			this.data = data;
			this.height = 1;
		}
	}

	AVLTreeNode root;

	AVLTree() {
		this.root = new AVLTreeNode(9);
	}

	AVLTree(int rootValue) {
		this.root = new AVLTreeNode(rootValue);
	}

	int getHeight(AVLTreeNode node) {
		if (node == null)
			return 0;
		return node.height;
	}

	void updateHeight(AVLTreeNode node) {
		if (node == null)
			return;
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}

	AVLTreeNode rotateRight(AVLTreeNode node) {
		if (node == null)
			return node;
		AVLTreeNode beta = node.left;
		AVLTreeNode t2 = beta.right;
		beta.right = node;
		node.left = t2;
		updateHeight(node);
		updateHeight(beta);
		return beta;
	}

	AVLTreeNode rotateLeft(AVLTreeNode node) {
		if (node == null)
			return node;
		AVLTreeNode beta = node.right;
		AVLTreeNode t2 = beta.left;
		beta.left = node;
		node.right = t2;
		updateHeight(node);
		updateHeight(beta);
		return beta;
	}

	int getBalance(AVLTreeNode node) {
		if (node == null) {
			return 0;
		}
		int balance;
		balance = getHeight(node.left) - getHeight(node.right);
		return balance;
	}

	int getMinValue(AVLTreeNode node) {
		if (node == null)
			return Integer.MIN_VALUE;
		if (node.left == null)
			return node.data;
		return getMinValue(node.left);
	}

	/*
	 * Time Complexity is O(logn) Space Complexity is O(1)
	 */
	private AVLTreeNode insert(AVLTreeNode node, int key) {
		if (node == null) {
			return new AVLTreeNode(key);
		}
		if (key < node.data) {
			node.left = insert(node.left, key);
		} else if (key > node.data) {
			node.right = insert(node.right, key);
		} else {
			return node;
		}
		updateHeight(node);
		int balance = getBalance(node);
		if (balance > 1) {
			if (key < node.left.data) {
				node = rotateRight(node);
			} else {
				node.left = rotateLeft(node.left);
				node = rotateRight(node);
			}
		} else if (balance < -1) {
			if (key > node.right.data) {
				node = rotateLeft(node);
			} else {
				node.right = rotateRight(node.right);
				node = rotateLeft(node);
			}
		}
		return node;
	}

	public void insert(int key) {
		root = insert(this.root, key);
		return;
	}

	public void printTreeLevelOrder() {
		if (root == null)
			return;
		LinkedList<QueueNode> queue = new LinkedList<>();
		queue.add(new QueueNode(root, 0));
		int maxLevelVisited = -1;
		while (!queue.isEmpty()) {
			QueueNode currentNode = queue.remove();
			if (currentNode.level > maxLevelVisited) {
				maxLevelVisited = currentNode.level;
				System.out.print("\nlevel-" + currentNode.level + " nodes: ");
			}
			System.out.print(" " + currentNode.treeNode.data);
			if (currentNode.treeNode.left != null) {
				queue.add(new QueueNode(currentNode.treeNode.left,
						currentNode.level + 1));
			}
			if (currentNode.treeNode.right != null) {
				queue.add(new QueueNode(currentNode.treeNode.right,
						currentNode.level + 1));
			}
		}
	}

	/*
	 * Time Complexity is O(logn) Space Complexity is O(1)
	 */
	private AVLTreeNode delete(AVLTreeNode node, int key) {
		if (node == null)
			return null;
		if (key < node.data) {
			node.left = delete(node.left, key);
		} else if (key > node.data) {
			node.right = delete(node.right, key);
		} else {
			if (node.left == null) {
				node = node.right;
			} else if (node.right == null) {
				node = node.left;
			} else {
				int inorderSuccessorValue = getMinValue(node.right);
				node.data = inorderSuccessorValue;
				node.right = delete(node.right, inorderSuccessorValue);
			}
		}
		if (node == null) {
			return null;
		}
		updateHeight(node);
		int balance = getBalance(node);
		if (balance > 1) {
			if (getBalance(node.left) >= 0) {
				node = rotateRight(node);
			} else {
				node.left = rotateLeft(node.left);
				node = rotateRight(node);
			}
		} else if (balance < -1) {
			if (getBalance(node.right) <= 0) {
				node = rotateLeft(node);
			} else {
				node.right = rotateRight(node.right);
				node = rotateLeft(node);
			}
		}
		return node;
	}

	public void delete(int key) {
		root = delete(this.root, key);
		return;
	}

	public static void main(String[] args) {
		AVLTree tree = new AVLTree(0);
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(6);
		tree.insert(5);
		tree.printTreeLevelOrder();
		tree.delete(1);
		System.out.print("\n\nAVL tree after deleting key with value 1");
		tree.printTreeLevelOrder();
	}
}
