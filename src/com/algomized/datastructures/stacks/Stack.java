package com.algomized.datastructures.stacks;

/**
 * 
 * @author Poh Kah Kong
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
	
	protected Node top = null;
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
	 * Time:  O(1)<br>
	 * Space: O(1)
	 */
	public void push(Item item) {
		top = new Node(item, top);
		size++;
	}
	
	/**
	 * Time:  O(1)<br>
	 * Space: 1 item = O(1)
	 */
	public Item pop() {
		if (top == null) return null;
		Item item = top.item;
		top = top.next;
		size--;
		return item;
	}
	
	/**
	 * Time:  O(1)<br>
	 * Space: O(1)
	 */	
	public Item peek() {
		if (top == null) {
			return null;
		}		
		return top.item;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		Node current = top;
		while (current != null) {
			strBuf.append("[" + current.item + "]");
			current = current.next;
		}
		return strBuf.toString();
	}
}
