package com.algomized.datastructures.graphs;

import com.algomized.datastructures.linkedlists.LinkedList;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Each vertex can be linked to multiple vertices. Unidirectional 
 * such that v -> w = w -> v.<br>
 * <br>
 * Implemented using an array of adjacent lists which have low time complexity 
 * due to the use of array to allow fast search of the adjacent lists and 
 * low time complexity due to the use of linked list to store the edges.<br>
 * <br>
 * Space: O(|V| + |E|) 
 * </p>
 *
 */
public class Graph implements GraphAPI {
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
		
		graph.deleteEdge(0, 2);
		System.out.println(graph.vertices);
		System.out.println(graph.edges);
		System.out.println(graph);		
	}
	
	private LinkedList<Integer>[] adj;
	private final int vertices;
	private int edges = 0;
	
	@SuppressWarnings("unchecked")
	public Graph(int vertices) {
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
	 * <b>Insert Edge</b><br>
	 * Time:  Average = Worst = O(1)<br>
	 * Space: Worst = O(1) 
	 */
	public void addEdge(int v, int w) {
		if (v < 0 || v >= vertices || w < 0 || w >= vertices) {
			return;
		}
		adj[v].add(w);
		adj[w].add(v);
		edges++;
	}
	
	/**
	 * <b>Remove Edge</b><br>
	 * Time:  Average = Worst = O(|E|)<br>
	 * Space: Worst = O(1)
	 */
	public void deleteEdge(int v, int w) {
		if (v < 0 || v >= vertices || w < 0 || w >= vertices) {
			return;
		}
		adj[v].delete(new Integer(w));
		adj[w].delete(new Integer(v));
		edges--;
	}
	
	/**
	 * Time:  Average = Worst = O(1)<br>
	 * Space: Worst = O(1) 
	 */
	public Iterable<Integer> adj(int v) {
		if (v < 0 || v >= vertices) {
			return null;
		}
		return adj[v];
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < adj.length; i++) {
			strBuf.append(i + ": " + adj[i] + "\n");
		}
		return strBuf.toString();
	}
}
