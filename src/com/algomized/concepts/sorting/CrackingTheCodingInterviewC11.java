package com.algomized.concepts.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * <b>Sorting</b>
 * <ol>
 * <li>Bubble sort</li>
 * <li>Selection sort</li>
 * <li>Insertion sort</li>
 * <li>Shell sort</li>
 * <li>Merge sort</li>
 * <li>Quick sort</li>
 * <li>Radix sort</li>
 * </ol>
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC11 {
	public static void main(String[] args) {
		int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

		int[] result = arr.clone();
		bubbleSort(result);
		System.out.println(Arrays.toString(result));

		result = arr.clone();
		selectionSort(result);
		System.out.println(Arrays.toString(result));	

		result = arr.clone();
		insertionSort(result);
		System.out.println(Arrays.toString(result));

		result = arr.clone();
		shellSort(result);
		System.out.println(Arrays.toString(result));	

		result = arr.clone();
		mergeSort(result);
		System.out.println(Arrays.toString(result));

		result = arr.clone();
		quickSort(result);
		System.out.println(Arrays.toString(result));

		int[] result2 = {93, 812, 73, 6, 5235, 421, 395, 24, 151, 8};
		radixSort(result2);
		System.out.println(Arrays.toString(result2));		
	}

	/**
	 * Time:  Best = O(n), Average = Worst = O(n^2)<br>
	 * Space: Worst = O(1)
	 */
	public static void bubbleSort(int[] arr) {		
		if (arr == null) return;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);				
			}			
		}
	}

	/**
	 * Time:  Best = Worst = Average = O(n^2)<br>
	 * Space: Worst = O(1) 
	 */
	public static void selectionSort(int[] arr) {
		if (arr == null) return;
		for (int i = 0; i < arr.length; i++) {
			int m = i;			
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[m]) m = j;				
			}
			swap(arr, i, m);
		}
	}

	/**
	 * Time:  Best = O(n), Average = Worst = O(n^2)<br>
	 * Space: Worst = O(1)
	 * 
	 */
	public static void insertionSort(int[] arr) {
		if (arr == null) return;
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int k;
			for (k = i; k > 0 && arr[k - 1] > temp; k--) { // shift right
				arr[k] = arr[k - 1];
			}			
			arr[k] = temp; // swap
		}
	}

	/**
	 * Time:  Best = O(n), Worst = Average = O(n^2)<br>
	 * Space: Worst = O(1)
	 */
	public static void shellSort(int[] arr) {
		if (arr == null) return;
		int h = 1;
		while (h < arr.length / 3) h += 3 * h + 1;
		while (h >= 1) { 
			for (int i = h; i < arr.length; i += h) {
				int temp = arr[i];
				int k;
				for (k = i; k > 0 && arr[k - 1] > temp; k--) { // shift right
					arr[k] = arr[k - 1];
				}			
				arr[k] = temp; // swap
			}
			h /= 3;
		}
	}

	/**
	 * Time:  Best = Average = Worst = O(nlog(n))<br>
	 * Space: Worst = O(n)	
	 */
	public static void mergeSort(int[] arr) {
		if (arr == null) return;
		mergeSort(arr, new int[arr.length], 0, arr.length - 1);
	}

	private static void mergeSort(int[] arr, int[] helper, int lo, int hi) {
		if (lo >= hi) return;
		int mid = (hi + lo) / 2;
		mergeSort(arr, helper, lo, mid);
		mergeSort(arr, helper, mid + 1, hi);
		merge(arr, helper, lo, mid, hi);
	}

	private static void merge(int[] arr, int[] helper, int lo, int mid, int hi) {
		for (int i = lo; i <= hi; i++) helper[i] = arr[i]; // copy arr into helper
		int left = lo;
		int right = mid + 1;
		int current = lo;
		while (left <= mid && right <= hi) {
			if (helper[left] < helper[right]) arr[current++] = helper[left++];
			else arr[current++] = helper[right++];
		}
		while (left <= mid) arr[current++] = helper[left++];		
		while (right <= hi) arr[current++] = helper[right++];		
	}

	/**
	 * Time:  Best = Average = O(nlog(n)), Worst = O(n^2)<br>
	 * Space: Worst = O(1) 
	 */
	public static void quickSort(int[] arr) {
		if (arr == null) return;
		quickSort(arr, 0, arr.length - 1);
	}

	private static void quickSort(int[] arr, int lo, int hi) {
		if (lo >= hi) return;
		int i = partition(arr, lo, hi);
		quickSort(arr, lo, i - 1);
		quickSort(arr, i + 1, hi);
	}

	private static int partition(int[] arr, int lo, int hi) {
		int i = lo;
		int j = hi;
		int pivot = arr[i++];
		while (i < j) {
			while (i < hi && arr[i] < pivot) i++;
			while (j > lo && arr[j] > pivot) j--;
			if (i < j) swap(arr, i, j);
		}
		swap(arr, j, lo);
		return j;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i]; // swap
		arr[i] = arr[j];
		arr[j] = temp;		
	}

	/**
	 * Time:  Best = Average = Worst = O(kn), k = number of passes<br>
	 * Space: Worst = O(n + k)
	 */
	public static void radixSort(int[] arr) {
		if (arr == null) return;

		List<List<Integer>> bucket = new ArrayList<List<Integer>>();
		for (int i = 0; i < 10; i++) {
			bucket.add(new ArrayList<Integer>());
		}
		int n = 1;
		boolean maxLength = false;
		while (!maxLength) {
			maxLength = true;
			for (int i = 0; i < arr.length; i++) {
				int temp = arr[i] / n;
				bucket.get(temp % 10).add(arr[i]);
				if (maxLength &&  temp > 0) maxLength = false; 
			}
			n *= 10;
			int i = 0;
			for (List<Integer> l : bucket) {
				for (int j : l) {
					arr[i++] = j;
				}
				l.clear();
			}
		}		
	}
}