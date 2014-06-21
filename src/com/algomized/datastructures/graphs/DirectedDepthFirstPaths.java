package com.algomized.datastructures.graphs;

import com.algomized.datastructures.stacks.Stack;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Uses Depth First Search to find a path from source vertex s to vertex v.
 * </p>
 *
 */
public class DirectedDepthFirstPaths {
	private final int s;
	private boolean[] marked;	
	private int[] edgeTo;
	
	public static void main(String[] args) {
		Graph graph = new Graph(13);
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

		DirectedDepthFirstPaths dfp = new DirectedDepthFirstPaths(graph, 0);
		for (int i = 0; i < graph.vertices(); i++) {
			System.out.println(i + ": " + dfp.pathTo(i));
		}		
	}
	
	public DirectedDepthFirstPaths(Graph graph, int s) {
		this.s = s;
		this.marked = new boolean[graph.vertices()];
		this.edgeTo = new int[graph.vertices()];
		dfs(graph, s);
	}		
	
	/**
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = O(|V|) 
	 */
	public void dfs(Graph graph, int v) {
		marked[v] = true;
		for (int w : graph.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(graph, w);
			}
		}
	}
	
	public boolean hasPath(int v) {
		return marked[v];
	}
	
	/**
	 * Time:  Worst = O(|V|)<br>
	 * Space: Worst = 1 stack = O(|V|) 
	 */
	public Iterable<Integer> pathTo(int v) {
		if (!marked[v]) {
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
