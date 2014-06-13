package com.algomized.datastructures.linkedlists;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class Node<Item> {
	public static void main(String[] args) {
		Node<Integer> node = new Node<Integer>(1);
		node.append(2);
		node.append(3);
		node.append(4);
		node.append(5);
		System.out.println(node);
	}
	
	Node<Item> next = null;
	Item item;
	
	public Node(Item item) {
		this.item = item;
	}
	
	/**
	 * Time:  O(n)
	 * Space: 1 node = O(1)
	 */
	public Node<Item> append(Item item) {
		Node<Item> node = this;	
		Node<Item> end = new Node<Item>(item);
		while (node.next != null) {
			node = node.next;
		}
		node.next = end;
		return end;
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		Node<Item> node = this;
		while (node != null) {
			strBuf.append("[" + node.item + "]");			
			node = node.next;
		}
		return strBuf.toString();
	}
}
