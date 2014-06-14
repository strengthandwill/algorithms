package com.algomized.datastructures.strings;

import com.algomized.datastructures.hashtables.SeparateChainingHashtable;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given two strings, write a method to decide if one is a permutation of the other.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC1Q3 {

	public static void main(String[] args) {
		String str1 = "hello world";
		String str2 = "wld lolhero";
		System.out.println(isPermutationUsingArray(str1, str2));
		//System.out.println(isPermutationUsingHashtable(str1, str2));
		//System.out.println(isPermutationUsingSorting(str1, str2));
	}
	
	/**
	 * Time:  Average = Worst = 2 * O(n) = O(n)<br>
	 * Space: Worst = 1 int array of size 255 = O(1)  
	 */
	public static boolean isPermutationUsingArray(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() != str2.length()) {
			return false;
		}
		int[] count = new int[255]; // assuming char is ASCII
		for (int i = 0; i < str1.length(); i++) {
			count[str1.charAt(i)]++;
		}
		for (int i = 0; i < str2.length(); i++) {
			count[str2.charAt(i)]--;
			if (count[str2.charAt(i)] < 0) { // char not in str1 is found
				return false;
			}
		}
		return true; // all chars of str1 matches str2
	}
	
	/**
	 * Time:  Average = 2 * O(n) * O(1), Worst = 2 * O(n) * O(n) = O(n^2)<br>
	 * Space: Worst = 1 hashtable + 1 char = O(n)  
	 */
	public static boolean isPermutationUsingHashtable(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() != str2.length()) {
			return false;
		}
		
		SeparateChainingHashtable<Character, Integer> hashtable = 
				new SeparateChainingHashtable<Character, Integer>();
		for (int i = 0; i < str1.length(); i++) { // stores chars and its count in str1
			char c = str1.charAt(i);
			if (!hashtable.contains(c)) {
				hashtable.put(c, 1);
			} else {
				hashtable.put(c, hashtable.get(c) + 1);
			}
		}
		for (int i = 0; i < str2.length(); i++) { // check chars and its count in str2
			char c = str2.charAt(i);
			if (hashtable.contains(c)) { // char found
				int count = hashtable.get(c);
				if (count == 1) {
					hashtable.remove(c);
				} else {
					hashtable.put(c, count - 1);
				}
			} else {
				hashtable.put(c, null);
			}
		}
		return hashtable.isEmpty();
	}
	
	/**
	 * Time:  Average = Worst = O(nlgn) (Merge sort) + O(n) = O(nlgn)<br>
	 * Space: Worst = O(n)
	 */
	public static boolean isPermutationUsingSorting(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() != str2.length()) {
			return false;
		}
		char[] str1Chars = sort(str1.toCharArray());
		char[] str2Chars = sort(str2.toCharArray());
		for (int i = 0; i < str1Chars.length; i++) {
			if (str1Chars[i] != str2Chars[i]) { // different char found
				return false;
			}
		}
		return true; // all the chars are the same
	}
	
	/**
	 * Brute force sorting<br>
	 * Time:  Average = Worst = O(n^2)<br>
	 * Space: Worst = 1 char = O(1)
	 */
	public static char[] sort(char[] chars) {
		for (int i = 0; i < chars.length; i++) {
			for (int j = i + 1; j < chars.length; j++) {
				if (chars[i] > chars[j]) {
					char temp = chars[i];
					chars[i] = chars[j];
					chars[j] = temp;
				}
			}
		}
		return chars;
	}	
}
