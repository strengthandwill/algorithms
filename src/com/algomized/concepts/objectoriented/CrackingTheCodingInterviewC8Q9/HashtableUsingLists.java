package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q9;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Design and implement a hash table which uses chaining (linked lists) to handle
 * collisions.
 * </p>
 * 
 */
public class HashtableUsingLists<K, V> {
	private List<K>[] keys;
	private List<V>[] values;
	private int size = 0;
	
	public static void main(String[] args) {
		HashtableUsingLists<Integer, String> hashtable = new HashtableUsingLists<Integer, String>();
		hashtable.put(0, "this");
		hashtable.put(1, "is");
		hashtable.put(2, "a");
		hashtable.put(3, "hashtable");
		hashtable.put(4, "implemented");
		hashtable.put(5, "using");
		hashtable.put(6, "linked");
		hashtable.put(7, "lists");
		
		System.out.println(hashtable.size());
		System.out.println(hashtable.get(3));
		System.out.println(hashtable.contains(3));
		System.out.println(hashtable.remove(3));
		System.out.println(hashtable.size());
		System.out.println(hashtable.keys());
		System.out.println(hashtable.values());
	}
	
	public HashtableUsingLists() {
		this(3);
	}
	
	@SuppressWarnings("unchecked")
	public HashtableUsingLists(int size) {
		keys = (LinkedList<K>[]) new LinkedList[size];
		values = (LinkedList<V>[]) new LinkedList[size];
	}
	
	public void put(K key, V value) {
		int h = hash(key);		
		if (keys[h] == null && values[h] == null) {
			keys[h] = new LinkedList<K>();
			values[h] = new LinkedList<V>();			
		}		
		int index = keys[h].indexOf(key);
		if (index != -1) { // collision so only update value
			values[h].set(index, value);
			return;
		}
		
		// no collision
		size++;
		keys[h].add(key);
		values[h].add(value);		
	}
	
	public V get(K key) {
		int h = hash(key);		
		if (keys[h] == null) {
			return null;
		}
		
		int index = keys[h].indexOf(key);				
		if (index == -1) {
			return null;
		}
		return values[h].get(index);
	}
	
	public V remove(K key) {
		int h = hash(key);
		if (keys[h] == null) {
			return null;
		}		
		
		int index = keys[h].indexOf(key);
		if (index == -1) {
			return null;
		}
		size--;
		keys[h].remove(index);
		return values[h].remove(index);		
	}
	
	public boolean contains(K key) {
		int h = hash(key);
		return keys[h] != null && (keys[hash(key)].contains(key));		
	}
	
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % keys.length;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public Iterable<K> keys() {
		List<K> list = new LinkedList<K>();
		for (int i = 0; i < keys.length; i++) {
			for (K k : keys[i]) {
				list.add(k);
			}
		}
		return list;
	}
	
	public Iterable<V> values() {
		List<V> list = new LinkedList<V>();
		for (int i = 0; i < values.length; i++) {
			for (V v : values[i]) {
				list.add(v);
			}
		}
		return list;
	}	
}
