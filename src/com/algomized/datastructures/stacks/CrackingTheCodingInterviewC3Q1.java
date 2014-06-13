package com.algomized.datastructures.stacks;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Describe how you could use a single buffer to implement three stacks
 * <p/>
 *
 */
public class CrackingTheCodingInterviewC3Q1 {

	public static void main(String[] args) {
		DynamicCapacityStacks stacks = new DynamicCapacityStacks();
		
		stacks.push(0, 1);
		stacks.push(1, 11);		
		stacks.push(2, 21);
		System.out.println(stacks);
		
		stacks.push(0, 2);
		stacks.push(1, 12);
		stacks.push(2, 22);
		System.out.println(stacks);
		
		stacks.push(0, 3);
		stacks.push(1, 13);
		stacks.push(2, 23);		
		System.out.println(stacks);
		
		stacks.push(0, 4);
		stacks.push(0, 5);
		System.out.println(stacks);
		
		stacks.push(1, 14);
		stacks.push(1, 15);
		System.out.println(stacks);		
		
		stacks.push(2, 24);
		stacks.push(2, 25);
		System.out.println(stacks);
		
		System.out.println(stacks.pop(0));
		System.out.println(stacks.pop(0));
		System.out.println(stacks.pop(0));
		
		System.out.println(stacks);
		
		/*
		FixedCapacityStacks stacks = new FixedCapacityStacks();
		stacks.push(0, 01);
		stacks.push(1, 11);
		stacks.push(2, 21);
		
		stacks.push(0, 02);
		stacks.push(1, 12);
		stacks.push(2, 22);

		stacks.push(0, 03);
		stacks.push(1, 13);
		stacks.push(2, 23);
		
		System.out.println(stacks);
		
		System.out.println(stacks.pop(0));
		System.out.println(stacks.pop(0));
		System.out.println(stacks.pop(0));	
		*/	
	}
	
	/**
	 * Stacks with fixed capacity.
	 */
	static class FixedCapacityStacks {
		private final int stackMaxNum;
		private final int capacity;
		private int[] buffer;
		private int[] top;
		private int[] size;

		
		public FixedCapacityStacks() {
			this(15, 3);
		}
		
		public FixedCapacityStacks(int cap, int stackMaxNum) {
			this.stackMaxNum = stackMaxNum;
			top = new int[stackMaxNum];
			size = new int[stackMaxNum];
			for (int i = 0; i < top.length; i++) {
				top[i] = -1;
			}
			buffer = new int[cap];
			capacity = buffer.length / stackMaxNum;
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: O(m), where m is the length of buffer 
		 */
		public void push(int stackNum, int item) {
			if (stackNum < 0 || stackNum > stackMaxNum || top[stackNum] > (buffer.length / 3)) {
				return;
			}
			top[stackNum]++;
			buffer[getIndex(stackNum)] = item;
			size[stackNum]++;
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: O(m), where m is the length of buffer 
		 */
		public int pop(int stackNum) {
			if (stackNum < 0 || stackNum > stackMaxNum) {
				return -1;
			}
			int item = buffer[getIndex(stackNum)];
			top[stackNum]--;
			size[stackNum]--;
			return item;
		}
		
		public int peek(int stackNum) {
			if (stackNum < 0 || stackNum > stackMaxNum) {
				return -1;
			}
			return buffer[getIndex(stackNum)];
		}
		
		public boolean isEmpty(int stackNum) {
			if (stackNum < 0 || stackNum > stackMaxNum) {
				return false;
			}			
			return top[stackNum] == -1;
		}
		
		public int size(int stackNum) {
			if (stackNum < 0 || stackNum > stackMaxNum) {
				return -1;
			}			
			return size[stackNum];
		}
		
		public String toString() {
			StringBuffer strBuf = new StringBuffer();
			for (int i = 0 ; i < buffer.length; i++) {
				strBuf.append("[" + buffer[i] + "]");
			} 
			return strBuf.toString();
		}
		
		private int getIndex(int stackNum) {
			if (top[stackNum] == -1 || top[stackNum] >= capacity) {
				return -1;
			}
			return top[stackNum] + stackNum * capacity;
		}
	}
	
	/**
	 * Stacks with dynamic capacity. If a stack is full, its capacity 
	 * will increase by 1.  
	 */
	static class DynamicCapacityStacks {				
		class StackBlock {
			int capacity;
			int start;
			int top = -1;
			int size;
			
			public StackBlock(int capacity, int start) {
				this.capacity = capacity;
				this.start = start;
			}
			
			public void push(int item) {
				if (top >= capacity) {
					return;
				}
				top++;
				buffer[top + start] = item;
				size++;
			}
			
			public int pop() {
				if (top == -1) {
					return -1;
				}
				int item = buffer[top + start];
				buffer[top + start] = 0;
				top--;
				size--;
				return item;
			}
			
			public int peek() {
				if (top == -1) {
					return -1;
				}
				return buffer[top + start];				
			}
			
			public boolean isFull() {
				return top >= capacity - 1;
			}
		}
		
		private int[] buffer;
		private StackBlock[] blocks;
		
		public DynamicCapacityStacks() {
			this(new int []{3, 4, 5});
		}
		
		public DynamicCapacityStacks(int[] capacity) {
			int totalCapacity = 0;
			for (int i = 0; i < capacity.length; i++) {
				totalCapacity += capacity[i];
			}
			buffer = new int[totalCapacity * 2];			
			blocks = new StackBlock[capacity.length];
			int start = 0;
			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = new StackBlock(capacity[i], start);
				start += capacity[i];
			}
		}
		
		/**
		 * Time:  O(1) average case, O(n) for worst case with shifting
		 * Space: O(1) 
		 */
		public void push(int stackNum, int item) {
			if (stackNum < 0 || stackNum > blocks.length) {
				return;
			}			
			if (blocks[stackNum].isFull()) {
				expand(stackNum);
			}
			blocks[stackNum].push(item);
		}
		
		/**
		 * Time:  O(1)
		 * Space: O(1) 
		 */
		public int pop(int stackNum) {
			if (stackNum < 0 || stackNum > blocks.length) {
				return -1;
			}			
			return blocks[stackNum].pop();
		}
		
		/**
		 * Time:  O(1)
		 * Space: O(1) 
		 */
		public int peek(int stackNum) {
			if (stackNum < 0 || stackNum > blocks.length) {
				return -1;
			}			
			return blocks[stackNum].peek();
		}
		
		private void expand(int stackNum) {
			for (int i = (blocks.length - 1); i > stackNum; i--) {
				shift(i);
			}
			blocks[stackNum].capacity++;
		}
		
		private void shift(int stackNum) {
			int start = blocks[stackNum].start;
			int end = blocks[stackNum].start + blocks[stackNum].capacity - 1;
			for (int i = end; i >= start; i--) { // shift right
				buffer[i + 1] = buffer[i]; 
			}
			blocks[stackNum].start++;
		}
		
		public String toString() {
			StringBuffer strBuf = new StringBuffer();
			for (int i = 0; i < buffer.length; i++) {
				strBuf.append("[" + buffer[i] + "]");
			}
			return strBuf.toString();
		}		
	}
}
