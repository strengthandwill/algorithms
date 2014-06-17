package com.algomized.datastructures;

public interface ListAPI<Item> {
	public void add(Item item);
	public Item get(int index);
	public void delete(int index);
	public boolean isEmpty();
	public int size();
}
