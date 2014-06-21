package com.algomized.datastructures.graphs;

import com.algomized.datastructures.queues.Queue;
import com.algomized.datastructures.stacks.Stack;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Determines the depth first order of a directed graph if present.
 * There are three vertex orderings namely pre-order, post-order and
 * post-order.
 * </p>
 *
 */
public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;
	
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
		
		DepthFirstOrder dfs = new DepthFirstOrder(graph);	
		System.out.println(dfs.pre());
		System.out.println(dfs.post());
		System.out.println(dfs.reversePost());
	}
	
	public DepthFirstOrder(Digraph graph) {
		marked = new boolean[graph.vertices()];
		pre = new Queue<Integer>();
		post = new Queue<Integer>();
		reversePost = new Stack<Integer>();
		for (int s = 0; s < graph.vertices(); s++) {
			if (!marked[s]) {
				dfs(graph, s);
			}
		}
	}
	
	public DepthFirstOrder(Digraph graph, Iterable<Integer> ss) {
		marked = new boolean[graph.vertices()];
		pre = new Queue<Integer>();
		post = new Queue<Integer>();
		reversePost = new Stack<Integer>();
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
		pre.enqueue(v);
		for (int w : graph.adj(v)) {
			if (!marked[w]) {
				dfs(graph, w);
			}
		}
		post.enqueue(v);
		reversePost.push(v);		
	}
	
	public boolean marked(int w) {
		if (w < 0 || w >= marked.length) {
			return false;
		}
		return marked[w];
	}
	
	public Iterable<Integer> pre() {
		return pre;
	}
	
	public Iterable<Integer> post() {
		return post;
	}
	
	public Iterable<Integer> reversePost() {
		return reversePost;
	}
}
