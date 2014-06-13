package com.algomized.datastructures.queues;

import com.algomized.datastructures.stacks.Stack;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Implement a MyQueue class which implements a queue using two stacks.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC3Q5 {
	public static void main(String[] args) {
		MyQueueUsingTwoStacks queue = new MyQueueUsingTwoStacks();
		//MyQueue queue = new MyQueue();
		queue.enqueue(1);
		queue.enqueue(2);		
		queue.enqueue(3);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());		
		
		queue.enqueue(4);
		System.out.println(queue.dequeue());
		
		queue.enqueue(5);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
	}
	
	/**
	 * Uses 2 stacks to maintain order: stackLIFO to enqueue item in LIFO order 
	 * and stackFIFO to dequeue item in FIFO order.
	 */
	static class MyQueueUsingTwoStacks {
		private Stack<Integer> stackLIFO, stackFIFO;
		
		public MyQueueUsingTwoStacks() {
			stackLIFO = new Stack<Integer>();
			stackFIFO = new Stack<Integer>();
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: O(1)
		 */
		public void enqueue(int item) {
			stackLIFO.push(item);
		}
		
		/**
		 * Time:  O(1) for average case, O(n) for worst case<br>
		 * Space: O(1)
		 */
		public int dequeue() {
			shiftStacks();
			return stackFIFO.pop();
		}
		
		public boolean isEmpty() {
			return stackLIFO.isEmpty() && stackFIFO.isEmpty();
		}
		
		public int size() {
			return stackLIFO.size() + stackFIFO.size();
		}
		
		private void shiftStacks() {
			if (!stackFIFO.isEmpty()) {
				return;
			}
			while (!stackLIFO.isEmpty()) {
				stackFIFO.push(stackLIFO.pop());
			}
		}
	}
	
	/**
	 * Uses one stack to store the items. During enqueuing of item, the stack is reversed to LIFO order.
	 * During dequeue of item, the stack is reversed to FIFO order.
	 */
	static class MyQueue {
		private Stack<Integer> stack;
		private boolean isEnqueue;
		
		public MyQueue() {
			stack = new Stack<Integer>();
			isEnqueue = true;
		}
		
		/**
		 * Time:  O(1) for average case, O(n) for worst case<br>
		 * Space: O(1)
		 */
		public void enqueue(int item) {			
			if (!isEnqueue) { // reverse order if in FIFO order
				reverse();
				isEnqueue = true;
			}
			stack.push(item); // enqueue item in LIFO order
		}
		
		/**
		 * Time:  O(1) for average case, O(n) for worst case<br>
		 * Space: O(1)
		 */
		public int dequeue() {
			if (isEnqueue) { // reverse order if in LIFO order
				reverse();
				isEnqueue = false;
			}
			return stack.pop();	// dequeue item in FIFI order
		}
		
		public boolean isEmpty() {
			return stack.isEmpty();
		}
		
		public int size() {
			return stack.size();
		}
		
		/**
		 * Time: O(n)<br>
		 * Space O(1)
		 */
		private void reverse() {
			Stack<Integer> stack2 = new Stack<Integer>();
			while (!stack.isEmpty()) {
				stack2.push(stack.pop());
			}
			stack = stack2;
		}
	}

}
