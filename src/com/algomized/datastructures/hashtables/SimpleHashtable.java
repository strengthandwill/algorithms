package com.algomized.datastructures.hashtables;

public class SimpleHashtable<K, V> implements HashtableAPI<K, V> {
	private java.util.Hashtable<K, V> hashtable = new java.util.Hashtable<K, V>();
	
	public void put(K key, V value) {
		hashtable.put(key, value);	
	}

	public boolean contains(K key) {		
		return hashtable.contains(key);
	}
	
	public V get(K key) {
		return hashtable.get(key);
	}

	public void remove(K key) {
		hashtable.remove(key);
	}

	public boolean isEmpty() {
		return hashtable.isEmpty();
	}

	public int size() {
		return hashtable.size();
	}
}
