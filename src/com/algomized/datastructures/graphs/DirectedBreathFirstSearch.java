package com.algomized.datastructures.graphs;

import com.algomized.datastructures.queues.Queue;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Transverses all the unvisited adjacent vertices of the target vertex,
 * followed by traverses all the unvisited adjacent vertices of the previously
 * visited vertices, thereby transversing the vertices layer by layer.<br>
 * <br>
 * Implemented using a queue.
 * </p>
 *
 */
public class DirectedBreathFirstSearch {
	private boolean[] marked;
	private int count = 0;
	
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

		DirectedBreathFirstSearch bfs = new DirectedBreathFirstSearch(graph, 0);
		System.out.println(bfs.count());
	}
	
	public DirectedBreathFirstSearch(Digraph graph, int s) {
		marked = new boolean[graph.vertices()];
		bfs(graph, s);		
	}
	
	/**
	 * <b>Breath First Search</b>
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = 1 queue = O(|V|)
	 */ 
	private void bfs(Digraph graph, int s) {
		Queue<Integer> queue = new Queue<Integer>();
		marked[s] = true;
		queue.enqueue(s);
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			for (int w : graph.adj(v)) {
				if (!marked[w]) {
					marked[w] = true;
					count++;
					queue.enqueue(w);
				}
			}
		}
	}
	
	public int count() {
		return count;
	}
}
