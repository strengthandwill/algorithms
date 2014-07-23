package com.algomized.scalability;

import java.util.BitSet;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * You have an array with all the numbers from 1 to N, where N is at most 32,000.
 * The array may have duplicate entries and you do not know what N is. With only
 * 4 kilobytes of memory available, how would you print all duplicate elements in
 * the array.<br>
 * <br>
 * Memory: 4 KB = 4 * 2 ^ 10 bytes = 2 ^ 12 * 8 bits = 2 ^ 15 bits<br>
 * Range: 1 to 32000 = 32000 values < 2 ^ 15 values (Memory size)<br>
 * </p>
 *
 */
public class CrackingTheCodingInterviewC10Q4 {	
	public static void main(String[] args) {
		int[] arr = {1, 1, 1, 2, 3, 4, 5, 5};
		printDuplicates(arr);
	}

	public static void printDuplicates(int[] arr) {
		if (arr == null) return;
		BitSet bs = new BitSet(32000);
		for (int i = 0; i < arr.length; i++) {
			if (!bs.get(arr[i] - 1)) bs.set(arr[i] - 1);
			else System.out.println(arr[i]);			
		}
	}	
}