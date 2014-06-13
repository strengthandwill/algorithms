package com.algomized.datastructures.stacks;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Write a program to sort a stack in ascending order (with biggest items on top). 
 * You may use at most one additional stack to hold items, but you may not copy 
 * the elements into any other data structure (such as an array). The stack supports 
 * the following operations: push, pop, peek, and isEmpty
 * </p>
 */
public class CrackingTheCodingInterviewC3Q6 {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(3);
		stack.push(5);
		stack.push(1);
		stack.push(2);
		stack.push(4);
		
		stack = sort(stack);

		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
	
	/**
	 * Time:  O(n^2)<br>
	 * Space: 1 stack (O(n)), 1 int = O(n) + O(1) = O(n)
	 */
	public static Stack<Integer> sort(Stack<Integer> stack) {
		Stack<Integer> stack2 = new Stack<Integer>();
		// move items from stack to stacks to sort items from stack2 to stack
		while (!stack.isEmpty()) {
			if (!stack2.isEmpty() && stack.peek() < stack2.peek()) { // swap greater item of stack2 into stack
				int temp = stack.pop();
				stack.push(stack2.pop());
				stack.push(temp);
			} else { // push greater item of stack into stack2
				stack2.push(stack.pop());
			}
		}
		return stack2;
	}
}
