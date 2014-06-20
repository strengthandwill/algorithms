package com.algomized.datastructures.graphs;

public interface GraphAPI {
	public int vertices();
	public int edges();
	public void addEdge(int v, int w);
	public void deleteEdge(int v, int w);
	public Iterable<Integer> adj(int v);
}
