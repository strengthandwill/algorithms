package com.algomized.datastructures.graphs;

import com.algomized.datastructures.queues.Queue;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given a directed graph, design an algorithm to find out whether there is a route 
 * between two nodes
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC4Q2 {
	public static void main(String[] args) {
		Digraph graph = new Digraph(13);
		graph.addEdge(4, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 2);
		graph.addEdge(6, 0);
		graph.addEdge(0, 1);
		graph.addEdge(2, 0);
		graph.addEdge(11, 12);
		graph.addEdge(12, 9);
		graph.addEdge(9, 10);
		graph.addEdge(9, 11);
		graph.addEdge(8, 9);
		graph.addEdge(10, 12);
		graph.addEdge(11, 4);
		graph.addEdge(4, 3);
		graph.addEdge(3, 5);
		graph.addEdge(7, 8);
		graph.addEdge(8, 7);
		graph.addEdge(5, 4);
		graph.addEdge(0, 5);
		graph.addEdge(6, 4);
		graph.addEdge(6, 9);
		graph.addEdge(7, 6);

		System.out.println(graph.vertices());
		System.out.println(graph.edges());
		System.out.println(graph);
		
		System.out.println(connectedDFS(graph, 0, 1));
		System.out.println(connectedBFS(graph, 0, 1));
	}
	
	/**
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = O(|V|) [Recursive calls]
	 */
	public static boolean connectedDFS(Digraph graph, int v, int w) {
		if (graph == null || v < 0 || v >= graph.vertices() || 
				w < 0 || w >= graph.vertices()) {
			return false;
		}
		return dfs(graph, v, w, new boolean[graph.vertices()]);
	}
	
	private static boolean dfs(Digraph graph, int v, int w, boolean[] marked) {
		if (v == w) {
			return true; // v is connected w
		}
		marked[v] = true;
		boolean connected = false;
		for (int x : graph.adj(v)) {
			if (!connected && !marked[x]) {
 				connected |= dfs(graph, x, w, marked); 				
			}
		}
		return connected;
	}
	
	/**
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = O(|V|) [Queue] 
	 */
	private static boolean connectedBFS(Digraph graph, int v, int w) {
		if (graph == null || v < 0 || v >= graph.vertices() || 
				w < 0 || w >= graph.vertices()) {
			return false;
		}
		boolean[] marked = new boolean[graph.vertices()];
		Queue<Integer> queue = new Queue<Integer>();
		marked[v] = true;
		queue.enqueue(v);
		while (!queue.isEmpty()) {
			for (int x : graph.adj(queue.dequeue())) {
				if (!marked[x]) {
					if (x == w) {
						return true; // v is connected to w
					}					
					marked[x] = true;
					queue.enqueue(x);
				}
			}
		}
		return false;
	}
}
