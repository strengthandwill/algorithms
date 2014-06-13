package com.algomized.datastructures.hashtables;


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
	
	public void put(K key, V value) {
		int hash = hash(key);
		if (!sequentialSearchST[hash].containsKey(key)) {
			n++;			
		}		
		sequentialSearchST[hash].put(key, value);
	}
	
	public V get(K key) {
		return sequentialSearchST[hash(key)].get(key);
	}

	public void remove(K key) {
		int hash = hash(key);
		if (!sequentialSearchST[hash].containsKey(key)) {
			return;
		}		
		sequentialSearchST[hash].remove(key);
		n--;
	}
	
	public boolean containsKey(K key) {
		return sequentialSearchST[hash(key)].containsKey(key);
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