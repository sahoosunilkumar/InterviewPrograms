package com.sunilsahoo.programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class GraphProblem {
	public static void main(String[] args) {
		int[][] matrix = { { 1, 0, 1, 0, 1 }, { 1, 1, 0, 0, 0 },
				{ 0, 1, 0, 1, 1 }, };
		NumberOfClusters solution = new NumberOfClusters();
		System.out.println(solution.findNumberofClusters(matrix));
		
		
		Graph<Integer> graph = new Graph<>();
		createGraph(graph);
		int srcNodeId = 0, destNodeId = 5;
		System.out
				.print("\n\nIf path exists between source and destination node:\n"
						+ graph.isPathExists(srcNodeId, destNodeId));
	}
	
	public static void createGraph(Graph<Integer> graph) {
		graph.addNode(0);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addEdge(0, 1, 2);

		graph.addEdge(0, 2, 4);
		graph.addEdge(1, 2, 4);
		graph.addEdge(1, 3, 5);
		graph.addEdge(1, 4, 3);
		graph.addEdge(3, 5, 1);
		graph.addEdge(4, 5, 1);
	}

}

// Given a 2D matrix of 0s and 1s, find total number of clusters formed by
// elements with value 1. For example, in the below shown 2D matrix there are
// total three such clusters.
class NumberOfClusters {
	final static int[] offsets = { -1, 0, +1 };

	private boolean neighborExists(int[][] matrix, int i, int j) {
		if ((i >= 0) && (i < matrix.length) && (j >= 0)
				&& (j < matrix[0].length)) {
			if (matrix[i][j] == 1) {
				return true;
			}
		}
		return false;
	}

	private void doDFS(int[][] matrix, int i, int j, boolean[][] visited) {
		if (visited[i][j]) {
			return;
		}
		visited[i][j] = true;
		int xOffset, yOffset;
		for (int l = 0; l < offsets.length; l++) {
			xOffset = offsets[l];
			for (int m = 0; m < offsets.length; m++) {
				yOffset = offsets[m];
				if (xOffset == 0 && yOffset == 0) {
					continue;
				}
				if (neighborExists(matrix, i + xOffset, j + yOffset)) {
					doDFS(matrix, i + xOffset, j + yOffset, visited);
				}
			}
		}
	}

	public int findNumberofClusters(int[][] matrix) {
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if ((matrix[i][j] == 1) && (!visited[i][j])) {
					count += 1;
					doDFS(matrix, i, j, visited);
				}
			}
		}
		return count;
	}
}

/*
 * Time Complexity is O(|V| + |E|) Space Complexity is O(|V|) Breadth first
 * search in a graph Given a graph, check if path exists between source node and
 * destination node using breadth first search. In the breadth first search, all
 * nodes at a lower level are visited before all nodes at a higher level.
 * 
 * 
 * The algorithm for breadth first search in a graph is a simple queue based
 * traversal algorithm. We will try to understand following steps for doing
 * breadth first search from 'source' node to 'destination' node using an
 * example. 1. Add 'source' node to an empty 'queue', mark 'source' node as
 * visited. 2. While this 'queue' has more nodes - 2a. Remove the first node
 * from this queue. Let's call this currentNode. 2b. If currentNode is
 * 'destination' node, then search is complete. We can return from this point.
 * In the implementation, we have only set boolean 'destNodeFound' as true
 * without returning. 2c. Add all unvisited neighbors of currentNode to queue.
 * While adding each neighbor to queue, mark it as visited. Note that if we add
 * all neighbors of currentNode to queue without checking if it is visited or
 * not, we might end up in never-ending traversal if there are cycles in the
 * graph. These steps complete the algorithm. In the implementation, we have
 * also kept track of level of current node and maximum level visited so far to
 * identify when the new level starts. Let's look at an example now. For the
 * following graph, if source node is '0' and destination node is '5',
 */

class Graph<T> {
	private class QueueNode {
		GraphNode graphNode;
		int level;

		public QueueNode(GraphNode node, int level) {
			this.graphNode = node;
			this.level = level;
		}
	}

	private class GraphNode {
		T nodeId;
		GraphNode next;
		int parentDist;

		GraphNode(T id) {
			nodeId = id;
			next = null;
		}

		GraphNode(T id, int dist) {
			nodeId = id;
			next = null;
			parentDist = dist;
		}
	}

	ArrayList<GraphNode> nodeList;

	public Graph() {
		nodeList = new ArrayList<GraphNode>();
	}

	public void addNode(T id) {
		GraphNode node = new GraphNode(id);
		nodeList.add(node);
	}

	public void addEdge(T id1, T id2, int dist) {
		int i = 0;
		for (i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).nodeId == id1) {
				break;
			}
		}
		if (i == nodeList.size()) {
			return;
		}
		GraphNode node1 = nodeList.get(i);
		GraphNode node2 = new GraphNode(id2, dist);
		node2.next = node1.next;
		node1.next = node2;
	}

	public GraphNode findGraphNode(T nodeId) {
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).nodeId == nodeId) {
				return nodeList.get(i);
			}
		}
		return null;
	}

	public void printGraph() {
		for (int i = 0; i < nodeList.size(); i++) {
			GraphNode curr = nodeList.get(i);
			while (curr != null) {
				System.out.print(
						curr.nodeId + "(" + curr.parentDist + ")" + "->");
				curr = curr.next;
			}
			System.out.print("Null");
			System.out.println();
		}
	}

	/*
	 * using breadth first search
	 */
	public boolean isPathExists(T srcId, T destId) {
		if (nodeList.isEmpty()) {
			System.out.println("Empty graph");
			return false;
		}
		LinkedList<QueueNode> queue = new LinkedList<>();
		HashMap<T, Integer> visited = new HashMap<>();
		GraphNode srcNode = null;
		//find source id
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).nodeId == srcId) {
				srcNode = nodeList.get(i);
				break;
			}
		}
		
		if (srcNode == null) {
			System.out.println("Source vertex not found");
			return false;
		}
		boolean destNodeFound = false;
		int maxLevelVisited = -1;
		queue.add(new QueueNode(srcNode, 0));
		visited.put(srcNode.nodeId, 1);

		while (!queue.isEmpty()) {
			QueueNode currentNode = queue.remove();
			if (currentNode.level > maxLevelVisited) {
				System.out.print("\nlevel " + currentNode.level + "-");
				maxLevelVisited = currentNode.level;
			}
			System.out.print(currentNode.graphNode.nodeId + " ");
			if (currentNode.graphNode.nodeId == destId) {
				destNodeFound = true;
			}
			GraphNode neighbor = currentNode.graphNode.next;
			while (neighbor != null) {
				if (visited.get(neighbor.nodeId) == null) {
					visited.put(neighbor.nodeId, 1);
					queue.add(new QueueNode(findGraphNode(neighbor.nodeId),
							currentNode.level + 1));
				}
				neighbor = neighbor.next;
			}
		}
		return destNodeFound;
	}

}
