package com.algomized.datastructures.linkedlists;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Write code to partition a linked list around a value x, such that all nodes less than 
 * x come before all nodes greater than or equal to x.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC2Q4 {

	public static void main(String[] args) {
		Node<Integer> node = new Node<Integer>(0);
		node.append(1);
		node.append(8);
		node.append(3);
		node.append(9);
		node.append(4);
		node.append(5);
		node.append(6);
		node.append(7);
		node.append(2);
		System.out.println(node);
		//Node<Integer> partitioned = partition(node, 5);
		Node<Integer> partitioned = partitionUsingMergeLists(node, 20);
		System.out.println(partitioned);
	}
	
	/**
	 * Time:  O(n)<br>
	 * Space: 3 nodes = O(1)
	 */
	public static Node<Integer> partition(Node<Integer> node, int x) {
		if (node == null) return null;
		if (node.next == null) return node;
		Node<Integer> head = node, tail = node;
		node = node.next;
		while (node != null) {
			Node<Integer> next = node.next; 
			if (node.item < x) { // insert at the beginning
				node.next = head;
				head = node;
			} else { // insert at the end
				tail.next = node;
				tail = tail.next;
			}
			node = next;
		}
		tail.next = null; // set the end of linked list
		return head;
	}
	/**
	 * Time:  O(n)
	 * Space: 3 nodes = O(1) 
	 */
	public static Node<Integer> partitionUsingMergeLists(Node<Integer> node, int x) {
		if (node == null) return null;
		if (node.next == null) return node;
		Node<Integer> before = null, beforeTail = null;
		Node<Integer> after = null;
		while (node != null) { // partition
			Node<Integer> next = node.next;
			if (node.item < x) { // insert to before list				
				if (before == null) beforeTail = node;
				node.next = before;
				before = node;
			} else { // insert to after list
				node.next = after;
				after = node;
			}
			node = next;
		}
		beforeTail.next = after; // merger list
		return before;
	}
}
