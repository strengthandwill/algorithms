package com.algomized.datastructures.hashtables;

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
public class SeparateChainingHashtable<K, V> implements HashtableAPI<K, V> {
	private int m;
	private int n = 0;
	private SequentialSearchLinkedListHashtable<K, V>[] sequentialSearchST;
	
	public SeparateChainingHashtable() {
		this(997);
	}
	
	@SuppressWarnings("unchecked")
	public SeparateChainingHashtable(int m) {
		this.m = m;
		sequentialSearchST = (SequentialSearchLinkedListHashtable<K, V>[]) 
				new SequentialSearchLinkedListHashtable[m];
		for (int i=0; i<sequentialSearchST.length; i++) {
			sequentialSearchST[i] = new SequentialSearchLinkedListHashtable<K, V>();
		}		
	}
	
	/**
	 * <b>Insert</b><br>
	 * Time:  Average = O(1), Worst = O(n) (all elements at one linked list)<br>
	 * Space: 1 int = O(1)
	 */
	public void put(K key, V value) {
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
	public V get(K key) {
		return sequentialSearchST[hash(key)].get(key);
	}

	/**
	 * <b>Delete</b><br>
	 * Time:  Average = O(1), Worst = O(n) (all elements at one linked list)<br>
	 * Space: 1 int = O(1)
	 */	
	public void remove(K key) {
		int hash = hash(key);
		if (!sequentialSearchST[hash].contains(key)) {
			return;
		}		
		sequentialSearchST[hash].remove(key);
		n--;
	}
	
	public boolean contains(K key) {
		return sequentialSearchST[hash(key)].contains(key);
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}	
}