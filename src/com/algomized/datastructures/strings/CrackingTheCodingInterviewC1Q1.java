package com.algomized.datastructures.strings;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Implement an algorithm to determine if a string has all unique characters.<br>
 * What if you cannot use additional data structures? 
 * </p>
 *  
 */
public class CrackingTheCodingInterviewC1Q1 {	
	public static void main(String[] args) {
		String string = "Hello world";
		System.out.println(isUnique(string));
		System.out.println(isUniqueUsingBitVector(string));

	}
	
	/**	 
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = 1 boolean array of size 255, 1 int = O(1)
	 */
	public static boolean isUnique(String string) {
		boolean[] found = new boolean[255];
		for (int i = 0; i < string.length(); i++) {
			int value = string.charAt(i);
			if (!found[value]) found[value] = true; // char is first found
			else return false; // char already exists
		}
		return true; // all chars are unique
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = 2 int = O(1)
	 */
	public static boolean isUniqueUsingBitVector(String string) {
		int checker = 0;
		for (int i = 0; i < string.length(); i++) {
			int value = string.charAt(i) - 'a'; // limit char range from a to z
			if ((checker & (1 << value)) == 0) checker |= (1 << value); // char is first found
			else return false; // char already exists
		}
		return true;
	}
}
