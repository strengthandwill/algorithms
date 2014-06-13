package com.algomized.datastructures.linkedlists;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Implement an algorithm to delete a node in the middle of a singly linked list,
 * given only access to that node.<br>
 * EXAMPLE<br>
 * Input: the node c from the linked list a->b->c->d->e<br>
 * Result: nothing is returned, but the new linked list looks like a- >b- >d->e<br>
 * 
 */
public class CrackingTheCodingInterviewC2Q3 {

	public static void main(String[] args) {
		Node<Character> node = new Node<Character>('a');
		node.append('b');
		Node<Character> delete = node.append('c');
		node.append('d');
		node.append('e');
		System.out.println(node);
		delete(delete);
		System.out.println(node);
	}
	
	/**
	 * Time:  O(1)<br>
	 * Space: O(1)
	 */
	public static void delete(Node<Character> node) {
		if (node == null || node.next == null) return;
		node.item = node.next.item; // copy item
		node.next = node.next.next; // remove node node.next
	}

}
