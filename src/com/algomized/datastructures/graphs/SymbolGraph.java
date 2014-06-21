package com.algomized.datastructures.graphs;

import com.algomized.datastructures.hashtables.SeparateChainingHashtable;
import com.algomized.datastructures.queues.Queue;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Allows the use of non-integer item (e.g. String) as the vertices of the graph. Implemented by 
 * extending Graph where the vertices and edges are stored as integer. A symbol table is used to 
 * map the integer to the item while a keys array is used to map the index to the item.
 * </p>
 * 
 */
public class SymbolGraph<Item> extends Graph implements SymbolGraphAPI<Item> {

	public static void main(String[] args) {
		String[] keys = {"ATL", "DEN", "DFW", "HOU", "JFK", "LAS", "LAX", "MCO", "ORD", "PHX"};
		SymbolGraph<String> sg = new SymbolGraph<String>(keys);
		sg.addEdge("JFK" ,"MCO");
		sg.addEdge("ORD" ,"DEN");
		sg.addEdge("ORD" ,"HOU");
		sg.addEdge("DFW" ,"PHX");
		sg.addEdge("JFK" ,"ATL");
		sg.addEdge("ORD" ,"DFW");
		sg.addEdge("ORD" ,"PHX");
		sg.addEdge("ATL" ,"HOU");
		sg.addEdge("DEN" ,"PHX");
		sg.addEdge("PHX" ,"LAX");
		sg.addEdge("JFK" ,"ORD");
		sg.addEdge("DEN" ,"LAS");
		sg.addEdge("DFW" ,"HOU");
		sg.addEdge("ORD" ,"ATL");
		sg.addEdge("LAS" ,"LAX");
		sg.addEdge("ATL" ,"MCO");
		sg.addEdge("HOU" ,"MCO");
		sg.addEdge("LAS" ,"PHX");
		System.out.println(sg);
		System.out.println(sg.vertices());
		System.out.println(sg.contains("JFK"));
	}
	
	private SeparateChainingHashtable<Item, Integer> st; // Item -> index
	private Item[] keys; // index -> Item
	
	public SymbolGraph(Item[] keys) {
		super(keys.length);
		st = new SeparateChainingHashtable<Item, Integer>();
		this.keys = keys;
		for (int i = 0; i < keys.length; i++) {
			st.put(keys[i], i);
		}				
	}
	
	public void addEdge(Item v, Item w) {
		super.addEdge(index(v), index(w));
	}
	
	public void deleteEdge(Item v, Item w) {
		super.deleteEdge(index(v), index(w));
		
	}
	
	public Iterable<Item> adj(Item v) {
		Queue<Item> queue = new Queue<Item>();
		for (int w : super.adj(index(v))) {
			queue.enqueue(name(w));
		}
		return queue;
	}

	public boolean contains(Item key) {
		return st.contains(key);
	}
	
	public int index(Item key) {
		return st.get(key);
	}

	public Item name(int index) {
		return keys[index];
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		for (int v = 0; v < super.vertices(); v++) {
			strBuf.append(name(v) + ": ");
			for (int w : super.adj(v)) {
				strBuf.append("[" + name(w) + "]");
			}
			strBuf.append("\n");
		}
		return strBuf.toString();
	}
}