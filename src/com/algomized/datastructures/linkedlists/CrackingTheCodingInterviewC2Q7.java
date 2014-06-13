package com.algomized.datastructures.linkedlists;

import com.algomized.datastructures.stacks.Stack;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Implement a function to check if a linked list is a palindrome.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC2Q7 {
	public static void main(String[] args) {
		Node<Character> node = new Node<Character>('a');
		node.append('i');
		node.append('b');
		node.append('o');
		node.append('h');
		node.append('p');
		node.append('h');
		node.append('o');
		node.append('b');
		node.append('i');
		node.append('a');
		System.out.println(isPalindromeUsingRunnerTechnique(node));
		//System.out.println(isPalindrome(node));
		System.out.println(isPalindromeRecursive(node));
	}
	
	/**
	 * Time:  O(n/2) + O(n/2) = O(n)<br>
	 * Space: 1 stack (O(n)) + 2 nodes = O(n) 
	 */
	public static boolean isPalindromeUsingRunnerTechnique(Node<Character> node) {
		if (node == null) return false;
		Stack<Character> stack = new Stack<Character>();
		Node<Character> first = node;
		Node<Character> second = node;
		while (second != null && second.next != null) { // first will reach the middle when second reach the end
			stack.push(first.item);
			first = first.next;
			second = second.next.next;
		}
		if (second != null) first = first.next; // odd length		
		while (first != null) {
			if (!first.item.equals(stack.pop())) return false; // check [i] and [n - i - 1] elements
			first = first.next;
		}
		return true;
	}
	
	/**
	 * Time:  O(n) + O(n/2) = O(n)<br>
	 * Space: 1 stack (O(n)) + 2 nodes + 1 int = O(n) 
	 */
	public static boolean isPalindrome(Node<Character> node) {
		if (node == null) return false;
		Stack<Character> stack = new Stack<Character>(); // use stack to reverse the linked list
		Node<Character> current = node;
		int counter = 0;
		while (current != null) {
			stack.push(current.item);
			counter++;
			current = current.next;
		}
		current = node;
		counter = counter / 2; // only need to check half of the elements
		for (int i = 0; i < counter; i++) {
			if (!current.item.equals(stack.pop())) return false; // check [i] and [n - i - 1] elements	
			current = current.next;
		}
		return true;		
	}
	
	/**
	 * Time:  O(n)<br>
	 * Space: n * (1 node, 1 node wrapper ) = O(n)
	 */
	public static boolean isPalindromeRecursive(Node<Character> node) {
		return isPalindromeRecursive(node, new NodeWrapper<Character>(node), new IntWrapper());
	}
	
	private static boolean isPalindromeRecursive(Node<Character> node, 
			NodeWrapper<Character> nw, IntWrapper counter) {
		if (node == null) {
			counter.value /= 2; // only need to check half of the elements
			return true;
		}		
		counter.value++;
		boolean result = isPalindromeRecursive(node.next, nw, counter);
		if (result && counter.value >= 0) {
			if (node.item.equals(nw.node.item)) { // check [i] and [n - i - 1] elements 
				nw.node = nw.node.next;
				counter.value--;
			}
			else result = false;
		}
		return result;
	}
	
	static class NodeWrapper<Item> {
		Node<Item> node;
		public NodeWrapper(Node<Item> node) {
			this.node = node;
		}
	}
	
	static class IntWrapper {
		int value = 0;
	}
}