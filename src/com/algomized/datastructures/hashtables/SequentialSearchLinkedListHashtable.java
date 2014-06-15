package com.algomized.datastructures.hashtables;

import com.algomized.datastructures.queues.Queue;

public class SequentialSearchLinkedListHashtable<Key, Value> implements HashtableAPI<Key, Value> {	
	private Node first;	
	private int n = 0;
	
	class Node {
		Key key;
		Value value;
		Node next;
		
		public Node(Key key, Value value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	public void put(Key key, Value value) {
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
	
	public Value get(Key key) {		
		for (Node x = first; x != null; x = x.next) {
			if (x.key.equals(key)) {
				return x.value; // search hit
			}
		}		
		return null; // search miss
	}
	
	public boolean contains(Key key) {
		for (Node x= first; x != null; x = x.next) {
			if (x.key.equals(key)) { // search hit
				return true;
			}
		}
		return false;
	}
	
	public void delete(Key key) {		
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
	
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		Node current = first;
		while (current != null) {
			queue.enqueue(current.key);
			current = current.next;
		}
		return queue;
	}
}
