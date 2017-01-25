package com.sunilsahoo.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GraphTraversal {
	static int noOfLoops = 0;
	LinkedList<Integer> vertexArr[];

	GraphTraversal(int vertexCount) {
		vertexArr = new LinkedList[vertexCount];
		for (int i = 0; i < vertexArr.length; i++) {
			vertexArr[i] = new LinkedList<>();
		}
	}

	void addEdge(int startVertex, int endVertex) {
		vertexArr[startVertex].add(endVertex);
	}

	/**
	 * Depth First Traversal (or Search) for a graph is similar to Depth First
	 * Traversal of a tree. The only catch here is, unlike trees, graphs may
	 * contain cycles, so we may come to the same node again. To avoid
	 * processing a node more than once, we use a boolean visited array. For
	 * example, in the following graph, we start traversal from vertex 2. When
	 * we come to vertex 0, we look for all adjacent vertices of it. 2 is also
	 * an adjacent vertex of 0. If we don’t mark visited vertices, then 2 will
	 * be processed again and it will become a non-terminating process. A Depth
	 * First Traversal of the following graph is 2, 0, 1, 3.
	 * 
	 * Time Complexity: O(V+E) where V is number of vertices in the graph and E
	 * is number of edges in the graph.
	 * 
	 */
	void dfs() {
		boolean visitedArr[] = new boolean[vertexArr.length];
		for (int i = 0; i < vertexArr.length; i++) {
			if (!visitedArr[i]) {
				dfs(i, visitedArr);
			}
		}
	}

	void dfs(int startVertex) {
		boolean visitedArr[] = new boolean[vertexArr.length];
		dfs(startVertex, visitedArr);
	}

	void dfs(int startVertex, boolean visitedArr[]) {
		visitedArr[startVertex] = true;
		System.out.print(startVertex + " ");
		Iterator<Integer> itr = vertexArr[startVertex].iterator();
		while (itr.hasNext()) {
			int node = itr.next();
			if (!visitedArr[node]) {
				dfs(node, visitedArr);
			}
		}
	}
	void findLoop(int startVertex) {
		List<Integer> list = new ArrayList<>();
		findLoop(startVertex, list);
	}
	void findLoop(int startVertex, List<Integer> list) {
		list.add(startVertex);
//		System.out.print(startVertex + " ");
		Iterator<Integer> itr = vertexArr[startVertex].iterator();
		while (itr.hasNext()) {
			int node = itr.next();
			if (!list.contains(node)) {
				findLoop(node, list);
			}else{
//				System.out.println("loop vertex is "+ node);
				noOfLoops++;
			}
		}
	}

	/**
	 * Breadth First Traversal (or Search) for a graph is similar to Breadth
	 * First Traversal of a tree (See method 2 of this post). The only catch
	 * here is, unlike trees, graphs may contain cycles, so we may come to the
	 * same node again. To avoid processing a node more than once, we use a
	 * boolean visited array. For simplicity, it is assumed that all vertices
	 * are reachable from the starting vertex. For example, in the following
	 * graph, we start traversal from vertex 2. When we come to vertex 0, we
	 * look for all adjacent vertices of it. 2 is also an adjacent vertex of 0.
	 * If we don’t mark visited vertices, then 2 will be processed again and it
	 * will become a non-terminating process. A Breadth First Traversal of the
	 * following graph is 2, 0, 3, 1.
	 * 
	 * Time Complexity: O(V+E) where V is number of vertices in the graph and E
	 * is number of edges in the graph.
	 * 
	 * @param startVertex
	 */
	void bfs(int startVertex) {
		boolean visitedArr[] = new boolean[vertexArr.length];
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(startVertex);
		visitedArr[startVertex] = true;
		while (!queue.isEmpty()) {
			int vertex = queue.removeFirst();
			System.out.print(" " + vertex);
			Iterator<Integer> iterator = vertexArr[vertex].iterator();
			while (iterator.hasNext()) {
				int index = iterator.next();
				if (!visitedArr[index]) {
					visitedArr[index] = true;
					queue.add(index);
				}
			}
		}
	}

	public static void main(String[] args) {
		GraphTraversal g = new GraphTraversal(10);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
//		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		g.addEdge(4, 8);
		
		g.addEdge(5, 6);
		g.addEdge(6, 4);
		g.addEdge(6, 7);
		g.addEdge(7, 5);
		g.addEdge(8, 3);
		g.addEdge(8, 9);


		
		int startPoint = 0;
		g.findLoop(startPoint);

		System.out.println("no of loops " + noOfLoops);
		
		System.out.println("start dfs from " + startPoint);
		g.dfs(startPoint);
		System.out.println("complete dfs ");
		g.dfs();
		System.out.println("start bfs from " + startPoint);
		g.bfs(startPoint);
	}

}
