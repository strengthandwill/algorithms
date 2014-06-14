package com.algomized.datastructures.arrays;

public interface ArrayListAPI<Item> {
	public void insert(Item item);
	public void append(Item item);
	public Item get(int index);
	public Item delete(int index);
	public boolean isEmpty();
	public int size();
}
