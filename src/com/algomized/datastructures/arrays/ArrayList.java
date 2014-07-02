package com.algomized.datastructures.arrays;

import java.util.Iterator;

import com.algomized.datastructures.ListAPI;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Has low time complexity of an array and low space complexity of a linked list 
 * which increases as it grows.<br>
 * <br>
 * Order is not important. <br>
 * <br>
 * Space: Worst = O(n)
 * </p>
 * 
 */
public class ArrayList<Item> implements ListAPI<Item>, Iterable<Item> {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.insert(5);
		System.out.println(list);
		System.out.println(list.get(2));
		
		list.delete(2);
		System.out.println(list);
	}
	
	private Item[] buffer;
	private int size = 0;
	
	public ArrayList() {
		this(9);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int cap) {
		buffer = (Item[]) new Object[cap];
	}
	
	/**
	 * <b>Insert</b><br>
	 * Time:  Average = Worst = O(n) (Shifting)<br>
	 * Space: Worst = O(n)
	 */
	public void insert(Item item) {
		if (size > buffer.length / 2) {
			resize(buffer.length * 2);
		}		
		shiftRight(0);
		buffer[0] = item;
		size++;
	}
	
	/**
	 * <b>Append</b><br>
	 * Time:  Average = O(1), Worst = O(n) (resizing)<br>
	 * Space: Worst = O(n) (resizing)
	 */
	public void add(Item item) {
		if (size > buffer.length / 2) {
			resize(buffer.length * 2);
		}
		buffer[size] = item;
		size++;
	}
	
	/**
	 * <b>Append</b><br>
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(n) (resizing)
	 */
	public void add(int index, Item item) {
		if (size == 0 || index < 0 || index >= size) {
			return;
		}
		if (size > buffer.length / 2) {
			resize(buffer.length * 2);
		}
		shiftRight(index);
		buffer[index] = item;
		size++;
	}		
	
	/**
	 * <b>Delete</b><br>
	 * Time:  Average = Worst = O(n) (Shifting)<br>
	 * Space: Worst = O(n)
	 */
	public void delete(int index) {
		if (size == 0 || index < 0 || index >= size) {
			return;
		}
		shiftLeft(index);
		if (size < buffer.length / 4) {
			resize(buffer.length / 2);
		}
	}
	
	/**
	 * <b>Delete</b><br>
	 * Time:  Average = Worst = O(n) (Shifting)<br>
	 * Space: Worst = O(n)
	 */
	public void delete(Item item) {
		if (size == 0 || item == null) {
			return;
		}
		for (int i = 0; i < size; i++) {
			if (buffer[i].equals(item)) {
				delete(i);
				return;
			}
		}		
	}
	
	/**
	 * <b>Search</b><br>
	 * Time:  Average = Worst = O(1)<br>
	 * Space: Worst = O(1)
	 */
	public Item get(int index) {
		if (size == 0 || index < 0 || index >= size) {
			return null;
		}
		return buffer[index];
	}
			
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(1)
	 */
	public boolean contains(Item item) {
		if (size == 0 || item == null) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (buffer[i].equals(item)) {
				return true;
			}
		}
		return false;
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
	
	private void shiftLeft(int index) {
		for (int i = index; i < size - 1; i++) {
			buffer[i] = buffer[i + 1];
		}
		size--;
	}
	
	private void shiftRight(int index) {
		for (int i = size - 1; i >= index; i--) {
			buffer[i + 1] = buffer[i];
		}
		
	}

	public Iterator<Item> iterator() {
		return new ArrayListIterator();
	}	
	
	class ArrayListIterator implements Iterator<Item> {
		int current = 0;

		public boolean hasNext() {
			return current < size;
		}

		public Item next() {
			return buffer[current++];
		}

		public void remove() {
		}		
	}
}