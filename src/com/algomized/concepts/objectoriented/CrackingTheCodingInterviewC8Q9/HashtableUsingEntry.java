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
 */
public class HashtableUsingEntry<K, V> {
	private List<Entry>[] items;
	private int size = 0;
	
	public static void main(String[] args) {
		HashtableUsingEntry<Integer, String> hashtable = new HashtableUsingEntry<Integer, String>();
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
	
	public HashtableUsingEntry() {
		this(3);
	}
	
	@SuppressWarnings("unchecked")
	public HashtableUsingEntry(int size) {
		items = (LinkedList<Entry>[]) new LinkedList[size];
	}
	
	public void put(K key, V value) {
		int h = hash(key);				
		if (items[h] == null) {
			items[h] = new LinkedList<Entry>();
		}		
		
		Entry entry = get(items[h], key);
		if (entry != null) { // collision so only update value
			entry.value = value;
			return;
		}
		
		// No collision
		size++;	
		items[h].add(new Entry(key, value));	
	}
	
	public V get(K key) {
		int h = hash(key);		
		Entry entry = get(items[h], key);
		if (entry == null) {
			return null;
		}
		return entry.value;
	}
	
	public V remove(K key) {
		int h = hash(key);
		Entry entry = get(items[h], key);
		if (entry == null) {
			return null;
		}
		size--;
		items[h].remove(entry);
		return entry.value;
	}
	
	public boolean contains(K key) {
		int h = hash(key);
		return items[h] != null && (get(items[h], key) != null);
	}
		
	private Entry get(List<Entry> list, K key) {
		if (list == null) {
			return null;
		}
		for (Entry e : list) {
			if (e.key.equals(key)) {
				return e;
			}
		}
		return null;
	}
	
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % items.length;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public Iterable<K> keys() {
		List<K> keys = new LinkedList<K>();
		for (int i = 0; i < items.length; i++) {
			for (Entry e : items[i]) {
				keys.add(e.key);
			}
		}
		return keys;
	}
	
	public Iterable<V> values() {
		List<V> values = new LinkedList<V>();
		for (int i = 0; i < items.length; i++) {
			for (Entry e : items[i]) {
				values.add(e.value);
			}
		}
		return values;
	}
	
	class Entry {
		K key;
		V value;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}		
	}	
}