package com.algomized.scalability.CrackingTheCodingInterviewC10Q7;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 * @author Poh Kah Kong
 *
 *<p>
 * Imagine a web server for a simplified search engine. This system has 100
 * machines to respond to search queries, which may then call out using
 * processSearch(string query) to another cluster of machines to actually
 * get the result. The machine which responds to a given query is chosen at
 * random, so you can not guarantee that the same machine will always respond
 * to the same request. The method processSearch is very expensive. Design
 * a caching mechanism for the most recent queries. Be sure to explain how you
 * would update the cache when data changes.<br>
 * <br>
 * <b>Single Machine</b>
 * Use below cache.<br>
 * <br>
 * <b>Multiple machines</b>
 * <ul>
 * <li>
 * Each machine has its own cache<br>
 * PRO: No need to machines to communicate with each other<br>
 * CON: May have duplicates<br>
 * </li>
 * <li>
 * All machine has the same cache<br>
 * PRO: No duplicates<br>
 * CON: Expensive update for all the machines, and total cache is actually one cache size
 * </li>
 * <li>
 * Each machine store a segment of cache. The machine to store url u is given by j = hash(u) % 100.
 * Machine i call machine j for the query. Machine j will call search and update its cache if query 
 * not present, and return result to machine i. 
 * PRO: No duplicates<br>
 * CON: Need additional call between machines
 * </li>
 * </ul>
 * <br>
 * <b>Update content</b>
 * Set a timeout value for the cache entries, so that the machines will call search when the time expires
 * and so the cache can always be fresh.
 *</p>
 * 
 */
public class Cache {
	private final int MAX_CACHE = 10;	
	private HashMap<String, Node> map;
	private Node head, tail;
	
	static class Node {
		String query;
		Node next;
		String[] results;
		
		public Node(String query, String[] results) {
			this.query = query;
			this.results = results;
		}
		
		@Override
		public String toString() {
			return "[" + query + "]";
		}
	}
	
	public static void main(String[] args) {
		Cache cache = new Cache();
		cache.add("a", new String[] {"a1", "a2"});
		cache.add("b", new String[] {"b1", "b2"});		
		System.out.println(Arrays.toString(cache.getResults("a")));		
		cache.add("c", new String[] {"c1", "c2"});
		cache.add("d", new String[] {"d1", "d2"});
		cache.add("e", new String[] {"e1", "e2"});		
		cache.remove();
	}
		
	public Cache() {
		map = new HashMap<String, Node>();
	}
	
	public void add(Node node) {
		if (node == null) return;
		if (head == null) {
			head = node;
			tail = node;
			return;
		}		
		tail.next = node;
		tail = node;		
	}
	
	public Node remove() {
		Node remove = head;
		head = head.next;
		if (head == null) tail = null;
		return remove;
	}
	
	public Node remove(Node node) {
		if (head == null) return null;
		if (head.equals(node)) {
			Node remove = head;
			head = null;
			tail = null;
			return remove;
		}
		Node current = head;
		while (current.next != null) {
			if (current.next.equals(node)) {
				Node remove = current.next;
				current.next = current.next.next;
				return remove;
			}
			current = current.next;
		}
		return null;
	}
	
	public void move(Node node) {
		if (node == null || head == null) return;
		if (head.equals(node)) {
			Node move = head;			
			head = head.next;
			add(move);
			return;
		}
		Node current = head;
		while (current.next != null) {
			if (current.next.equals(node)) {
				Node move = current.next;
				current.next = current.next.next;
				add(move);
				return;
			}
			current = current.next;
		}
	}
	
	public void add(String query, String[] results) {
		if (map.containsKey(query)) { // update results
			Node node = map.get(query);
			node.results = results;
			move(node);
			return;
		}		
		if (map.size() >= MAX_CACHE - 1) { // remove oldest
			Node remove = remove();
			map.remove(remove);
		}		
		Node node = new Node(query, results);
		map.put(query, node);
		add(node);
	}
	
	public String[] getResults(String query) {
		if (!map.containsKey(query)) return null;
		Node node = map.get(query); 
		move(node);
		return node.results;
	}
}