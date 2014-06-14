package com.algomized.datastructures.stacks;

import com.algomized.datastructures.arrays.ArrayList;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * Therefore, in real life, we would likely start a new stack when the previous stack
 * exceeds some threshold. Implement a data structure SetOf Stacks that mimics
 * this. SetOf Stacks should be composed of several stacks and should create a
 * new stack once the previous one exceeds capacity. SetOf Stacks. push() and
 * SetOf Stacks. pop() should behave identically to a single stack (that is, pop()
 * should return the same values as it would if there were just a single stack).<br>
 * FOLLOW UP<br>
 * Implement a function popAt(int index) which performs a pop operation on
 * a specific sub-stack.
 * 
 */
public class CrackingTheCodingInterviewC3Q3 {
	public static void main(String[] args) {
		SetOfStacks stacks = new SetOfStacks();
		stacks.push(1);
		stacks.push(2);
		stacks.push(3);
		stacks.push(4);
		stacks.push(5);
		stacks.push(6);
		stacks.push(7);
		stacks.push(8);
		stacks.push(9);
		System.out.println(stacks);
		
		System.out.println(stacks.pop());
		System.out.println(stacks);
		
		System.out.println(stacks.pop());
		System.out.println(stacks);
		
		System.out.println(stacks.popAt(0));
		System.out.println(stacks);
	}
	
	static class SetOfStacks {
		private ArrayList<StackWithCapacity<Integer>> list;
		private int index = 0;
		
		public SetOfStacks() { 
			list = new ArrayList<StackWithCapacity<Integer>>();
			list.append(new StackWithCapacity<Integer>());			
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: O(1)
		 */
		public void push(int item) {
			if (list.get(index).isFull()) {
				list.append(new StackWithCapacity<Integer>());
				index++;
			}
			list.get(index).push(item);
		}

		/**
		 * Time:  O(1)<br>
		 * Space: O(1)
		 */		
		public int pop() {
			if (isEmpty()) {
				return -1;
			}
			if (list.get(index).isEmpty()) {
				list.delete(index);
				index--;
			}
			return list.get(index).pop();
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: O(1)
		 */		
		public int peek() {
			if (isEmpty()) {
				return -1;
			}
			return list.get(index).peek();
		}
		
		/**
		 * Time:  O(mn) where m is the number of lists and n is the length of each sub-stack<br>
		 * Space: O(1)
		 */		
		public int popAt(int index) {
			if (isEmpty() || index < 0 || index >= list.size()) {
				return -1;
			}
			int item = list.get(index).pop();
			shift(index);			
			return item;
		}
		
		/**
		 * Time:  O(m) where m is the number of list
		 * Space: O(1)
		 */
		private void shift(int index) {
			for (int i = index + 1; i < list.size(); i++) {
				int item = list.get(i).removedBottom();
				list.get(i - 1).push(item);
			}
			if (list.get(this.index).isEmpty()) {				
				list.delete(this.index);
				this.index--;
			}							
		}
				
		public boolean isEmpty() {
			return index == 0 && list.get(index).isEmpty();
		}
		
		public int size() {
			int size = 0;
			for (int i = 0; i < list.size(); i++) {
				size += list.get(i).size();
			}
			return size;
		}
		
		public String toString() {
			StringBuffer strBuf = new StringBuffer();
			for (int i = list.size() - 1; i >= 0; i--) {
				strBuf.append(list.get(i).toString());
			}
			return strBuf.toString();
		}
	}
	
	static class StackWithCapacity<Item> extends Stack<Item> {
		private final int capacity;
		
		public StackWithCapacity() {
			this(3);
		}
		
		public StackWithCapacity(int capacity) {
			super();
			this.capacity = capacity;			
		}
		
		/**
		 * Time:  O(n)
		 * Space: 1 node = O(1)
		 * 
		 */
		public Item removedBottom() {		
			if (isEmpty()) {
				return null;
			}
			if (size() == 1) {
				return pop();
			}
			Node current = first;
			for (int i = 0; i < size - 2; i++) {
				current = current.next;				
			}
			Item item = current.next.item;
			current.next = null;
			size--;
			return item;
		}
		
		public boolean isFull() {
			return size >= capacity;
		}			
	}
}
