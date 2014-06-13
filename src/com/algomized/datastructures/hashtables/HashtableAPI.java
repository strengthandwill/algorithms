package com.algomized.datastructures.hashtables;

public interface HashtableAPI <K, V> {
	public void put(K key, V value);
	public boolean containsKey(K key);
	public void remove(K key);
	public boolean isEmpty();
	public int size();	
}
