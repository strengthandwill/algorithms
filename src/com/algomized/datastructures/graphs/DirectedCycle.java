package com.algomized.datastructures.graphs;

import com.algomized.datastructures.stacks.Stack;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Determines whether a graph has cycle(s) using Depth First Search.
 *
 */
public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack;
	private Stack<Integer> cycle;

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
		
		DirectedCycle cycle = new DirectedCycle(graph);
		System.out.println(cycle.hasCycle());
		System.out.println(cycle.cycle());
	}
	
	public DirectedCycle(Digraph graph) {
		marked = new boolean[graph.vertices()];
		edgeTo = new int[graph.vertices()];
		onStack = new boolean[graph.vertices()];
		for (int s = 0; s < graph.vertices(); s++) {
			if (!marked[s]) {
				dfs(graph, s);
			}
		}
	}
	
	/**
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = O(|V|) 
	 */
	private void dfs(Digraph graph, int v) {
		if (hasCycle()) {
			return;
		}
		
		marked[v] = true;
		onStack[v] = true;
		for (int w : graph.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(graph, w);
			} else if (onStack[w]) { // cycle found, onStack ensured that vertex is not backtracted
				cycle = new Stack<Integer>();
				int x = v;				
				while (x != w) {
					cycle.push(x);
					x = edgeTo[x];			
				}
				cycle.push(x);
				cycle.push(v); // start and end with the same vertex for cycle 
			}
		}
		onStack[v] = false;
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<Integer> cycle() {
		return cycle;
	}
}
