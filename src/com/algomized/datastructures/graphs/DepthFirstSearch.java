package com.algomized.datastructures.graphs;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Traverses the vertices by the edges until reaches visited vertices. Then backtrack 
 * to the previous vertices and repeat the process until the item is found (search hit) 
 * or all the vertices are visited (search miss).<br>
 * <br>
 * Implemented using recursive calls.
 * </p>
 *
 */
public class DepthFirstSearch {
	private boolean[] marked;
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
		
		DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);	
		System.out.println(dfs.count());
	}
	
	public DepthFirstSearch(Graph graph, int s) {
		this.marked = new boolean[graph.vertices()];
		dfs(graph, s);
	}
	
	/**
	 * <b>Depth First Search</b><br>
	 * Time:  Average = N.A., Worst = O(|V| + |E|)<br>
	 * Space: Worst = O(|V|) 
	 */
	private void dfs(Graph graph, int v) {
		marked[v] = true;
		count++;
		for (int w : graph.adj(v)) {
			if (!marked[w]) {
				dfs(graph, w);
			}
		}
	}
	
	public boolean marked(int w) {
		if (w < 0 || w >= marked.length) {
			return false;
		}
		return marked[w];
	}
	
	public int count() {
		return count;
	}
}
