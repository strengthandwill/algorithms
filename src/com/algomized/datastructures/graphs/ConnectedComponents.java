package com.algomized.datastructures.graphs;

public class ConnectedComponents {
	private boolean[] marked;
	private int[] id;
	private int count;

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
		
		ConnectedComponents cc = new ConnectedComponents(graph);
		System.out.println(cc.count());
		System.out.println(cc.connected(0, 1));
	}
	
	public ConnectedComponents(Graph graph) {
		marked = new boolean[graph.vertices()];
		id = new int[graph.vertices()];
		
		for (int s = 0; s < graph.vertices(); s++) {
			if (!marked[s]) {
				dfs(graph, s);
				count++;
			}
		}
	}
		
	/**
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = O(|V|)  
	 */
	private void dfs(Graph graph, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : graph.adj(v)) {
			if (!marked[w]) {
				dfs(graph, w);
			}
		}
	}
	
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}
	
	public int count() {
		return count;
	}
}
