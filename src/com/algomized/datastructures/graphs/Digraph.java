package com.algomized.datastructures.graphs;

import com.algomized.datastructures.linkedlists.LinkedList;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Each vertex can be linked to multiple vertices. Directed such that 
 * v -> w != w -> v.<br>
 * <br>
 * Implemented using an array of adjacent lists which have low time complexity 
 * due to the use of array to allow fast search of the adjacent lists and 
 * low time complexity due to the use of linked list to store the edges.<br>
 * <br>
 * Space: O(|V| + |E|) 
 * </p>
 *
 */
public class Digraph implements GraphAPI {
	private final int vertices;
	private int edges = 0;
	private LinkedList<Integer>[] adj;
	
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
		
		graph.deleteEdge(4, 2);
		System.out.println(graph.vertices);
		System.out.println(graph.edges);
		System.out.println(graph);		
	}	
	
	@SuppressWarnings("unchecked")
	public Digraph(int vertices) {
		this.vertices = vertices;
		adj = (LinkedList<Integer>[]) new LinkedList[vertices];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	public int vertices() {
		return vertices;
	}

	public int edges() {
		return edges;
	}

	/**
	 * <b>Insert Edge</b>
	 * Time:  Worst = O(1)<br>
	 * Space: Worst = O(1)
	 */
	public void addEdge(int v, int w) {
		if (v < 0 || v >= vertices || w < 0 || w >= vertices) {
			return;
		}
		adj[v].add(w);
		edges++;
	}

	/**
	 * <b>Remove Edge</b>
	 * Time:  Worst = O(|E|)<br>
	 * Space: Worst = O(1)
	 */
	public void deleteEdge(int v, int w) {
		if (v < 0 || v >= vertices || w < 0 || w >= vertices) {
			return;
		}
		adj[v].delete(new Integer(w));
		edges--;
	}

	/**
	 * Time:  Worst = O(1)<br>
	 * Space: Worst = O(1)
	 */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	/**
	 * Time:  Worst = O(|V| + |E|)<br>
	 * Space: Worst = O(|V| + |E|)
	 */
	public Digraph reverse() {
		Digraph reverse = new Digraph(vertices);
		for (int v = 0; v < adj.length; v++) {
			for (int w : adj[v]) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < adj.length; i++) {
			strBuf.append(i + ": ");
			if (adj[i] != null) {
				strBuf.append(adj[i].toString() + "\n");
			}
		}
		return strBuf.toString();
	}
}
