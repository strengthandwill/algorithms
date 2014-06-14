package com.algomized.datastructures.stacks;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Implemented using linked list. LIFO order.<br>
 * <br>
 * Space: Worst = O(n)
 * </p>
 *
 */
public class Stack<Item> implements StackAPI<Item> {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack);
		
		stack.pop();
		System.out.println(stack);
		
		stack.pop();
		System.out.println(stack);

		stack.pop();
		System.out.println(stack);

		stack.pop();
		System.out.println(stack);

		stack.pop();
		System.out.println(stack);
	}
	
	protected Node first = null;
	protected int size = 0;
	
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
	 * Space: O(1)
	 */
	public void push(Item item) {
		first = new Node(item, first);
		size++;
	}
	
	/**
	 * <b>Delete</b><br>
	 * Time:  O(1)<br>
	 * Space: 1 item = O(1)
	 */
	public Item pop() {
		if (first == null) return null;
		Item item = first.item;
		first = first.next;
		size--;
		return item;
	}
	
	/**
	 * Time:  O(1)<br>
	 * Space: O(1)
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
}
