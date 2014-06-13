package com.algomized.datastructures.strings;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Assume you have a method isSubstring which checks if one word is a 
 * substring of another. Given two strings, si and s2, write code to check if s2 is 
 * a rotation of si using only one call to isSubstring (e.g.,"waterbottle"is a rotation 
 * of "erbottlewat").
 * </p>
 *
 */
public class CrackingTheCodingInterviewC1Q8 {
	public static void main(String[] args) {
		String s1 = "waterbottle";
		String s2 = "erbottlewat";
		System.out.println(isRotation(s1, s2));
	}
	
	public static boolean isRotation(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length()) {
			return false;
		}
		return isSubString(s1 + s1, s2);
	}

	public static boolean isSubString(String s1, String s2) {
		return s1.indexOf(s2) != -1;
	}
}
