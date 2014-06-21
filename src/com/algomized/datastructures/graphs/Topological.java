package com.algomized.datastructures.graphs;

public class Topological {
	private Iterable<Integer> order;

	public static void main(String[] args) {
		Digraph graph = new Digraph(13);
		graph.addEdge(2, 3);
		graph.addEdge(0, 6);
		graph.addEdge(0, 1);
		graph.addEdge(2, 0);
		graph.addEdge(11, 12);
		graph.addEdge(9, 12);
		graph.addEdge(9, 10);
		graph.addEdge(9, 11);
		graph.addEdge(3, 5);
		graph.addEdge(8, 7);
		graph.addEdge(5, 4);
		graph.addEdge(0, 5);
		graph.addEdge(6, 4);
		graph.addEdge(6, 9);
		graph.addEdge(7, 6);		
		
		System.out.println(graph.vertices());
		System.out.println(graph.edges());
		System.out.println(graph);
		
		Topological t = new Topological(graph);
		System.out.println(t.isDAG());
		System.out.println(t.order());
	}
	
	public Topological(Digraph graph) {
		DirectedCycle dc = new DirectedCycle(graph);
		if (!dc.hasCycle()) {
			DepthFirstOrder dfo = new DepthFirstOrder(graph);
			order = dfo.reversePost();
		}
	}
	
	public boolean isDAG() {
		return order != null;
	}
	
	public Iterable<Integer> order() {
		return order;
	}
}
