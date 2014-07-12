package com.algomized.recursion;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * A magic index in an array A[0.. .n-1] is defined to be an index such that A[i]
 * = i. Given a sorted array of distinct integers, write a method to find a magic
 * index, if one exists, in array A.<br>
 * FOLLOW UP<br>
 * What if the values are not distinct?
 * </p>
 *
 */
public class CrackingTheCodingInterviewC9Q3 {
	
	public static void main(String[] args) {
		int[] arr = {-40, -20, -1, 1, 2, 3, 7, 7, 9, 12, 13};
		System.out.println(findMagicIndexDistinct(arr));
		System.out.println(findMagicIndexWithDuplicates(arr));
		System.out.println(findMagicIndexWithDuplicatesByIndex(arr));
	}
	
	/**
	 * Time:  Worst = O(log(n))<br>
	 * Space: Worst = O(log(n))
	 */
	public static int findMagicIndexDistinct(int[] arr) {
		if (arr == null) return -1;
		return findMagicIndexDistinct(arr, 0, arr.length - 1);		
	}

	private static int findMagicIndexDistinct(int[] arr, int lo, int hi) {
		if (lo > hi) return -1; // no magic index
		int mid = (hi + lo) / 2;		
		if (arr[mid] - mid > 0) return findMagicIndexDistinct(arr, lo, mid - 1);
		else if (arr[mid] - mid < 0) return findMagicIndexDistinct(arr, mid + 1, hi);				 
		else return mid;
	}
	
	/**
	 * Time:  Worst = O(log(n))<br>
	 * Space: Worst = O(log(n))
	 */
	public static int findMagicIndexWithDuplicates(int[] arr) {
		if (arr == null) return -1;
		return findMagicIndexWithDuplicates(arr, 0, arr.length - 1);		
	}

	private static int findMagicIndexWithDuplicates(int[] arr, int lo, int hi) {
		if (lo > hi) return -1; // no magic index
		int mid = (hi + lo) / 2;
		int m = mid;
		
		// check for duplicates
		while (m > 0 && arr[--m] == arr[mid]) {
			if (arr[m] == m) return m;
		}
		m = mid;
		while (m < (arr.length - 1) && arr[++m] == arr[mid]) {
			if (arr[m] == m) return m;
		}	
		
		if (arr[mid] - mid > 0) return findMagicIndexWithDuplicates(arr, lo, mid - 1);
		else if (arr[mid] - mid < 0) return findMagicIndexWithDuplicates(arr, mid + 1, hi);				 
		else return mid;
	}	
	
	/**
	 * Time:  Worst = O(log(n))<br>
	 * Space: Worst = O(log(n)) 
	 */
	public static int findMagicIndexWithDuplicatesByIndex(int[] arr) {
		if (arr == null) return -1;
		else return findMagicIndexWithDuplicatesByIndex(arr, 0, arr.length - 1);
	}
	
	private static int findMagicIndexWithDuplicatesByIndex(int[] arr, int lo, int hi) {
		if (lo < 0 || hi >= arr.length || lo > hi) {
			return -1;
		}
		int mid = (hi + lo) / 2;
		if (arr[mid] == mid) return mid;
		
		int leftIndex = Math.min(mid - 1, arr[mid]);
		int left = findMagicIndexWithDuplicatesByIndex(arr, lo, leftIndex);
		if (left >= 0) return left;
		int rightIndex = Math.max(mid + 1, arr[mid]);
		return findMagicIndexWithDuplicatesByIndex(arr, rightIndex, hi);
	}
}