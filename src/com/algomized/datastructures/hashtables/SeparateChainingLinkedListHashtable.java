package com.algomized.datastructures.hashtables;


public class SeparateChainingLinkedListHashtable<K, V> implements HashtableAPI<K, V> {
	private int m;
	private int n = 0;
	private SequentialSearchLinkedListHashtable<K, V>[] sequentialSearchST;
	
	public SeparateChainingLinkedListHashtable() {
		this(997);
	}
	
	@SuppressWarnings("unchecked")
	public SeparateChainingLinkedListHashtable(int m) {
		this.m = m;
		sequentialSearchST = (SequentialSearchLinkedListHashtable<K, V>[]) new SequentialSearchLinkedListHashtable[m];
		for (int i=0; i<sequentialSearchST.length; i++) {
			sequentialSearchST[i] = new SequentialSearchLinkedListHashtable<K, V>();
		}		
	}
	
	public void put(K key, V value) {
		int hash = hash(key);
		if (!sequentialSearchST[hash].contains(key)) {
			n++;
		}
		sequentialSearchST[hash].put(key, value);
	}
	
	public V get(K key) {
		return sequentialSearchST[hash(key)].get(key);
	}

	public void remove(K key) {
		int hash = hash(key);
		if (sequentialSearchST[hash].contains(key)) {
			n--;
		}		
		sequentialSearchST[hash].remove(key);
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