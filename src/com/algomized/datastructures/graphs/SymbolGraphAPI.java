package com.algomized.datastructures.graphs;

public interface SymbolGraphAPI <Item> {
	public int vertices();
	public int edges();
	public void addEdge(Item v, Item w);
	public void deleteEdge(Item v, Item w);
	public Iterable<Item> adj(Item v);
	public boolean contains(Item key);
	public int index(Item key);
	public Item name(int index);	
}
