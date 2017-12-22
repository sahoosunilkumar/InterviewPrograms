package com.sunilsahoo.collection;

import java.util.LinkedList;

public class GraphTest {
	public static void main(String[] args) {
		Graph graph = createGraph();
		Nodeg[] n = graph.getNodes();
		Nodeg start = n[3];
		Nodeg end = n[5];
		System.out.println(doesRootExists(graph, start, end));
	}

	/**
	 * it uses bfs
	 * 
	 * @param graph
	 * @param start
	 * @param end
	 * @return
	 */
	private static boolean doesRootExists(Graph graph, Nodeg start, Nodeg end) {
		LinkedList<Nodeg> queue = new LinkedList<Nodeg>();
		for (Nodeg node : graph.getNodes()) {
			node.state = State.Unvisited;
		}
		start.state = State.Visiting;
		queue.add(start);
		while (!queue.isEmpty()) {
			Nodeg node = queue.removeFirst();
			if (node != null) {
				for (Nodeg adjacent : node.getAdjacentNodes()) {
					if (adjacent.state == State.Unvisited) {
						if (adjacent == end) {
							return true;
						} else {
							adjacent.state = State.Visiting;
							queue.add(adjacent);
						}
					}
				}
				node.state = State.Visited;
			}
		}
		return false;
	}

	public enum State {
		Unvisited, Visited, Visiting;
	}

	private static Graph createGraph() {
		Graph graph = new Graph(6);
		Nodeg[] nodeArr = new Nodeg[6];
		nodeArr[0] = new Nodeg("a", 3);
		nodeArr[1] = new Nodeg("b", 0);
		nodeArr[2] = new Nodeg("c", 0);
		nodeArr[3] = new Nodeg("d", 1);
		nodeArr[4] = new Nodeg("e", 1);
		nodeArr[5] = new Nodeg("f", 0);

		// add adjacents
		nodeArr[0].addAdjacent(nodeArr[1]);
		nodeArr[0].addAdjacent(nodeArr[2]);
		nodeArr[0].addAdjacent(nodeArr[3]);
		nodeArr[3].addAdjacent(nodeArr[4]);
		nodeArr[4].addAdjacent(nodeArr[5]);

		for (int i = 0; i < nodeArr.length; i++) {
			graph.addNode(nodeArr[i]);
		}
		return graph;
	}
}

class Graph {
	Nodeg[] vertices;
	int verticesCount;

	Graph(int veticeCount) {
		vertices = new Nodeg[veticeCount];
	}

	public Nodeg[] getNodes() {
		return vertices;
	}

	public void addNode(Nodeg node) {
		vertices[verticesCount++] = node;
	}
}

class Nodeg {
	String vertex;
	int adjacentCount;
	Nodeg[] adjacentNodeArr;
	public GraphTest.State state;

	Nodeg(String vertex, int adjacentCount) {
		this.vertex = vertex;
		adjacentNodeArr = new Nodeg[adjacentCount];
	}

	void addAdjacent(Nodeg adjacentNode) {
		adjacentNodeArr[adjacentCount++] = adjacentNode;
	}

	public String getVertex() {
		return vertex;
	}

	public Nodeg[] getAdjacentNodes() {
		return adjacentNodeArr;
	}
}