package com.algomized.datastructures.stacks;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * How would you design a stack which, in addition to push and pop, also has a
 * function min which returns the minimum element? Push, pop and min should
 * all operate in O(1) time.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC3Q2 {

	public static void main(String[] args) {
		StackWithMin stack = new StackWithMin();
		//StackWithMinUsingStacks stack = new StackWithMinUsingStacks();
		stack.push(5);
		stack.push(2);
		stack.push(4);
		stack.push(1);
		stack.push(3);
		System.out.println(stack.min());
		
		stack.pop();
		System.out.println(stack.min());
		
		stack.pop();
		System.out.println(stack.min());
		
		stack.pop();
		System.out.println(stack.min());

		stack.pop();
		System.out.println(stack.min());
	}
	
	/**
	 * Node have additional min field to store the min value at the push instance.
	 * Min value is retrieved by peeking at the min value of the top node.
	 *
	 */
	static class StackWithMin {
		class Node {
			int item;
			int min;
			Node next;
			
			public Node(int item, int min, Node next) {
				this.item = item;
				this.min = min;
				this.next = next;
			}
		}
		
		private Node top = null;
		private int size = 0;
		
		/**
		 * Time:  O(1)<br>
		 * Space: 1 int = O(1)
		 */
		public void push(int item) {
			int min;
			if (top == null) {
				min = item;
			} else {
				min = item < top.min ? item : top.min; 
			}
			top = new Node(item, min, top);
			size++;
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: 1 int = O(1)
		 */
		public int pop() {
			if (top == null) {				
				return -1;
			}
			int item = top.item;
			top = top.next;
			size--;
			return item;
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: O(1)
		 */
		public int peek() {
			if (top == null) {
				return -1;
			}
			return top.item;
		}	
		
		/**
		 * Time:  O(1)<br>
		 * Space: O(1)
		 */
		public int min() {
			if (top == null) {
				return -1;
			}
			return top.min;
		}
				
		public boolean isEmpty() {
			return top == null;
		}
		
		public int size() {
			return size;
		}
	}
	
	/**
	 * One stack for values and one stack for min values. MinStack only store 
	 * new min values, so some space is saved.
	 */
	static class StackWithMinUsingStacks {
		private Stack<Integer> stack;
		private Stack<Integer> minStack;
		
		public StackWithMinUsingStacks() {
			stack = new Stack<Integer>();
			minStack = new Stack<Integer>();
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: O(1)
		 * 
		 */
		public void push(int item) {
			if (minStack.isEmpty() || item < minStack.peek()) {
				minStack.push(item);
			}
			stack.push(item);			
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: 1 int = O(1)
		 */
		public int pop() {
			if (stack.isEmpty()) {
				return -1;
			}
			int item = stack.pop();
			if (item == minStack.peek()) {
				minStack.pop();
			}
			return item;
		}
		
		/**
		 * Time:  O(1)
		 * Space: O(1)
		 */
		public int peek() {
			if (stack.isEmpty()) {
				return -1;
			}			
			return stack.peek();
		}
		
		/**
		 * Time:  O(1)
		 * Space: O(1)
		 */
		public int min() {
			if (stack.isEmpty()) {
				return -1;
			}			
			return minStack.peek();
		}
	}
}
