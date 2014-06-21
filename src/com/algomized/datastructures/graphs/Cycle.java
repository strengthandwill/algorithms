package com.algomized.datastructures.graphs;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Determines whether a graph has cycle(s) using Depth First Search.
 *
 */
public class Cycle {
	private boolean[] marked;
	private boolean hasCycle = false;

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
		
		Cycle cycle = new Cycle(graph);
		System.out.println(cycle.hasCycle());
	}
	
	public Cycle(Graph graph) {
		marked = new boolean[graph.vertices()];
		for (int s = 0; s < graph.vertices(); s++) {			
			if (!marked[s]) {
				dfs(graph, s, s);
			}
		}
	}
	
	/**
	 *  Time:  Worst = O(|V| + |E|)<br>
	 *  Space: Worst = O(|V|) 
	 */
	private void dfs(Graph graph, int v, int w) {
		if (hasCycle()) {
			return;
		}
		
		marked[v] = true;
		for (int x : graph.adj(v)) {					
			if (!marked[x]) {
				dfs(graph, x, v);
			} else if (x != w) { // cycle found
				hasCycle = true;
			} 
		}
	}
	
	public boolean hasCycle() {
		return hasCycle;
	}
}
