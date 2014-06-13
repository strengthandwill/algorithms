package com.algomized.datastructures.hashtables;

public class SequentialSearchLinkedListHashtable<K, V> implements HashtableAPI<K, V> {	
	private Node first;	
	private int n = 0;
	
	class Node {
		K key;
		V value;
		Node next;
		
		public Node(K key, V value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	public void put(K key, V value) {
		for (Node x = first; x != null; x = x.next) {
			if (x.key.equals(key)) { // search hit
				x.value = value;
				return;
			}
		}
		// first miss
		Node next = first;
		first = new Node(key, value, next);
		n++;
	}
	
	public V get(K key) {		
		for (Node x = first; x != null; x = x.next) {
			if (x.key.equals(key)) {
				return x.value; // search hit
			}
		}		
		return null; // search miss
	}
	
	public boolean containsKey(K key) {
		for (Node x= first; x != null; x = x.next) {
			if (x.key.equals(key)) { // search hit
				return true;
			}
		}
		return false;
	}
	
	public void remove(K key) {		
		if (first.key.equals(key)) { // search hit at first node
			first = first.next;
			n--;
			return;
		}
				
		for (Node prev = first, x = first.next; x != null; prev = x, x = x.next) {
			if (x.equals(key)) { // search hit
				prev.next = x.next;
				n--;
				return;
			}
		}
		// search miss
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return n;
	}
}
