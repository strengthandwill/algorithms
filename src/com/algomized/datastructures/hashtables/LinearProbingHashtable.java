package com.algomized.datastructures.hashtables;

import com.algomized.datastructures.SymbolTableAPI;
import com.algomized.datastructures.queues.Queue;

public class LinearProbingHashtable<Key, Value> implements SymbolTableAPI<Key, Value> {
	private int m;
	private int n = 0;
	private int sMax;
	
	private Key[] keys;
	private Value[] values;
	
	public LinearProbingHashtable() {
		this(997, 5);
	}
	
	@SuppressWarnings("unchecked")
	public LinearProbingHashtable(int m, int sMax) {
		this.m = m;
		this.sMax = sMax;
		keys = (Key[]) new Object[m];
		values = (Value[]) new Object[m];
	}
	
	public void put(Key key, Value value) {
		int i = hash(key);
		int s = 0;
		while (keys[i] != null && !keys[i].equals(key)) {
			i = (i + 1) % m;
			s++;
		}
		keys[i] = key;
		values[i] = value;
		n++;
		if (s > sMax || n > m / 8) {
			resize(m * 2);			
		}
	}
	
	public Value get(Key key) {
		int i = hash(key);		
		while (keys[i] != null && !keys[i].equals(key)) {
			i = (i + 1) % m;			
		}		
		return (keys[i] != null) ? values[i] : null;
	}
	
	public void delete(Key key) {
		int i = hash(key);
		while (keys[i] != null && keys[i].equals(key)) {
			i = (i + 1) % m;
		}
		if (keys[i] == null) {
			return;
		}
		
		keys[i] = null;
		values[i] = null;
		int j = (i + 1) % m;		
		while (keys[j] != null) {
			keys[i] = keys[j];
			values[i] = values[j];
			keys[j] = null;
			values[j] = null;
			i = j;
			j = (i + 1) % m;
		}
		n--;
		
		if (n < m / 8) {
			resize(m / 2);
		}
	}
	
	public boolean contains(Key key) {
		int i = hash(key);
		while (keys[i] != null && keys[i].equals(key)) {
			i = (i + 1) % m;
		}
		return keys[i] != null;
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
	
	private void resize(int cap) {
		LinearProbingHashtable<Key, Value> linearProbingHashST = new LinearProbingHashtable<Key, Value>(cap, sMax);
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) {
				linearProbingHashST.put(keys[i], values[i]);
			}
		}
		keys = linearProbingHashST.keys;
		values = linearProbingHashST.values;
		m = linearProbingHashST.m;
	}
	
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) {
				queue.enqueue(keys[i]);
			}
		}
		return queue;
	}
}
