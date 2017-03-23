package com.sunilsahoo.programs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class TreeProgram {

	int maxLevel = -1;

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
	 * the left view of This can be done by doing a level order traversal from
	 * left end to right end and keeping track of max-level seen so far to find
	 * out when the new level starts. As soon as we find out that the new level
	 * has started, we print out the current node that is being visited. The
	 * steps for this algorithm are - 1. Initialize maxLevelSeenSoFar to -1 and
	 * call printLeftViewLevelOrder(currentNode = root) 2. In function
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
	 * left sub-tree of node before right sub- Basically, in this traversal, we
	 * visit the node first, then visit the left sub-tree and finally the right
	 * sub-tree(N-L-R). b. We print out the node value as soon as we encounter a
	 * node in the level that is greater than the maximum level seen so far and
	 * update maximum level seen so far to current level. The steps of this
	 * algorithm are as following - 1. Initialize maxLevelSeenSoFar to -1 and
	 * call printLeftView(currentNode = root, level = 0) 2. In function
	 * printLeftView(currentNode, level), a. If currentNode is null, then we do
	 * nothing and return. b. Else, if (level > maxLevelSeenSoFar) we print out
	 * the currentNode's value and update maxLevelSeenSoFar to level. c. Make a
	 * recursive call printLeftView(currentNode.left, level + 1) to make sure
	 * nodes in the left sub- tree are visited. d. Make a recursive call
	 * printLeftView(currentNode.right, level + 1) to make sure nodes in the
	 * right sub- tree are visited. Notice that while visiting nodes with
	 * recursive calls, printLeftView(currentNode.left, level + 1) is called
	 * before printLeftView(currentNode.right, level + 1) in order to make sure
	 * that for any node, left sub- tree of that node is visited before right
	 * sub- This guarantees that at any level, the left-most node is visited
	 * before other nodes at that level. After execution of step #2, left view
	 * of tree would be printed. Please checkout code snippet and algorithm
	 * visualization section for more details of the algorithm. 2/11 Time
	 * Complexity is O(n) Space Complexity is O(1)
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
	 * be easily achieved by visiting right sub-tree of node before left sub-
	 * Basically, in this traversal, we visit the node first, then visit the
	 * right sub-tree and finally the left sub-tree(N-R-L). b. We print out the
	 * node value as soon as we encounter a node in the level that is greater
	 * than the maximum level seen so far and update maximum level seen so far
	 * to current level. Time Complexity is O(n) Space Complexity is O(1)
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
	 * Given a binary tree, print all the root to leaf paths of the For example:
	 * Consider the tree: 1 23 4567 There will be 4 paths to the from root to
	 * the leaves 4, 5, 6 and 7 as given in below output: Output: 124 125 136
	 * 137
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

	private void findMinDepthOfNode(Node node, int depth, int minDepth) {
		if (node == null) {
			if ((minDepth == 0) || (depth < minDepth)) {
				minDepth = depth;
			}
			return;
		}

		/* now recur on right subtree */
		findMinDepthOfNode(node.left, depth + 1, minDepth);
		/* then recur on left sutree */
		findMinDepthOfNode(node.right, depth + 1, minDepth);

	}

	/**
	 * Given a binary tree, write a program to print all nodes of that tree
	 * which do not have sibling nodes. For example in the following tree, nodes
	 * 6, 8, 5, 7 are such nodes because for all of these nodes parent node has
	 * only one child.
	 * 
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
				if (node.right == null) {
					System.out.print(node.left.key + " ");
				}

			}
			if (node.right != null) {
				queue.add(node.right);
				if (node.left == null) {
					System.out.print(node.right.key + " ");
				}
			}
		}

	}

	/*
	 * 1) Do In-Order Traversal of the given tree and store the result in a temp
	 * array. 3) Check if the temp array is sorted in ascending order, if it is,
	 * then the tree is BST. Time Complexity: O(n) We can avoid the use of
	 * Auxiliary Array. While doing In-Order traversal, we can keep track of
	 * previously visited node. If the value of the currently visited node is
	 * less than the previous value, then tree is not BST. Thanks to ygos for
	 * this space optimization.
	 */
	private boolean isBST(Node root) {
		if (root != null) {
			if (!isBST(root.left))
				return false;
			if (prev != null && root.key <= prev.key)
				return false;
			prev = root;
			return isBST(root.right);
		}
		return true;
	}
	
	/**
	 * Using two stack technique.
	 * right stack will push children to left stack (left child first)
	 * left stack will push children to right stack (right child first)
	 * 
	 * @param root
	 */
	private void spiralOrderTraversal(Node root) {
		Stack<Node> leftStack = new Stack<Node>();
		Stack<Node> rightStack = new Stack<Node>();
		rightStack.push(root);

		while (true) {
			if (leftStack.isEmpty() && rightStack.isEmpty()) {
				break;
			}

			while (!rightStack.isEmpty()) {
				Node node = rightStack.pop();
				if (node.left != null) {
					leftStack.push(node.left);
				}

				if (node.right != null) {
					leftStack.push(node.right);
				}
				System.out.print(node.key+" ");
			}

			while (!leftStack.isEmpty()) {
				Node node = leftStack.pop();

				if (node.right != null) {
					rightStack.push(node.right);
				}

				if (node.left != null) {
					rightStack.push(node.left);
				}
				System.out.print(node.key+" ");
			}
		}

	}

	// Driver method
	public static void main(String[] args) {

		TreeProgram treeProgram = new TreeProgram();
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		System.out.println("\nLeft View");
		treeProgram.printLeftViewOfTree(root);
		System.out.println("\nLeft View Using Preorder");
		treeProgram.maxLevel = -1;
		treeProgram.printLeftViewOfTree(root, 0);
		treeProgram.maxLevel = -1;
		System.out.println("\nRight View Using Modified Preorder");
		treeProgram.printRightViewOfTree(root, 0);
		System.out.println("\n Root To Leaf View");
		treeProgram.printRootToLeafPath(root, new ArrayList<Integer>());

		root = new Node(20);
		root.left = new Node(8);
		root.right = new Node(22);
		root.left.left = new Node(5);
		root.left.right = new Node(3);
		root.left.right.left = new Node(10);
		root.left.right.right = new Node(14);

		treeProgram.printBottomViewOfTree(root);
		treeProgram.printTopViewOfTree(root);
		treeProgram.printVerticalOrderOfTree(root);
		treeProgram.findMinDepthOfNode(root, 0, 0);

		root = new Node(0);
		root.left = new Node(1);
		root.right = new Node(2);
		root.left.left = new Node(3);
		root.left.right = new Node(4);
		root.left.left.right = new Node(6);
		root.left.left.right.right = new Node(8);
		root.right.left = new Node(5);
		root.right.left.right = new Node(7);
		treeProgram.printNodesWOSibblings(root);

		root = new Node(4);
		root.left = new Node(2);
		root.right = new Node(5);
		root.left.left = new Node(1);
		root.left.right = new Node(3);

		System.out.println("\nIs BST : " + treeProgram.isBST(root));
		
		
		root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		root.left.left.left = new Node(8);
		root.left.left.right = new Node(9);
		
		root.left.right.left = new Node(10);
		root.left.right.right = new Node(11);
		
		root.right.left.left = new Node(12);
		root.right.left.right = new Node(13);
		root.right.right.left = new Node(14);
		root.right.right.right = new Node(15);
		System.out.println("Spiral order Traversal : ");
		treeProgram.spiralOrderTraversal(root);
	}

	static Node prev = null;
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
