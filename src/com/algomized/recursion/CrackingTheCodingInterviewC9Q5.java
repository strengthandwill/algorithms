package com.algomized.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Write a method to compute all permutations of a string.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC9Q5 {
	public static void main(String[] args) {
		System.out.println(getPermutationsRecursive("abcde"));
		System.out.println(getPermutationsIterative("abcde"));
	}
	
	/**
	 * Time:  Worst = O(sum of n!), n = length of str<br>
	 * Space: Worst = O(n!) + O(n) = O(n!)
	 */	
	private static List<String> getPermutationsRecursive(String str) {
		if (str == null) return null;
		if (str.length() == 0) {
			List<String> p = new ArrayList<String>();
			p.add("");
			return p;
		}
		
		List<String> p = getPermutationsRecursive(str.substring(1));
		List<String> r = new ArrayList<String>();
		for (String s : p) {			
			for (int i = 0; i < str.length(); i++) {
				StringBuffer strBuf = new StringBuffer(s);
				strBuf.insert(i, str.charAt(0));
				r.add(strBuf.toString());
			}
		}
		return r;
	}
	
	/**
	 * Time:  Worst = O(sum of n!), n = length of str<br>
	 * Space: Worst = O(n!) 
	 */
	public static List<String> getPermutationsIterative(String str) {
		if (str == null) return null;
		List<String> p = new ArrayList<String>();
		p.add("");
		for (int i = 1; i <= str.length(); i++) {
			String str2 = str.substring(str.length() - i);
			List<String> r = new ArrayList<String>();
			for (String s : p) {
				for (int j = 0; j < i; j++) {
					StringBuffer strBuf = new StringBuffer(s);
					strBuf.insert(j, str2.charAt(0));
					r.add(strBuf.toString());
				}
			}
			p = r;
		}
		return p;
	}
}