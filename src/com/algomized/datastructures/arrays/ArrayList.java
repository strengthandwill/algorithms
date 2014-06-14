package com.algomized.datastructures.arrays;

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
 */
public class ArrayList<Item> implements ArrayListAPI<Item> {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.append(1);
		list.append(2);
		list.append(3);
		list.append(4);
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
	public void append(Item item) {
		if (size > buffer.length / 2) {
			resize(buffer.length * 2);
		}
		buffer[size] = item;
		size++;
	}	
	
	/**
	 * <b>Delete</b><br>
	 * Time:  Average = Worst = O(n) (Shifting)<br>
	 * Space: Worst = O(n)
	 */
	public Item delete(int index) {
		if (size == 0 || index < 0 || index >= size) {
			return null;
		}
		Item item = buffer[index];
		shiftLeft(index);
		if (size < buffer.length / 4) {
			resize(buffer.length / 2);
		}		
		return item;
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
}