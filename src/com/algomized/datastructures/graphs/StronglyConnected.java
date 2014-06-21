package com.algomized.datastructures.graphs;

import com.algomized.datastructures.queues.Queue;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Determines whether a directed graph is strongly connected using Kosarajus's algorithm.<br>
 * <br>
 * Steps:<br>
 * 1. Reverse graph [O(|V| + |E|)]<br>
 * 2. Depth first order on the reversed graph to create reverse post order [O(|V| + |E|)]<br>
 * 3. Depth first search on the graph on the reverse post order [O(|V| + |E|)]<br>
 * <br>
 * Time:  Worst = O(|V| + |E|)<br>
 * Space: Worst = O(|V| + |E|)
 * </p>
 *
 */
public class StronglyConnected {
	private boolean[] marked;
	private int[] id;
	private int count;
	private Queue<Integer>[] components;

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
		
		StronglyConnected scc = new StronglyConnected(graph);
		System.out.println(scc.count());
		for (int i = 0; i < scc.count(); i++) {
			System.out.println(scc.component(i));
		}
	}

	@SuppressWarnings("unchecked")
	public StronglyConnected(Digraph graph) {
		marked = new boolean[graph.vertices()];
		id = new int[graph.vertices()];
				
		/* Kosarajus's algorithm */
		DepthFirstOrder dfo = new DepthFirstOrder(graph.reverse());
		for (int s : dfo.reversePost()) {
			if (!marked[s]) {
				dfs(graph, s);
				count++;
			}
		}
		/* end of Kosarajus's algorithm */
		
		// compute components
		components = (Queue<Integer>[]) new Queue[count];
		for (int i = 0; i < components.length; i++) {
			components[i] = new Queue<Integer>();
		}		
		for (int i = 0; i < id.length; i++) {
			components[id[i]].enqueue(i);
		}
	}
		
	/**
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = O(|V|)  
	 */
	private void dfs(Digraph graph, int v) {
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
	
	public Iterable<Integer> component(int id) {
		return components[id];
	}
}
