package com.algomized.datastructures.graphs;

import com.algomized.datastructures.queues.Queue;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * The shortest degree of separation between two non-integer vertices 
 * using SymbolGraph and Breath First Search.
 * </p> 
 *
 */
public class DegreeOfSeparation<Item> {
	private SymbolGraph<Item> sg;
	private BreathFirstPaths bfp;
	
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
		
		DegreeOfSeparation<String> dos = new DegreeOfSeparation<String>(sg, "JFK");
		System.out.println("LAS: " + dos.degreeOfSepartion("LAS"));
		System.out.println("DFW: " + dos.degreeOfSepartion("DFW"));

	}
	
	public DegreeOfSeparation(SymbolGraph<Item> sg, Item key) {
		this.sg = sg;
		this.bfp = new BreathFirstPaths(sg, sg.index(key));
	}
		
	public Iterable<Item> degreeOfSepartion(Item key) {
		Queue<Item> queue = new Queue<Item>();
		for (int v : bfp.pathTo(sg.index(key))) {
			queue.enqueue(sg.name(v));
		}
		return queue;
	}	
}
