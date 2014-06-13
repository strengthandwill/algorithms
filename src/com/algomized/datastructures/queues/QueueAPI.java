package com.algomized.datastructures.queues;

public interface QueueAPI<Item> {
	public void enqueue(Item item);
	public Item dequeue();
	public Item peek();
	public boolean isEmpty();
	public int size();
}
