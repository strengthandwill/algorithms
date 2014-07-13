package com.algomized.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Implement an algorithm to print all valid (e.g., properly opened and closed)
 * combinations of n-pairs of parentheses.<br>
 * EXAMPLE<br>
 * Input: 3<br>
 * Output: ((())), (()()), (())(), ()(()), ()()()<br>
 * </p>
 *
 */
public class CrackingTheCodingInterviewC9Q6 {
	public static void main(String[] args) {
		System.out.println(getParenthesisRecursive(3));
		System.out.println(getParenthesisIterative(3));
		System.out.println(getParenthesis(3));
	}
	
	public static Set<String> getParenthesisRecursive(int n) {
		if (n < 0) return null;
		if (n == 0) {
			Set<String> p = new HashSet<String>();
			p.add("");
			return p;
		}
		
		Set<String> p = getParenthesisRecursive(n - 1);
		Set<String> r = new HashSet<String>();
		for (String s : p) {
			r.add("()" + s);
			r.add(s + "()");
			r.add("(" + s + ")");
		}
		return r;
	}
	
	public static Set<String> getParenthesisIterative(int n) {
		if (n <= 0) return null;
		Set<String> p = new HashSet<String>();		
		p.add("");
		for (int i = 0; i < n; i++) {
			Set<String> r = new HashSet<String>();
			for (String s : p) {
				r.add("()" + s);
				r.add(s + "()");
				r.add("(" + s + ")");		
			}
			p = r;
		}
		return p;
	}
	
	public static List<String> getParenthesis(int n) {
		if (n < 0) return null;
		List<String> p = new ArrayList<String>();
		addParenthesis(p, n, n, new char[n * 2], 0);
		return p;
	}
	
	private static void addParenthesis(List<String> p, int left, int right, 
			char[] str, int i) {
		if (left < 0 || right < left) return;
		if (left == 0 && right == 0) {
			p.add(new String(str));
			return;
		}
		if (left > 0) {
			str[i] = '(';
			addParenthesis(p, left - 1, right, str, i + 1);
		}
		if (right > left) {
			str[i] = ')';
			addParenthesis(p, left, right - 1, str, i + 1);
		}		
	}
}
