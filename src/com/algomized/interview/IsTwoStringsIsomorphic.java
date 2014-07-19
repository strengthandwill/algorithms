package com.algomized.interview;

import java.util.HashMap;
import java.util.Map;

public class IsTwoStringsIsomorphic {	
	public static void main(String[] args) {
		System.out.println(isIsomorphic("abca", "zbxz"));
	}

	public static boolean isIsomorphic(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
		Map<Character, Character> map = new HashMap<Character, Character>();
		for (int i = 0; i < s1.length(); i++) {
			if (!map.containsKey(s1.charAt(i))) {
				map.put(s1.charAt(i), s2.charAt(i));
			} else if (!map.get(s1.charAt(i)).equals(s2.charAt(i))) {
				return false;
			}
		}
		return true;
	}	
}
