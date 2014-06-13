package com.algomized.datastructures.linkedlists;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given a circular linked list, implement an algorithm which returns the node at
 * the beginning of the loop.<br>
 * DEFINITION<br>
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points
 * to an earlier node, so as to make a loop in the linked list.<br>
 * EXAMPLE<br>
 * Input: A - > B - > C - > D - > E - > C [the same C as earlier]<br>
 * Output: C
 * </p>
 *
 */
public class CrackingTheCodingInterviewC2Q6 {
	public static void main(String[] args) {
		Node<Character> node = new Node<Character>('a');
		node.append('b');
		Node<Character> loopBeginningNode = node.append('c');
		node.append('d');
		node.append('e').next = loopBeginningNode;
		System.out.println(getLoopBeginningNode(node).item);
	}
	
	/**
	 * Time:  O(n)<br>
	 * Space: 2 nodes = O(1) 
	 */
	public static Node<Character> getLoopBeginningNode(Node<Character> node) {
		if (node == null || node.next == node) return null;
		Node<Character> second = isCyclic(node); // get collision node
		if (second == null) return null;
		Node<Character> first = node;
		while (first != second) {
			first = first.next;
			second = second.next;
		}
		return first;
	}
	
	/**
	 * Time:  O(n)<br>
	 * Space: 2 nodes = O(1)
	 */
	private static Node<Character> isCyclic(Node<Character> node) {
		if (node == null || node.next == null) return null;
		Node<Character> first = node;
		Node<Character> second = node;
		while (second != null && second.next != null) {
			first = first.next;
			second = second.next.next;
			if (first == second) return first; // is cyclic and return the collision node
		}
		return null; // not cyclic
	}		
}
