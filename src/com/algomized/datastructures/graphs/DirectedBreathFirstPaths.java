package com.algomized.datastructures.graphs;

import com.algomized.datastructures.queues.Queue;
import com.algomized.datastructures.stacks.Stack;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>Find the shortest path between two vertices using Breath First Search.
 *
 */
public class DirectedBreathFirstPaths {
	private final int s;
	private boolean[] marked;
	private int[] edgeTo;
	
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

		DirectedBreathFirstPaths bfp = new DirectedBreathFirstPaths(graph, 0);
		for (int i = 0; i < graph.vertices(); i++) {
			System.out.print(i + ": ");
			Iterable<Integer> path = bfp.pathTo(i);
			if (path != null) {
				System.out.print(bfp.pathTo(i));
			}
			System.out.println();
		}			
	}
	
	public DirectedBreathFirstPaths(Digraph graph, int s) {
		this.s = s;
		this.marked = new boolean[graph.vertices()];
		this.edgeTo = new int[graph.vertices()];
		bfs(graph, s);
	}
	/**
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
					edgeTo[w] = v;
					queue.enqueue(w);
					marked[w] = true;
				}
			}
		}
	}
	
	public boolean hasPath(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		if (!hasPath(v)) {
			return null;
		}
		Stack<Integer> stack = new Stack<Integer>();
		while (v != s) {
			stack.push(v);
			v = edgeTo[v];
		}
		stack.push(v);
		return stack;
	}
}
