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
public class BreathFirstPaths {
	private final int s;
	private boolean[] marked;
	private int[] edgeTo;
	
	public static void main(String[] args) {
		Graph graph = new Graph(13);
		graph.addEdge(0, 5);
		graph.addEdge(4, 3);
		graph.addEdge(0, 1);
		graph.addEdge(9, 12);
		graph.addEdge(6, 4);
		graph.addEdge(5, 4);
		graph.addEdge(0, 2);
		graph.addEdge(11, 12);
		graph.addEdge(9, 10);
		graph.addEdge(0, 6);
		graph.addEdge(7, 8);
		graph.addEdge(9, 11);
		graph.addEdge(5, 3);
		System.out.println(graph.vertices());
		System.out.println(graph.edges());
		System.out.println(graph);

		BreathFirstPaths bfp = new BreathFirstPaths(graph, 0);
		for (int i = 0; i < graph.vertices(); i++) {
			System.out.println(i + ": " + bfp.pathTo(i));
		}			
	}
	
	public BreathFirstPaths(Graph graph, int s) {
		this.s = s;
		this.marked = new boolean[graph.vertices()];
		this.edgeTo = new int[graph.vertices()];
		bfs(graph, s);
	}
	/**
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = 1 queue = O(|V|) 
	 */
	private void bfs(Graph graph, int s) {
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
