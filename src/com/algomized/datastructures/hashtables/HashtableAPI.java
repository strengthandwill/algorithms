package com.algomized.datastructures.hashtables;

public interface HashtableAPI <Key, Value> {
	public void put(Key key, Value value);
	public boolean contains(Key key);
	public Value get(Key key);
	public void delete(Key key);
	public boolean isEmpty();
	public int size();	
	public Iterable<Key> keys();
}
