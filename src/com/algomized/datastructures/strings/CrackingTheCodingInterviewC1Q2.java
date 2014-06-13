package com.algomized.datastructures.strings;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Implement a function void reverse(char* str) in C or C++ 
 * which reverses a null terminated string.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC1Q2 {
	public static void main(String[] args) {
		char[] str = "Hello World!".toCharArray();
		System.out.println(str);
		//reverse(str);
		reverseRecursive(str);
		System.out.println(str);
	}
	
	/**
	 * Time:  Average = Worst = n / 2 = O(n)<br>
	 * Space: Worst = 1 char = O(1)
	 */
	public static void reverse(char[] str) {
		if (str == null) {
			return;
		}
		for (int i = 0; i < str.length / 2; i++) { // swap first and last char until middle
			char temp = str[i];
			str[i] = str[str.length - i - 1];
			str[str.length - i  - 1] = temp;
		}
	}
	
	/**
	 * Time:  Average = Worst = n0 + n1 + ... nx where x = lgn times = O(nlgn)<br>
	 * Space: Worst = n0 + n1 + ... nx where x = lgn times = O(nlgn)
	 */
	public static void reverseRecursive(char[] str) {
		reverseRecursive(str, 0, str.length - 1);
	}
	
	private static void reverseRecursive(char[] str, int lo, int hi) {
		if (lo >= hi) {
			return;
		}		
		int mid = (hi + lo) / 2;
		reverseRecursive(str, lo, mid);
		reverseRecursive(str, mid + 1, hi);
		merge(str, lo, mid, hi);				
	}
	
	/**
	 *  Time:  Average = Worst = O(n) where n = hi - lo + 1<br>
	 *  Space: Worst = char array of size n where n = hi - lo + 1
	 */
	public static void merge(char[] str, int lo, int mid, int hi) {
		char[] copy = new char[hi - lo + 1];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = str[i + lo];
		}
		int j = lo;
		// swap left and right
		for (int i = mid + 1; i <= hi; i++) { // copy right to left
			str[j] = copy[i - lo];
			j++;
		}
		for (int i = lo; i <= mid; i++) { // copy left to right
			str[j] = copy[i - lo];
			j++;
		}
	}
}
