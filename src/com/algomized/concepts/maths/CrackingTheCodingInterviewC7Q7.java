package com.algomized.concepts.maths;

import com.algomized.datastructures.arrays.ArrayList;
import com.algomized.datastructures.queues.Queue;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Design an algorithm to find the kth number such that the only prime factors are
 * 3,5, and 7.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC7Q7 {
	public static void main(String[] args) {
		System.out.println(getNumber(9));
		System.out.println(getNumberUsingList(9));		
		System.out.println(getNumberUsingQueues(9));
	}
	
	/**
	 * Time:  Average = Worst = O(k)<br>
	 * Space: Worst = O(k) 
	 */
	public static int getNumberUsingQueues(int k) {
		if (k <= 0) {
			return -1;
		}
		
		Queue<Integer> q3 = new Queue<Integer>();
		Queue<Integer> q5 = new Queue<Integer>();
		Queue<Integer> q7 = new Queue<Integer>();
		q3.enqueue(1);		
		int min = -1;
		for (int i = 0; i < k; i++) {
			min = removeMin(q3, q5, q7);
		}
		return min;
	}
	
	private static int removeMin(Queue<Integer> q3, Queue<Integer> q5, Queue<Integer> q7) {
		int q3Top = q3.peek() != null ? q3.peek() : Integer.MAX_VALUE;
		int q5Top = q5.peek() != null ? q5.peek() : Integer.MAX_VALUE;
		int q7Top = q7.peek() != null ? q7.peek() : Integer.MAX_VALUE;
				
		int min = Math.min(Math.min(q3Top, q5Top), q7Top);
		if (q3Top == min) {
			q3.dequeue();
			enqueue(q3, q5, q7, min * 3);
			enqueue(q5, q3, q7, min * 5);
		} else if (q5Top == min) {
			q5.dequeue();
			enqueue(q5, q3, q7, min * 5);
		} else if (q7Top == min) {
			q7.dequeue();
		}
		enqueue(q7, q3, q5, min * 7);
		return min;
	}
	
	private static void enqueue(Queue<Integer> q1, Queue<Integer> q2, Queue<Integer> q3, int n) {
		boolean q1Found = q1.peek() != null && q1.peek() == n;
		boolean q2Found = q2.peek() != null && q2.peek() == n;
		boolean q3Found = q3.peek() != null && q3.peek() == n;
		if (!q1Found && !q2Found && !q3Found) {
			q1.enqueue(n);
		}
	}
	
	/**
	 * Time:  Average = Worst = O(k) * O(k) [arraylist shifting] = O(k^2)<br>
	 * Space: Worst = O(k)
	 */
	public static int getNumberUsingList(int k) {
		if (k <= 0) {
			return -1;
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(7);
		
		for (int i = 0; i < k; i++) {
			int l = i;
			for (int j = 3; j <= 7; j += 2) {
				int n = list.get(i) * j;
				while (l < list.size() && n > list.get(l)) {
					l++;
				}
				if (l >= list.size()) {
					list.add(n);
				} else if (n != list.get(l)) {
					list.add(l, n);
				}
			}					
		}		
		return list.get(k-1);
	}
	
	/**
	 * Time:  Worst = Average = O(n^2)<br>
	 * Space: Worst = O(1) 
	 */
	public static int getNumber(int k) {
		if (k <= 0) {
			return -1;
		}
		int count = 0;
		int n = 0; // first number
		while (count < k) {
			n++;
			if (checkPrime(n)) { // find next number
				count++;
			}			
		}		
		return n;
	}
	
	private static boolean checkPrime(int n) {
		for (int i = 3; i <= 7; i += 2) { // check for prime 3, 5, 6
			while (n % i == 0) {
				n /= i;
			}
		}
		return n == 1; // n has other prime factor if n is not equal to 1
	}

}
