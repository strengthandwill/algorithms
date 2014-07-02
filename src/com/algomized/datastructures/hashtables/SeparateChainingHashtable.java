package com.algomized.datastructures.hashtables;

import com.algomized.datastructures.SymbolTableAPI;
import com.algomized.datastructures.queues.Queue;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Has low time complexity as an array and small space complexity using hash function.<br>
 * <br>
 * This variant of hashtable is an array with linked list for collision resolution.<br>
 * <br>
 * Space: Worst(n)
 * </p>
 */
public class SeparateChainingHashtable<Key, Value> implements SymbolTableAPI<Key, Value> {
	private int m;
	private int n = 0;
	private SequentialSearchLinkedListHashtable<Key, Value>[] sequentialSearchST;
	
	public SeparateChainingHashtable() {
		this(997);
	}
	
	@SuppressWarnings("unchecked")
	public SeparateChainingHashtable(int m) {
		this.m = m;
		sequentialSearchST = (SequentialSearchLinkedListHashtable<Key, Value>[]) 
				new SequentialSearchLinkedListHashtable[m];
		for (int i=0; i<sequentialSearchST.length; i++) {
			sequentialSearchST[i] = new SequentialSearchLinkedListHashtable<Key, Value>();
		}		
	}
	
	/**
	 * <b>Insert</b><br>
	 * Time:  Average = O(1), Worst = O(n) (all elements at one linked list)<br>
	 * Space: 1 int = O(1)
	 */
	public void put(Key key, Value value) {
		int hash = hash(key);
		if (!sequentialSearchST[hash].contains(key)) {
			n++;			
		}		
		sequentialSearchST[hash].put(key, value);
	}
	
	/**
	 * <b>Search</b><br>
	 * Time:  Average = O(1), Worst = O(n) (all elements at one linked list)<br>
	 * Space: O(1)
	 */
	public Value get(Key key) {
		return sequentialSearchST[hash(key)].get(key);
	}

	/**
	 * <b>Delete</b><br>
	 * Time:  Average = O(1), Worst = O(n) (all elements at one linked list)<br>
	 * Space: 1 int = O(1)
	 */	
	public void delete(Key key) {
		int hash = hash(key);
		if (!sequentialSearchST[hash].contains(key)) {
			return;
		}		
		sequentialSearchST[hash].delete(key);
		n--;
	}
	
	public boolean contains(Key key) {
		return sequentialSearchST[hash(key)].contains(key);
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}	
	
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < sequentialSearchST.length; i++) {
			Iterable<Key> keys = sequentialSearchST[i].keys();
			if (keys != null) {
				for (Key key : keys) {
					queue.enqueue(key);
				}			
			}
		}
		return queue;
	}
}