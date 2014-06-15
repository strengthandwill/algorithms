package com.algomized.datastructures.queues;

import java.util.Iterator;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Implemented using linked list. FIFO order.<br>
 * <br>
 * Space: Worst = O(n)
 * </p>
 *
 */
public class Queue<Item> implements QueueAPI<Item>, Iterable<Item> {
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		System.out.println(queue);
		
		queue.dequeue();
		System.out.println(queue);
		
		queue.dequeue();
		System.out.println(queue);

		queue.dequeue();
		System.out.println(queue);

		queue.dequeue();
		System.out.println(queue);

		queue.dequeue();
		System.out.println(queue);
	}
	
	private Node first, last;
	private int n = 0;
	
	class Node {
		Item item;
		Node next;
		
		public Node(Item item, Node next) {
			this.item = item;
			this.next = next;
		}
	}

	/**
	 * <b>Insert</b><br>
	 * Time:  O(1)<br>
	 * Space: 1 node = O(1)
	 */
	public void enqueue(Item item) {
		Node node = new Node(item, null);
		if (first == null) {
			first = node;
			last = node;
		} else {
			last.next = node;
			last = last.next;
		}
		n++;
	}

	/**
	 * <b>Delete</b><br>
	 * Time:  O(1)<br>
	 * Space: 1 item = O(1)
	 */	
	public Item dequeue() {
		if (first == null) {
			return null;
		}			
		Item item = first.item;
		first = first.next;
		n--;
		return item;
	}

	/**
	 * Time:  O(1)<br>
	 * Space: 1 item = O(1)
	 */		
	public Item peek() {
		if (first == null) {
			return null;
		}
		return first.item;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return n;
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

	public Iterator<Item> iterator() {
		return new QueueIterator();
	}
	
	class QueueIterator implements Iterator<Item> {
		Node current = first;

		public boolean hasNext() {
			return first != null;
		}

		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove() {
		}		
	}
	
}
