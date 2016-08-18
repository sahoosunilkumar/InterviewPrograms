package com.sunilsahoo.algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

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

This recursive function is in the standard form (T(n) = aT(n/b) + (-)(n) ) for master method http://en.wikipedia.org/wiki/Master_theorem. If we solve it by master method we get (-)(n)

Auxiliary Space : If we don’t consider size of stack for function calls then O(1) otherwise O(n).

Unlike linear data structures (Array, Linked List, Queues, Stacks, etc) which have only one logical way to traverse them, trees can be traversed in different ways. Following are the generally used ways for traversing trees.

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

	/*
	 * Given a binary tree, print the values of nodes that would appear when the
	 * tree is viewed from bottom. Print the node values starting from left-most
	 * end. A node will be in the bottom-view if it is the bottommost node at
	 * its horizontal distance from the root. Horizontal distance of root from
	 * itself is 0. Horizontal distance of right child of root node is 1 and
	 * horizontal distance of left child of root node is -1. Horizontal distance
	 * of node 'n' from root = horizontal distance of its parent from root + 1,
	 * if node 'n' is right child of its parent Horizontal distance of node 'n'
	 * from root = horizontal distance of its parent from root - 1, if node 'n'
	 * is left child of its parent If more than one nodes are at the same
	 * horizontal distance and are the bottommost nodes for that horizontal
	 * distance, then you can choose to include either of the nodes in the
	 * bottom view.
	 * 
	 * Time Complexity is O(n) Space Complexity is O(n)
	 */
	void printBottomViewOfTree(Node node) {
		System.out.println("\nFinding BottomView Of Tree");
		LinkedList<EntryNode> queue = new LinkedList<EntryNode>();
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		queue.add(new EntryNode(0, node));
		while (true) {
			if (queue.isEmpty()) {
				break;
			}
			EntryNode entryNode = queue.remove(0);

			map.put(entryNode.level, entryNode.node.key);
			if (entryNode.node.left != null) {
				queue.add(new EntryNode(entryNode.level - 1,
						entryNode.node.left));
			}
			if (entryNode.node.right != null) {
				queue.add(new EntryNode(entryNode.level + 1,
						entryNode.node.right));
			}
		}

		System.out.println("Map : " + map);
		Set<Entry<Integer, Integer>> set = map.entrySet();
		Iterator<Entry<Integer, Integer>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, Integer> me = iterator.next();
			System.out.print(me.getValue() + " ");
		}

	}

	/*
	 * Given a binary tree, print the nodes of binary tree grouped together in
	 * vertical order. Vertical order of a node is defined using its horizontal
	 * distance from the root node. Horizontal distance of root from itself is
	 * 0. Horizontal distance of right child of root node is +1 and horizontal
	 * distance of left child of root node is -1. Horizontal distance of node
	 * 'n' from root = horizontal distance of its parent from root + 1, if node
	 * 'n' is right child of its parent. Horizontal distance of node 'n' from
	 * root = horizontal distance of its parent from root - 1, if node 'n' is
	 * left child of its parent. 1 23 4567 8 In the above tree, horizontal
	 * distance of node 1 is 0 horizontal distance of node 2 is -1 horizontal
	 * distance of node 3 is +1 horizontal distance of node 4 is -2 horizontal
	 * distance of node 5 is 0 horizontal distance of node 6 is 0 horizontal
	 * distance of node 7 is +2 horizontal distance of node 8 is +1 and
	 * therefore expected vertical order print for this tree is: 4 2 156 38 7
	 * Note that, the requirement is that vertical order should be printed
	 * starting from left-most end(node 2 cannot be printed before node 4) and
	 * in top down manner(node 8 cannot be printed before node 3 though both
	 * have same horizontal distance).
	 * 
	 */
	void printVerticalOrderOfTree(Node node) {
		System.out.println("\nFinding Vertical Order Of Tree");
		LinkedList<EntryNode> queue = new LinkedList<EntryNode>();
		Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		queue.add(new EntryNode(0, node));
		while (true) {
			if (queue.isEmpty()) {
				break;
			}
			EntryNode entryNode = queue.remove(0);

			ArrayList<Integer> list = map.get(entryNode.level);
			if (list == null) {
				list = new ArrayList<Integer>();
			}
			list.add(entryNode.node.key);
			map.put(entryNode.level, list);
			if (entryNode.node.left != null) {
				queue.add(new EntryNode(entryNode.level - 1,
						entryNode.node.left));
			}
			if (entryNode.node.right != null) {
				queue.add(new EntryNode(entryNode.level + 1,
						entryNode.node.right));
			}
		}

		System.out.println("Map : " + map);
		Set<Entry<Integer, ArrayList<Integer>>> set = map.entrySet();
		Iterator<Entry<Integer, ArrayList<Integer>>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, ArrayList<Integer>> me = iterator.next();
			System.out.print(me.getValue() + " ");
		}

	}

	/*
	 * Given a binary tree, print the values of nodes that would appear when the
	 * tree is viewed from top. Print the node values starting from left-most
	 * end. A node will be in the top-view if it is the topmost node at its
	 * horizontal distance from the root. Horizontal distance of root from
	 * itself is 0. Horizontal distance of right child of root node is +1 and
	 * horizontal distance of left child of root node is -1. Horizontal distance
	 * of node 'n' from root = horizontal distance of its parent from root + 1,
	 * if node 'n' is right child of its parent Horizontal distance of node 'n'
	 * from root = horizontal distance of its parent from root - 1, if node 'n'
	 * is left child of its parent If more than one nodes are at the same
	 * horizontal distance and are the topmost nodes for that horizontal
	 * distance, then you can choose to include either of the nodes in the top
	 * view. example - For the following tree: 1 23 4567 Horizontal distance of
	 * 1 = +0 Horizontal distance of 2 = -1 Horizontal distance of 3 = +1
	 * Horizontal distance of 4 = -2 Horizontal distance of 5 = +0 Horizontal
	 * distance of 6 = +0 Horizontal distance of 7 = +2 and the top view would
	 * be 4, 2, 1, 3, 7.
	 * 
	 * Time Complexity is O(n) Space Complexity is O(n)
	 * 
	 */
	void printTopViewOfTree(Node node) {
		System.out.println("\nFinding Top Of Tree");
		LinkedList<EntryNode> queue = new LinkedList<EntryNode>();
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		queue.add(new EntryNode(0, node));
		while (true) {
			if (queue.isEmpty()) {
				break;
			}
			EntryNode entryNode = queue.remove(0);

			if (!map.containsKey(entryNode.level)) {
				map.put(entryNode.level, entryNode.node.key);
			}
			if (entryNode.node.left != null) {
				queue.add(new EntryNode(entryNode.level - 1,
						entryNode.node.left));
			}
			if (entryNode.node.right != null) {
				queue.add(new EntryNode(entryNode.level + 1,
						entryNode.node.right));
			}
		}

		System.out.println("Map : " + map);
		Set<Entry<Integer, Integer>> set = map.entrySet();
		Iterator<Entry<Integer, Integer>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, Integer> me = iterator.next();
			System.out.print(me.getValue() + " ");
		}

	}

	private void printLeftViewOfTree(Node node) {
		System.out.println("Finding LeftView Of Tree using BFS");
		LinkedList<EntryNode> queue = new LinkedList<EntryNode>();
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		int level = 0;
		queue.add(new EntryNode(level, node));
		while (true) {
			if (queue.isEmpty()) {
				break;
			}
			EntryNode entryNode = queue.remove(0);

			if (!map.containsKey(entryNode.level)) {
				map.put(entryNode.level, entryNode.node.key);
			}
			if (entryNode.node.left != null) {
				queue.add(new EntryNode(entryNode.level + 1,
						entryNode.node.left));
			}
			if (entryNode.node.right != null) {
				queue.add(new EntryNode(entryNode.level + 1,
						entryNode.node.right));
			}
		}

		System.out.println("Map : " + map);
		Set<Entry<Integer, Integer>> set = map.entrySet();
		Iterator<Entry<Integer, Integer>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, Integer> me = iterator.next();
			System.out.print(me.getValue() + " ");
		}

	}

	/*
	 * If we do a level order traversal of tree such that the left most node of
	 * each level is visited before all other nodes in that level then all we
	 * need to do is to print out the first visited node at each level to print
	 * the left view of tree. This can be done by doing a level order traversal
	 * from left end to right end and keeping track of max-level seen so far to
	 * find out when the new level starts. As soon as we find out that the new
	 * level has started, we print out the current node that is being visited.
	 * The steps for this algorithm are - 1. Initialize maxLevelSeenSoFar to -1
	 * and call printLeftViewLevelOrder(currentNode = root) 2. In function
	 * printLeftViewLevelOrder if currentNode is null, do nothing and return. 3.
	 * Else, add tuple (node = currentNode, level = 0) to the 'queue'. 4. while
	 * 'queue' is not empty do - remove the first tuple(currentNode, level) from
	 * the queue. - if (level > maxLevelSeenSoFar) then we know that we are
	 * starting to traverse a new level and this is the first(and left most)
	 * node for this new level and therefore we print currentNode's value and
	 * update maxLevelSeenSoFar to level. - if left child of currentNode is not
	 * null then we add tuple (currentNode.left, level + 1) to 'queue'. - if
	 * right child of currentNode is not null then we add tuple
	 * (currentNode.right, level + 1) to 'queue'. Please note that we are adding
	 * left child of node to the 'queue' before right child to make sure that
	 * the left-most node at any level is visited before other nodes at that
	 * level. After execution of step #4, left view of tree would be printed.
	 * Notice that this algorithm takes O(n) extra space. With the following
	 * algorithm, we can save on extra space. This algorithm basically uses
	 * modified pre-order traversal. In this modified pre-order traversal we
	 * make sure that - a. The left-most node of any given level is visited
	 * before other nodes in that level. This can be easily achieved by visiting
	 * left sub-tree of node before right sub-tree. Basically, in this
	 * traversal, we visit the node first, then visit the left sub-tree and
	 * finally the right sub-tree(N-L-R). b. We print out the node value as soon
	 * as we encounter a node in the level that is greater than the maximum
	 * level seen so far and update maximum level seen so far to current level.
	 * The steps of this algorithm are as following - 1. Initialize
	 * maxLevelSeenSoFar to -1 and call printLeftView(currentNode = root, level
	 * = 0) 2. In function printLeftView(currentNode, level), a. If currentNode
	 * is null, then we do nothing and return. b. Else, if (level >
	 * maxLevelSeenSoFar) we print out the currentNode's value and update
	 * maxLevelSeenSoFar to level. c. Make a recursive call
	 * printLeftView(currentNode.left, level + 1) to make sure nodes in the left
	 * sub- tree are visited. d. Make a recursive call
	 * printLeftView(currentNode.right, level + 1) to make sure nodes in the
	 * right sub- tree are visited. Notice that while visiting nodes with
	 * recursive calls, printLeftView(currentNode.left, level + 1) is called
	 * before printLeftView(currentNode.right, level + 1) in order to make sure
	 * that for any node, left sub- tree of that node is visited before right
	 * sub-tree. This guarantees that at any level, the left-most node is
	 * visited before other nodes at that level. After execution of step #2,
	 * left view of tree would be printed. Please checkout code snippet and
	 * algorithm visualization section for more details of the algorithm. 2/11
	 * Time Complexity is O(n) Space Complexity is O(1)
	 */
	private void printLeftViewOfTree(Node node, int currentLevel) {
		if (node == null) {
			return;
		}
		currentLevel = currentLevel + 1;
		if (currentLevel > maxLevel) {
			maxLevel = currentLevel;
			System.out.print(node.key + " ");
		}
		/* first print data of node */

		/* then recur on left sutree */
		printLeftViewOfTree(node.left, currentLevel);

		/* now recur on right subtree */
		printLeftViewOfTree(node.right, currentLevel);
	}

	/*
	 * This algorithm basically uses modified pre-order traversal. In this
	 * modified pre-order traversal we make sure that - a. The right-most node
	 * of any given level is visited before other nodes in that level. This can
	 * be easily achieved by visiting right sub-tree of node before left
	 * sub-tree. Basically, in this traversal, we visit the node first, then
	 * visit the right sub-tree and finally the left sub-tree(N-R-L). b. We
	 * print out the node value as soon as we encounter a node in the level that
	 * is greater than the maximum level seen so far and update maximum level
	 * seen so far to current level. Time Complexity is O(n) Space Complexity is
	 * O(1)
	 */
	private void printRightViewOfTree(Node node, int currentLevel) {
		if (node == null) {
			return;
		}
		currentLevel = currentLevel + 1;
		if (currentLevel > maxLevel) {
			maxLevel = currentLevel;
			System.out.print(node.key + " ");
		}
		/* now recur on right subtree */
		printLeftViewOfTree(node.right, currentLevel);
		/* then recur on left sutree */
		printLeftViewOfTree(node.left, currentLevel);

	}

	/*
	 * Given a binary tree, print all the root to leaf paths of the tree. For
	 * example: Consider the tree: 1 23 4567 There will be 4 paths to the from
	 * root to the leaves 4, 5, 6 and 7 as given in below output: Output: 124
	 * 125 136 137
	 * 
	 * Time Complexity is O(n) Space Complexity is O(1)
	 */
	private void printRootToLeafPath(Node node, ArrayList<Integer> path) {
		if (node == null) {
			return;
		}

		path.add(node.key);
		if ((node.left == null) && (node.right == null)) {
			System.out.println("path :" + path);
			return;
		}
		/* now recur on right subtree */
		printRootToLeafPath(node.left, new ArrayList<>(path));
		/* then recur on left sutree */
		printRootToLeafPath(node.right, new ArrayList<>(path));
	}
	
	private void findMinDepthOfNode(Node node, int depth, int minDepth){
		if (node == null) {
			if((minDepth == 0) || (depth < minDepth)){
				 minDepth = depth;
				 System.out.println(depth+" Min depth : "+minDepth);
			 }
			return;
		}

		/* now recur on right subtree */
		findMinDepthOfNode(node.left, depth+1, minDepth);
		/* then recur on left sutree */
		findMinDepthOfNode(node.right, depth+1, minDepth);

	}
	
	/**
	 * Given a binary tree, write a program to print all nodes of that tree which do not have sibling nodes. For example in the following tree, nodes 6, 8, 5, 7 are such nodes because for all of these nodes parent node has only one child.
	 * @param node
	 */
	private void printNodesWOSibblings(Node node) {
		System.out.println("printNodesWOSibblings");
		if (node == null)
			return;

		List<Node> queue = new LinkedList<Node>();
		queue.add(node);
		while (!queue.isEmpty()) {
			node = queue.remove(0);
			if (node.left != null) {
				queue.add(node.left);
				if(node.right == null){
					System.out.print(node.left.key + " ");
				}
				
			}
			if (node.right != null) {
				queue.add(node.right);
				if(node.left == null){
					System.out.print(node.right.key + " ");
				}
			}
		}

	}


	// Driver method
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);

		System.out.println("Preorder traversal of binary tree is ");
		tree.printPreorder();
		tree.printPreorderWoRecurssion(tree.root);

		System.out.println("\nInorder traversal of binary tree is ");
		tree.printInorder();

		System.out.println("\nPostorder traversal of binary tree is ");
		tree.printPostorder();

		System.out.println("\nLeft View");
		tree.printLeftViewOfTree(tree.root);
		System.out.println("\nLeft View Using Preorder");
		tree.maxLevel = -1;
		tree.printLeftViewOfTree(tree.root, 0);
		tree.maxLevel = -1;
		System.out.println("\nRight View Using Modified Preorder");
		tree.printRightViewOfTree(tree.root, 0);
		System.out.println("\n Root To Leaf View");
		tree.printRootToLeafPath(tree.root, new ArrayList<Integer>());
		System.out.println("\n BFS");
		tree.printBFS(tree.root);

		tree = null;
		tree = new BinaryTree();
		tree.root = new Node(20);
		tree.root.left = new Node(8);
		tree.root.right = new Node(22);
		tree.root.left.left = new Node(5);
		tree.root.left.right = new Node(3);
		tree.root.left.right.left = new Node(10);
		tree.root.left.right.right = new Node(14);


		tree.printBottomViewOfTree(tree.root);

		tree.printTopViewOfTree(tree.root);

		tree.printVerticalOrderOfTree(tree.root);
		tree.findMinDepthOfNode(tree.root, 0, 0);
		
		
		
		tree = new BinaryTree();
		tree.root = new Node(0);
		tree.root.left = new Node(1);
		tree.root.right = new Node(2);
		tree.root.left.left = new Node(3);
		tree.root.left.right = new Node(4);
		tree.root.left.left.right = new Node(6);
		tree.root.left.left.right.right = new Node(8);
		tree.root.right.left = new Node(5);
		tree.root.right.left.right = new Node(7);
		tree.printNodesWOSibblings(tree.root);
	}

}

/*
 * Class containing left and right child of current node and key value
 */
class Node {
	int key;
	Node left, right;

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
