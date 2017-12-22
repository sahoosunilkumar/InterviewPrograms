package com.sunilsahoo.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Bellman-Ford algorithm can detect negative cycles in the graph.
Bellman-Ford Algorithm
G - Graph
s - source vertex
V - vertices in the graph
E - Edges in the graph
(u,v) - Edge from u to v
distance[i] - shortest distance from source to vertex i w(u,v) - weight on the edge from u to v
Step 1: Initialize distances from source to all vertices: distance[s] = 0
distance[u] = Integer.MAX_VALUE for all vertices
other than s
Step 2: Relax Edges- Repeat following steps V-1 times:
For every edge E, repeat following steps:
If distance[v] > distance[u] + weight(u,v) then
set distance[v] = distance[u] + weight(u,v)
Source = A
distance[B] = 10
distance[C] = 3
Since distance[C] + w(C,B) = 3 + 4 = 7 <
distance[B],
the alternate path A->C->B is shorter than direct A-
>B path.
So, we relax the edge A->B and replace it with A-
>C->B.
Step 3: Detect negative cycle- Check if there exists an edge (u,v) for which,
distance[v] > distance[u] + weight(u,v), then graph has a negative cycle.
So, return true
Step 4: If there is no negative cycle, return false


 * Time Complexity is O(VE) Space Complexity is O(V)
 */

public class BellmanFord {
	private static final int INFINITY = 100000;

	public static void main(String[] args) {
		BellmanFord program = new BellmanFord();
		Graph graph = program.new Graph();
		graph.addEdge(0, 3, 8);
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 2, 5);
		graph.addEdge(1, 2, -3);
		graph.addEdge(2, 4, 4);
		graph.addEdge(3, 4, 2);
		graph.addEdge(4, 3, 1);

		Vertex sourceVertex = graph.getAllVertex().iterator().next();
		program.getShortestPathsBellmanFord(graph, sourceVertex);

	}

	public void getShortestPathsBellmanFord(Graph graph, Vertex sourceVertex) {
		Map<Integer, Integer> distanceMap = new HashMap<>();
		Map<Integer, Integer> parentNodeMap = new HashMap<>();
		// Vertex sourceVertex = graph.getAllVertex().iterator().next();
		for (Vertex vertex : graph.getAllVertex()) {
			distanceMap.put(vertex.id, INFINITY);
			parentNodeMap.put(vertex.id, null);
		}

		distanceMap.put(sourceVertex.id, 0);

		// iterate the loop V-1 times to get shortest path
		int size = graph.getAllVertex().size();
		for (int counter = 0; counter < size - 1; counter++) {
			for (Vertex vertex : graph.getAllVertex()) {
				for (Edge edge : vertex.getAllAdjacents()) {
					System.out.println(
							"map : " + distanceMap + " source : " + edge.source
									+ " destination : " + edge.destination);
					if (distanceMap.get(edge.destination.id) > edge.weight
							+ distanceMap.get(edge.source.id)) {
						distanceMap.put(edge.destination.id, (edge.weight
								+ distanceMap.get(edge.source.id)));
						parentNodeMap.put(edge.destination.id, edge.source.id);
					}
				}
			}
		}

		// iterate one more time to find -ve weight cycle
		for (Vertex vertex : graph.getAllVertex()) {
			for (Edge edge : vertex.getAllAdjacents()) {
				if (distanceMap.get(edge.destination.id) > edge.weight
						+ distanceMap.get(edge.source.id)) {
					System.out.println("Negative cycle exist");
				}
			}
		}

		System.out.println("shortest path : " + distanceMap);
		System.out.println("parent path : " + parentNodeMap);
	}

	class Graph {
		Map<Integer, Vertex> vertexMap = new HashMap<>();

		void addEdge(int sourceVertex, int destinationVertex, int weight) {
			if (!vertexMap.containsKey(sourceVertex)) {
				vertexMap.put(sourceVertex, new Vertex(sourceVertex));
			}
			if (!vertexMap.containsKey(destinationVertex)) {
				vertexMap.put(destinationVertex, new Vertex(destinationVertex));
			}
			Vertex source = vertexMap.get(sourceVertex);
			Vertex destination = vertexMap.get(destinationVertex);
			vertexMap.get(sourceVertex)
					.addAdjacentVertex(new Edge(source, destination, weight));
		}

		Collection<Vertex> getAllVertex() {
			return vertexMap.values();
		}
	}

	class Vertex {
		private List<Edge> edgeList = new ArrayList<>();
		int id;

		Vertex(int vertex) {
			this.id = vertex;
		}

		void addAdjacentVertex(Edge edge) {
			edgeList.add(edge);
		}

		List<Edge> getAllAdjacents() {
			return edgeList;
		}
	}

	class Edge {
		public Vertex source, destination;
		public int weight;

		Edge(Vertex source, Vertex destination, int weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}
	}
}