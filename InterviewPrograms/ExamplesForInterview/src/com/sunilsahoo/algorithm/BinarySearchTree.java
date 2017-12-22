package com.sunilsahoo.algorithm;

public class BinarySearchTree {

	Node rootNode;

	private void addNode(int key) {
		Node node = new Node(key);
		if (rootNode == null) {
			rootNode = node;
		} else {
			Node focusNode = rootNode;
			Node parent;
			while (true) {
				parent = focusNode;
				if (key > focusNode.key) {

					focusNode = focusNode.rightChild;
					if (focusNode == null) {
						parent.rightChild = node;
						return;
					}
				} else {
					focusNode = focusNode.leftChild;
					if (focusNode == null) {
						parent.leftChild = node;
						return;
					}
				}
			}
		}
	}

	private Node insert(Node node, int key) {
		if (node == null) {
			return new Node(key);
		}
		if (key < node.key) {
			node.leftChild = insert(node.leftChild, key);
		} else if (key > node.key) {
			node.rightChild = insert(node.rightChild, key);
		} else {
			return node;
		}
		return node;
	}

	/*
	 * If insert(currentNode, key) is called then - 1. If currentNode is null,
	 * then either this is an empty tree or we have reached the correct position
	 * in the tree for this key. We create a new node with this key and make
	 * currentNode reference point to this new node. 2. If the value of key is
	 * less than currentNode value, then we know that correct position for this
	 * key is in the left sub-tree of currentNode. We also know that the left
	 * sub-tree would be modified after inserting this key. Therefore we make a
	 * recursive call as - currentNode.left = insert(currentNode.left, key) 3.
	 * If the value of key is greater than currentNode value, then we know that
	 * correct position for this key is in the right sub-tree of currentNode. We
	 * also know that the right sub-tree would be modified after inserting this
	 * key. Therefore we make a recursive call as - currentNode.right =
	 * insert(currentNode.right, key) 4. Insertion of key would be completed in
	 * above steps. In this step, we return reference of currentNode to the
	 * calling function to make it visible the changes that have occurred in the
	 * tree(due to insertion of new key) rooted at currentNode. Note how this
	 * algorithm handles insertion of duplicates keys. If the key value matches
	 * the currentNode value, then this algorithm does no action and simply
	 * returns reference of currentNode thereby avoiding insertion of duplicate
	 * keys.
	 * 
	 * Time Complexity is O(logn)(Average case) Space Complexity is O(1)
	 */
	public void insert(int key) {
		rootNode = insert(this.rootNode, key);
		return;
	}

	/*
	 * To delete a node/key from the given binary search tree, we need to
	 * consider two cases. First case - if the node to be deleted is a leaf node
	 * or has only one child. Second case - if the node to be deleted has both
	 * children. First case: a. Leaf node deletion: As shown below, to delete
	 * node 4 we simply need to make left child of node 5 as null by returning
	 * null to the calling function when current node is 4. Calling function
	 * would have current node as 5. b. Deletion of node with only one child: As
	 * shown below, to delete node 5 which has only one child(node 4), we need
	 * to return the reference of the child node to the calling function when
	 * the current node is 5. In this example, calling function would have
	 * current node as 3 and to it we return reference to node 4, effectively
	 * deleting node 5. Second case: Deletion of node which has both children:
	 * To delete node 3 from the below tree, what we do is we find out the
	 * inorder successor of node 3 (which is node 4), copy its value to the node
	 * 3(which needs to be deleted) and then proceed to delete the node
	 * 4(inorder successor of node 4).
	 * 
	 * 
	 * Time Complexity is O(logn)(Average case) Space Complexity is O(h) h:
	 * height of BST
	 * 
	 */
	public void delete(int key) {
		rootNode = delete(this.rootNode, key);
		return;
	}

	private Node delete(Node node, int key) {
		if (node == null)
			return null;
		if (key < node.key) {
			node.leftChild = delete(node.leftChild, key);
		} else if (key > node.key) {
			node.rightChild = delete(node.rightChild, key);
		} else {
			if (node.leftChild == null) {
				node = node.rightChild;
			} else if (node.rightChild == null) {
				node = node.leftChild;
			} else {
				int inorderSuccessorValue = getMinValue(node.rightChild);
				node.key = inorderSuccessorValue;
				node.rightChild = delete(node.rightChild,
						inorderSuccessorValue);
			}
		}
		return node;
	}

	int getMinValue(Node node) {
		if (node == null)
			return Integer.MIN_VALUE;
		if (node.leftChild == null)
			return node.key;
		return getMinValue(node.leftChild);
	}

	/*
	 * In a BST, the root is greater than all nodes of the left sub tree. So
	 * root value forms an upper bound on left sub tree values. Similarly, since
	 * the root is less than all the nodes of the right sub tree, root value
	 * forms a lower bound on the right sub tree values. This can be used for
	 * checking if a binary tree is a binary search tree or not. 1. Initialize,
	 * low = MIN_VALUE, high = MAX_VALUE. 2. If root.data <= low || root.data >=
	 * high, return false. 3. Recursively check for left sub tree and right sub
	 * tree. a. For left sub tree, pass high as root.data because for a BST,
	 * root forms an upper bound for left sub tree node values. b. For right sub
	 * tree, pass low as root.data because for a BST, root forms a lower bound
	 * for right sub tree node values. Time complexity: O(n)
	 */
	public boolean isBinarySearchTree() {
		return isBinarySearchTree(this.rootNode, Integer.MIN_VALUE,
				Integer.MAX_VALUE);
	}

	private boolean isBinarySearchTree(Node root, int low, int high) {
		if (root == null) {
			return true;
		}
		if (root.key <= low || root.key >= high) {
			return false;
		}
		return isBinarySearchTree(root.leftChild, low, root.key)
				&& isBinarySearchTree(root.rightChild, root.key, high);
	}

	class Node {
		private int key;
		public Node leftChild, rightChild;

		public Node(int key) {
			this.key = key;
		}

		@Override
		public String toString() {
			// System.out.println(key+" L : "+leftChild+" R : "+rightChild);
			return key + " L : " + leftChild + " R : " + rightChild;
		}
	}

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.addNode(1);
		bst.addNode(5);
		bst.addNode(0);
		bst.addNode(2);
		bst.addNode(4);
		System.out.println(bst.rootNode);
		System.out.println(bst.rootNode.key);

		BinarySearchTree bst1 = new BinarySearchTree();
		bst1.insert(1);
		bst1.insert(5);
		bst1.insert(0);
		bst1.insert(2);
		bst1.insert(4);
		System.out.println(bst1.rootNode);
		System.out.println(bst1.rootNode.key);
		bst1.delete(1);
		System.out.println(bst1.rootNode);
		System.out.println(bst1.isBinarySearchTree());

	}
}
