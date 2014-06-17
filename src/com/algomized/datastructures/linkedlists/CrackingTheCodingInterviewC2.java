package com.algomized.datastructures.linkedlists;


public class CrackingTheCodingInterviewC2 {
	public static void main(String[] args) {
		ListNode<Integer> node = new ListNode<Integer>(1);
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
	 * <b>Insert</b><br>
	 * Time:  O(1)<br>
	 * Space: 1 node = O(1) 
	 */
	public static ListNode<Integer> insert(ListNode<Integer> head, Integer item) {
		if (head == null) return null;		
		ListNode<Integer> node = new ListNode<Integer>(item);
		node.next = head;
		return node;
	}
	
	/**
	 * <b>Delete<b><br>
	 * Time:  O(n)<br>
	 * Space: O(1)
	 */
	public static ListNode<Integer> delete(ListNode<Integer> head, Integer item) {
		if (head == null) return null;
		if (head.item.equals(item)) {
			head = head.next;
			return head;
		}
		ListNode<Integer> current = head;		
		while (current.next != null) {
			if (current.next.item.equals(item)) {
				current.next = current.next.next;
			}
			current = current.next;
		}
		return head;
	}		
}