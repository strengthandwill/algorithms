package com.algomized.datastructures.stacks;

public interface StackAPI<Item> {
	public void push(Item item);
	public Item pop();
	public Item peek();
	public boolean isEmpty();
	public int size();
}
