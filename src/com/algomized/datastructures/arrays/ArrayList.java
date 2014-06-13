package com.algomized.datastructures.arrays;

public class ArrayList<Item> {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		System.out.println(list);
		System.out.println(list.get(2));
		
		list.remove(2);
		System.out.println(list);
	}
	
	private Item[] buffer;
	private int size = 0;
	
	public ArrayList() {
		this(1);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int cap) {
		buffer = (Item[]) new Object[cap];
	}
	
	public void add(Item item) {
		if (size > buffer.length / 2) {
			resize(buffer.length * 2);
		}
		buffer[size] = item;
		size++;
	}
	
	public Item remove(int index) {
		if (size == 0 || index < 0 || index >= size) {
			return null;
		}
		Item item = buffer[index];
		shift(index);
		return item;
	}
	
	public Item get(int index) {
		if (size == 0 || index < 0 || index >= size) {
			return null;
		}
		if (size < buffer.length / 4) {
			resize(buffer.length / 2);
		}
		return buffer[index];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < size; i++) {
			strBuf.append("[" + buffer[i] + "]");
		}
		return strBuf.toString();
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int cap) {
		Item[] newBuffer = (Item []) new Object[cap];
		for (int i = 0; i < size; i++) {
			newBuffer[i] = buffer[i];
		}
		buffer = newBuffer;
	}
	
	private void shift(int index) {
		for (int i = index; i < size - 1; i++) {
			buffer[i] = buffer[i + 1];
		}
		size--;
	}
}
