package com.algomized.datastructures.linkedlists;

import com.algomized.datastructures.hashtables.SeparateChainingHashtable;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Write code to delete duplicates from an unsorted linked list.<br>
 * FOLLOW UP<br>
 * How would you solve this problem if a temporary buffer is not allowed?
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC2Q1 {

	public static void main(String[] args) {
		ListNode<Character> node = new ListNode<Character>('h');
		node.append('e');		
		node.append('l');
		node.append('l');
		node.append('o');
		node.append('w');
		node.append('o');
		node.append('r');
		node.append('l');
		node.append('d');
		System.out.println(node.toString());
		//removeDuplicates(node);
		//removeDuplicatesUsingArray(node);
		//removeDuplicatesUsingBitVector(node);
		removeDuplicatesUsingHashtable(node);
		System.out.println(node.toString());
	}
	
	/**
	 * Time:  O(n^2)<br>
	 * Space: O(1)
	 */
	public static void removeDuplicates(ListNode<Character> node) {
		if (node == null || node.next == null) return;
		while (node != null) {
			ListNode<Character> second = node;
			while (second != null && second.next != null) {
				if (node.item.equals(second.next.item)) second.next = second.next.next; // duplicate found
				else second = second.next;				
			}
			node = node.next;			
		}
	}
	
	/**
	 * Time:  O(n)<br>
	 * Space: 256 booleans
	 */
	public static void removeDuplicatesUsingArray(ListNode<Character> node) {
		if (node == null || node.next == null) return;
		boolean[] found = new boolean[256];				
		found[node.item] = true;
		while (node.next != null) {
			int value = node.next.item;
			if (!found[value]) { // first found
				found[value] = true;
				node = node.next;
			} else { // duplicate found
				node.next = node.next.next;
			}
		}
	}
	
	/**
	 * Time:  O(n)<br>
	 * Space: 1 integer for 32 values
	 */
	public static void removeDuplicatesUsingBitVector(ListNode<Character> node) {
		if (node == null || node.next ==null) return;
		int checker = 0;
		checker |= (1 << (node.item - 'a'));
		while (node.next != null) {
			int value = node.next.item - 'a';
			if ((checker & (1 << value)) == 0) { // first found
				checker |= (1 << value);
				node = node.next;
			} else { // duplicate found
				node.next = node.next.next;
			}
		}
	}
	
	/**
	 * Time:  O(n)<br>
	 * Space: O(m), where m is the unique char
	 */
	public static void removeDuplicatesUsingHashtable(ListNode<Character> node) {
		if (node == null || node.next == null) return;
		SeparateChainingHashtable<Integer, Character> hashtable = new SeparateChainingHashtable<Integer, Character>();
		hashtable.put((int) node.item, node.item);
		while (node.next != null) {
			int key = node.next.item;
			if (!hashtable.contains(key)) { // first found
				hashtable.put(key, node.next.item);
				node = node.next;
			} else { // duplicate found
				node.next = node.next.next;
			}
		}
	}
}
