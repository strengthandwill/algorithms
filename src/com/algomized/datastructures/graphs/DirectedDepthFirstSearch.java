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
public class DirectedDepthFirstSearch {
	private boolean[] marked;
	private int count;
	
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
		
		DirectedDepthFirstSearch dfs = new DirectedDepthFirstSearch(graph, 0);	
		System.out.println(dfs.count());
	}
	
	public DirectedDepthFirstSearch(Digraph graph, int s) {
		this.marked = new boolean[graph.vertices()];
		dfs(graph, s);
	}
	
	public DirectedDepthFirstSearch(Digraph graph, Iterable<Integer> ss) {
		this.marked = new boolean[graph.vertices()];
		for (int s : ss) {
			if (!marked[s]) {
				dfs(graph, s);
			}
		}
	}
	
	/**
	 * <b>Depth First Search</b><br>
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = O(|V|)
	 */
	private void dfs(Digraph graph, int v) {
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
