package com.algomized.datastructures.graphs;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p> 
 * Determines whether a graph is bipartite using Depth First Search.
 * </p>
 *
 */
public class Bipartite {
	private boolean[] marked;
	private boolean[] color;
	private boolean isBipartite = true;

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
		
		Bipartite b = new Bipartite(graph);
		System.out.println(b.isBipartite);

	}
	
	public Bipartite(Graph graph) {
		marked = new boolean[graph.vertices()];
		color = new boolean[graph.vertices()];
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
	private void dfs(Graph graph, int v) {
		marked[v] = true;
		for (int w : graph.adj(v)) {
			if (!marked[w]) {
				color[w] = !color[v];
				dfs(graph, w);
			} else if (color[w] == !color[v]) {
				isBipartite = false;
			}
		}
	}
	
	public boolean isBipartite() {
		return isBipartite;
	}
}
