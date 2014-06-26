package com.algomized.concepts.bitsmanipulation;

import com.algomized.datastructures.arrays.ArrayList;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * An array A contains all the integers from 0 to n, except for one number which is
 * missing. In this problem, we cannot access an entire integer in A with a single
 * operation. The elements of A are represented in binary, and the only operation
 * we can use to access them is "fetch the jth bit of A[i]," which takes constant
 * time. Write code to find the missing integer. Can you do it in 0(n) time?
 * </p>
 *
 */
public class CrackingTheCodingInterviewC5Q7 {

	public static void main(String[] args) {		
		int[] a = {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		System.out.println(findMissing(a));
		System.out.println(findMissingRecursive(a));
		System.out.println(findMissingUsingParity(a, 9));		
	}
		
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = 4 int = O(1) 
	 */
	public static int findMissing(int[] a) {
		if (a == null) {
			return -1;
		}
		ArrayList<Integer> list = toList(a);
		
		int m = 0;
		int i = 0;
		while (!list.isEmpty()) {
			int countOnes = 0;
			int countZeros = 0;
			for (int j : list) {
				if ((j & (1 << i)) > 0) {
					countOnes++;
				} else {
					countZeros++;
				}
			}
			if (countZeros > countOnes) { // add odd
				m |= (1 << i);
				remove(list, i, 0);
			} else { // add even
				remove(list, i, 1);
			}
			i++;
		}
		return m;
	}
	
	private static void remove(ArrayList<Integer> list, int i, int v) {
		for (int k = 0; k < list.size(); k++) {
			if (getBit(list.get(k), i) == v) {
				list.delete(k);
				k--;
			}						
		}
	}
	
	public static int findMissingRecursive(int[] a) {
		if (a == null) {
			return -1;			
		}
		ArrayList<Integer> list = toList(a);
		return findMissingRecursive(list, 0);
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(n) [Recursive] 
	 */
	public static int findMissingRecursive(ArrayList<Integer> list, int i) {
		if (list.isEmpty()) {
			return 0;
		}

		int countOnes = 0;
		int countZeros = 0;
		int m = 0;
		for (int j : list) {
			if (getBit(j, i) > 0) {
				countOnes++;
			} else {
				countZeros++;
			}
		}
		if (countZeros > countOnes) { // add odd
			m = 1;
			remove(list, i, 0);
		} else {
			remove(list, i, 1);
		}
		return findMissingRecursive(list, i + 1) | (m << i);
	}
		
	/**
	 * Time:  Average = Worst = 2 * 32 * O(n) = O(n)<br>
	 * Space: Worst = 2 int = O(1) 
	 */
	public static int findMissingUsingParity(int[] a, int n) {
		int actual = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < 32; j++) {
				 actual ^= getBit(a[i], j) << j;
			}
		}
		int expected = 0;
		for (int j = 0; j < 32; j++) {
			expected |= getParity(n, j) << j;
		}
		
		return actual ^ expected;
	}
	
	private static int getBit(int n, int i) {
		return (n & (1 << i)) > 0 ? 1 : 0;
	}

	/**
	 * True:  Odd (1)<br>
	 * False: Even (0)
	 */
	private static int getParity(int n, int i) {
		if (i == 0) {
			return (((n + 1) % 4) - 2) >= 0 ? 1 : 0;
		}		
		int b = (int) Math.pow(2, (i + 1)); 
		int v = ((n) % b) - (b / 2);
		if (v >= 0) {
			return (v % 2) == 0 ? 1 : 0; // has ones
		} else {
			return 0; // does not have ones
		}
	}
	
	private static ArrayList<Integer> toList(int[] a) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) {
			list.add(a[i]);
		}
		return list;
	}	
}
