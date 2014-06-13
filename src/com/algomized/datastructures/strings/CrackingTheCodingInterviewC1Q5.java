package com.algomized.datastructures.strings;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Implement a method to perform basic string compression using the counts 
 * of repeated characters. For example, the string aabcccccaaa would become 
 * a2blc5a3. If the "compressed" string would not become smaller than the original 
 * string, your method should return the original string.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC1Q5 {
	public static void main(String[] args) {
		String str = "aabcccccaaa";
		System.out.println(compress(str));
		System.out.println(compressUsingCharArray(str));
	}
	
	/**
	 * Time:  Average = Worst = O(n) where n is the length of str and m is the length of compressed<br>
	 * Space: 1 StringBuffer, 2 char, 1 int, 1 String = O(m) where m is the length of compressed 
	 */
	public static String compress(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() == 1) {
			return str;
		}
		StringBuffer strBuf = new StringBuffer();
		char repeat = str.charAt(0);
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char current = str.charAt(i);
			if (str.charAt(i) != repeat) { // new char found
				strBuf.append(String.valueOf(repeat) + count);
				repeat = current;
				count = 1;
			} else { // char repeats
				count++;
			}
		}
		strBuf.append(String.valueOf(repeat) + count); // last compression
		String compressed = strBuf.toString();
		return compressed.length() < str.length() ? compressed : str; // return str if compressed is longer
	}
	
	/**
	 * Time:  Average = Worst = O(n) + O(n) = O(n) where n is the length of str and m is the length of compressed<br>
	 * Space: Worst = O(m) where n is the length of compressed
	 */
	public static String compressUsingCharArray(String str) {
		if (str == null) {
			return null;
		}
		int compressedLength = compressedLength(str);
		if (compressedLength > str.length()) { // return str if compressed is longer
			return str;
		}
		char[] compressed = new char[compressedLength];
		char repeat = str.charAt(0);
		int count = 0;
		int j = 0;
		for (int i = 0; i < str.length(); i++) {
			char current = str.charAt(i);
			if (current != repeat) { // new char found
				j = writeCharArray(compressed, j, repeat, count);
				repeat = current;
				count = 1;
			} else { // char repeats
				count++;
			}
		}
		writeCharArray(compressed, j, repeat, count); // last compressed
		return String.copyValueOf(compressed);
	}
	
	/**
	 * Time:  Average = Worst = O(n) where n is the length of count<br>
	 * Space: Worst = O(n) where n is the length of count<br>
	 * 
	 */
	private static int writeCharArray(char[] charArray, int index, char c, int count) {
		charArray[index++] = c; // write char
		char[] countCharArray = String.valueOf(count).toCharArray();
		for (int i = 0; i < countCharArray.length; i++) { // write count
			charArray[index++] = countCharArray[i];
		}
		return index;
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = 2 int, 1 char = O(1) 
	 */
	private static int compressedLength(String str) {
		int length = 0;
		char repeat = str.charAt(0);
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char current = str.charAt(i);
			if (current != repeat) { // new char found
				length += 1 + String.valueOf(count).length();
				repeat = current;
				count = 1;
			} else { // char repeats
				count++;				
			}
		}
		length += 1 + String.valueOf(count).length(); // last compression
		return length;
	}
}