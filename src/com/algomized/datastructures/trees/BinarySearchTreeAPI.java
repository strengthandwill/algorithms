package com.algomized.datastructures.trees;

public interface BinarySearchTreeAPI<K, V> {
	public void put(K key, V value);
	public V get(K key);
	public int size();
	public boolean isEmpty();
}