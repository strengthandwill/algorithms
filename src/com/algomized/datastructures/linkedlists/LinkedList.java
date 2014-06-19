package com.algomized.datastructures.linkedlists;

import java.util.Iterator;

import com.algomized.datastructures.ListAPI;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Has low space complexity but have high time complexity due to
 * traversal of the linked list.<br>
 * <br>
 * Order is not important.<br>
 * <br>
 * Space: Worst = O(n)
 * </p>
 * 
 */
public class LinkedList <Item> implements Iterable<Item>, ListAPI<Item> {
	public static void main(String[] args) {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		linkedList.add(0);
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(9);		
		System.out.println(linkedList);
		System.out.println(linkedList.get(3));
		linkedList.delete(3);
		System.out.println(linkedList);
		linkedList.delete(0);
		System.out.println(linkedList);
	}
	
	private Node first;
	private int size = 0;
	
	class Node {
		Item item;
		Node next;
		
		public Node(Item item) {
			this.item = item;
		}
	}

	/**
	 * <b>Insert</b><br>
	 * Time:  Worst = Average = O(1)<br>
	 * Space: Worst = 1 node = O(1) 
	 */
	public void add(Item item) { 
		Node node = new Node(item);
		node.next = first;
		first = node;
		size++;
	}

	/**
	 * <b>Search</b><br>
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = 1 node = O(1)
	 */
	public Item get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		Node current = first;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.item;
	}	
	
	/**
	 * <b>Delete</b><br>
	 * Time:  Average = Worst = O(n)<br>
	 * Space: 1 node = O(1)
	 */
	public void delete(int index) {
		if (index < 0 || index >= size) {
			return;
		}
		if (index == 0) {
			first = first.next;
		} else {
			Node current = first;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			current.next = current.next.next;
		}
		size--;
	}
	
	/**
	 * <b>Delete</b><br>
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = 1 node = O(1)
	 */
	public void delete(Item item) {
		if (first.item.equals(item)) {
			first = null;
			size--;
			return;
		}
		
		Node current = first;		
		while (current.next != null) {
			if (current.next.item.equals(item)) {
				current.next = current.next.next;
				size--;
				return;
			}			
		}
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		Node current = first;
		while (current != null) {
			strBuf.append("[" + current.item + "]");
			current = current.next;
		}
		return strBuf.toString();
	}

	@Override
	public Iterator<Item> iterator() {
		return new LinkedListIterator();
	}
	
	class LinkedListIterator implements Iterator<Item> {
		private Node current = first;
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
		}		
	}
}
