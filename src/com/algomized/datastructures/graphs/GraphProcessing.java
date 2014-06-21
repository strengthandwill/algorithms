package com.algomized.datastructures.graphs;

import java.util.Iterator;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Some common graph processing methods.
 * </p>
 *
 */
public class GraphProcessing {

	public static void main(String[] args) {
		Graph graph = new Graph(13);
		graph.addEdge(0, 0);
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
		
		System.out.println(degree(graph, 0));
		System.out.println(maxDegree(graph));
		System.out.println(avgDegree(graph));
		System.out.println(numberOfSelfLoop(graph));
	}
	
	/**
	 * Time:  Worst = O(|E|)<br>
	 * Space: Worst = 1 int = O(1) 
	 */
	public static int degree(Graph graph, int v) {
		if (graph == null || v < 0 || v >= graph.vertices()) {
			return -1;
		}
		int degree = 0;
		Iterator<Integer> i = graph.adj(v).iterator();
		while (i.hasNext()) {
			degree++;
			i.next();
		}
		return degree;
	}
	
	/**
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = 1 int = O(1) 
	 */
	public static int maxDegree(Graph graph) {
		int maxDegree = 0;
		for (int s = 0; s < graph.vertices(); s++) {
			int degree = degree(graph, s);
			if (degree > maxDegree) {
				maxDegree = degree; 
			}
		}
		return maxDegree;
	}

	/**
	 * Time:  Worst = O(1)<br>
	 * Space: Worst = 1 int = O(1) 
	 */	
	public static int avgDegree(Graph graph) {
		return 2 * graph.edges() / graph.vertices();		
	}
	
	/**
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = 1 int = O(1) 
	 */
	public static int numberOfSelfLoop(Graph graph) {
		int numberOfSelfLoop = 0;
		for (int s = 0; s < graph.vertices(); s++) {
			for (int w : graph.adj(s)) {
				if (w == s) {
					numberOfSelfLoop++;
				}
			}
		}
		return numberOfSelfLoop / 2;
	}
}
