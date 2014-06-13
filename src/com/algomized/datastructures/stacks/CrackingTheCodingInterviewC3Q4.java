package com.algomized.datastructures.stacks;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of 
 * different sizes which can slide onto any tower. The puzzle starts with disks sorted 
 * in ascending order of size from top to bottom (i.e., each disk sits on top of an 
 * even larger one). You have the following constraints:<br>
 * (1) Only one disk can be moved at a time.<br>
 * (2) A disk is slid off the top of one tower onto the next tower.<br>
 * (3) A disk can only be placed on top of a larger disk.<br>
 * Write a program to move the disks from the first tower to the last using stacks.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC3Q4 {
	private static Stack<Integer> stack1;
	private static Stack<Integer> stack2;
	private static Stack<Integer> stack3;		

	public static void main(String[] args) {
		stack1 = new Stack<Integer>();
		stack2 = new Stack<Integer>();
		stack3 = new Stack<Integer>();		
		stack1.push(3);
		stack1.push(2);
		stack1.push(1);
		print();
		
		towerOfHanoi(stack1.size(), stack1, stack2, stack3);		
	}
	
	/**
	 * Time:  2 * n^2 - 1 = O(n^2)<br>
	 * Space: O(n^2)
	 */
	public static void towerOfHanoi(int n, Stack<Integer> src, Stack<Integer> aux, Stack<Integer> dest) {
		if (n == 1) {
			dest.push(src.pop());
			print();
			return;
		}		
		towerOfHanoi(n - 1, src, dest, aux);
		towerOfHanoi(1, src, aux, dest);
		towerOfHanoi(n - 1, aux, src, dest);
		print();
	}
	
	public static void print() {
		System.out.println("Stack1: " + stack1);
		System.out.println("Stack2: " + stack2);
		System.out.println("Stack3: " + stack3);		
		System.out.println("--------------------------");
	}
}
