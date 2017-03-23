package com.sunilsahoo.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Dijkstra's algorithm essentially uses breadth first search with greedy
 * approach to come up with the shortest distance between given two nodes. Let
 * the node from which we would find the shortest distance of all other nodes be
 * called initial node. We define the distance of node 'Y' as the distance from
 * the initial node to node 'Y'. Dijkstra's algorithm will assign some initial
 * distance values and will try to improve them step by step. The steps of the
 * algorithm are as following 1. Assign to every node a tentative distance
 * value: set it to zero for our initial node and to infinity for all other
 * nodes. This step signifies that at the start of the algorithm, the starting
 * node is at distance 0 from itself and other nodes are unreachable. 2. Set the
 * initial node as current. Mark all other nodes unvisited. Create a set of all
 * the unvisited nodes called the unvisited set. 3. For the current node,
 * consider all of its unvisited neighbors and calculate their tentative
 * distances. Compare the newly calculated tentative distance to the current
 * assigned value and assign the smaller one. For example, if the current node A
 * is marked with a distance of 6, and the edge connecting it with a neighbor B
 * has length 2, then the distance to B (through A) will be 6 + 2 = 8. If B was
 * previously marked with a distance greater than 8 then change it to 8. Also
 * change the parent node of this neighbor as the current node. These parent
 * nodes will help to backtrack the shortest path to a node from the source
 * node. If the currently assigned distance value of the neighbor node is
 * smaller than distance of neighbor from current node + current node's assigned
 * distance then don't do anything. 4. When we are done considering all the
 * neighbors of the current node, mark the current node as visited and remove it
 * from the unvisited set. A visited node will never be checked again. 5. If
 * there is no node remaining in unvisited set or the smallest distance of node
 * in unvisited set is infinity then stop. Algorithm is completed. 6. Otherwise,
 * select the unvisited node that is marked with the smallest tentative
 * distance, set it as the new "current node", and go back to step 3. (Source:
 * wikipedia) This algorithm is implemented using priority queue for keeping
 * node with the shortest distance from the source at the front of the
 * queue(remember greedy approach), parent array for keeping track of immediate
 * parent of a node on the shortest path from source to that node, distance
 * array to keep track of shortest distance of a node from source node, unvisted
 * array. Let's see how it works for the graph shown here.
 * 
 * Time Complexity is O(|E| + |V|^2) Space Complexity is O(|V|)
 * 
 * @author sunilkumarsahoo
 *
 */
public class Dijkstra {
	final private static int QUEUE_INITIAL_CAPACITY = 10;

	ArrayList<GraphNode> nodeList;

	public Dijkstra() {
		nodeList = new ArrayList<GraphNode>();
	}

	public void addNode(int id) {
		GraphNode node = new GraphNode(id);
		nodeList.add(node);
	}

	public void addEdge(int id1, int id2, int dist) {
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

	private GraphNode findGraphNode(int currQueueNodeId) {
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).nodeId == currQueueNodeId) {
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

	private void updateQueue(PriorityQueue queue, int nodeId, int oldDist,
			int newDist) {

		Iterator<QueueNode> queueItr = queue.iterator();
		boolean queueUpdated = false;
		while (queueItr.hasNext()) {
			QueueNode tempNode = queueItr.next();
			if (tempNode.nodeId == nodeId) {
				queueUpdated = true;
				tempNode.distFromSrc = newDist;
				break;
			}
		}
		if (!queueUpdated) {
			queue.add(new QueueNode(nodeId, newDist));
		}
	}

	public int[] findShortestDijkstra(int srcId) {
		Comparator<QueueNode> comparator = new QueueNodeComparator();
		PriorityQueue<QueueNode> queue = new PriorityQueue<QueueNode>(
				QUEUE_INITIAL_CAPACITY, comparator);
		boolean[] unvisited = new boolean[nodeList.size()];
		int[] parent = new int[nodeList.size()];
		int[] distance = new int[nodeList.size()];
		for (int i = 0; i < nodeList.size(); i++) {
			unvisited[i] = true;
			parent[i] = -1;
			distance[i] = Integer.MAX_VALUE;
		}
		queue.add(new QueueNode(srcId, 0));
		while (!queue.isEmpty()) {
			System.out.println("queue : "+queue);
			QueueNode currQueueNode = queue.remove();
			unvisited[currQueueNode.nodeId] = false;
			distance[currQueueNode.nodeId] = currQueueNode.distFromSrc;
			GraphNode currGraphNode = findGraphNode(currQueueNode.nodeId);
			GraphNode neighborNode = (currGraphNode == null) ? null
					: currGraphNode.next;
			while (neighborNode != null) {
				if (unvisited[neighborNode.nodeId]) {
					if ((distance[currQueueNode.nodeId]
							+ neighborNode.parentDist) < distance[neighborNode.nodeId])

					{
						int oldDistance = distance[neighborNode.nodeId];
						int newDistance = distance[currQueueNode.nodeId]
								+ neighborNode.parentDist;
						distance[neighborNode.nodeId] = newDistance;
						parent[neighborNode.nodeId] = currQueueNode.nodeId;
						updateQueue(queue, neighborNode.nodeId, oldDistance,
								newDistance);
					}
				}
				neighborNode = neighborNode.next;
			}
		}
		return distance;
	}

	@Override
	public String toString() {
		return nodeList.toString();
	}

	public static void main(String[] args) {
		Dijkstra graphObj = new Dijkstra();
		graphObj.addNode(0);
		graphObj.addNode(1);
		graphObj.addNode(2);
		graphObj.addNode(3);
		graphObj.addNode(4);
		graphObj.addNode(5);
		graphObj.addNode(6);
		graphObj.addEdge(0, 2, 1);
		graphObj.addEdge(0, 1, 2);
		graphObj.addEdge(1, 2, 3);
		graphObj.addEdge(2, 3, 5);
		graphObj.addEdge(2, 6, 13);
		graphObj.addEdge(3, 5, 6);
		graphObj.addEdge(3, 1, 6);
		graphObj.addEdge(3, 6, 6);
		graphObj.addEdge(5, 3, 7);
		System.out.println(graphObj);
		int[] distance = graphObj.findShortestDijkstra(0);
		for (int i = 0; i < distance.length; i++) {
			if (distance[i] == Integer.MAX_VALUE) {
				System.out.println(
						"vertex \'" + i + "\' is unreachable from vertex '0'");

			} else {
				System.out.println("distance of vertex \'" + i
						+ "\' from vertex '0' is " + distance[i]);
			}
		}
	}
}

class GraphNode {
	int nodeId;
	GraphNode next;
	int parentDist;

	GraphNode(int id) {
		nodeId = id;
		next = null;
		System.out.println("graph constructor : " + id);
	}

	GraphNode(int id, int dist) {
		nodeId = id;
		next = null;
		parentDist = dist;
		System.out.println("graph constructor : " + id + " dist : " + dist);
	}

	@Override
	public String toString() {
		return nodeId + " : " + parentDist + " next: " + next;
	}
}

class QueueNode {
	int nodeId;
	int distFromSrc;

	public QueueNode(int id, int dist) {
		nodeId = id;
		distFromSrc = dist;
	}
	@Override
	public String toString() {
		return "nodeId :"+nodeId+" dist : "+distFromSrc;
	}
}

class QueueNodeComparator implements Comparator<QueueNode> {
	@Override
	public int compare(QueueNode x, QueueNode y) {
		if (x.distFromSrc < y.distFromSrc) {
			return -1;
		}
		if (x.distFromSrc > y.distFromSrc) {
			return 1;
		}
		return 0;
	}
}
