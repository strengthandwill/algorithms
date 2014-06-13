package com.algomized.datastructures.strings;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Write a method to replace all spaces in a string with'%20'. You may assume that 
 * the string has sufficient space at the end of the string to hold the additional
 * characters, and that you are given the "true" length of the string. (Note: if implementing
 * in Java, please use a character array so that you can perform this operation in place.)<br>
 * EXAMPLE<br>
 * Input: "Mr John Smith<br>
 * Output: "Mr%20Dohn%20Smith"<br>
 * </p>
 *
 */
public class CrackingTheCodingInterviewC1Q4 {
	public static void main(String[] args) {
		char[] str = "Mr John Smith    ".toCharArray();
		replaceSpaces(str, 13);
		System.out.println(str);
	}
	
	/**
	 * Time:  Average = Worst = O(n) + O(n) = O(n)<br>
	 * Space: Worst = 2 int = O(1)
	 */
	public static void replaceSpaces(char[] str, int length) {
		if (str == null) {
			return;
		}
		int spaceCount = 0;
		for (int i = 0; i < length; i++) { // count the number of spaces
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		int j = length + spaceCount * 2 - 1;		
		for (int i = length - 1; i >= 0; i--) { // replace spaces from back to front
			if (str[i] == ' ') {
				str[j--] = '0';
				str[j--] = '2';
				str[j--] = '%';
			} else {
				str[j] = str[i];
				j--;
			}
		}
	}
}