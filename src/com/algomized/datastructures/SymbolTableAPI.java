package com.algomized.datastructures;

public interface SymbolTableAPI<Key, Value> {
	public void put(Key key, Value value);
	public Value get(Key key);
	public void delete(Key key);
	public boolean contains(Key key);
	public int size();
	public boolean isEmpty();
	public Iterable<Key> keys();
}
