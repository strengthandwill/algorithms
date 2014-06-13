package com.algomized.datastructures.linkedlists;


public class CrackingTheCodingInterviewC2 {
	public static void main(String[] args) {
		Node<Integer> node = new Node<Integer>(1);
		node.append(2);
		node.append(3);
		node.append(4);
		node.append(5);
		
		System.out.println(node);		
		System.out.println(insert(node, 20));		
		System.out.println(delete(node, 1));		
		System.out.println(delete(node, 3));
	}

	/**
	 * Time:  O(1)
	 * Space: 1 node = O(1) 
	 */
	public static Node<Integer> insert(Node<Integer> head, Integer item) {
		if (head == null) return null;		
		Node<Integer> node = new Node<Integer>(item);
		node.next = head;
		return node;
	}
	
	/**
	 * Time:  O(n)
	 * Space: O(1)
	 */
	public static Node<Integer> delete(Node<Integer> head, Integer item) {
		if (head == null) return null;
		if (head.item.equals(item)) {
			head = head.next;
			return head;
		}
		Node<Integer> current = head;		
		while (current.next != null) {
			if (current.next.item.equals(item)) {
				current.next = current.next.next;
			}
			current = current.next;
		}
		return head;
	}		
}
