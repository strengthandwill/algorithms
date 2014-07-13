package com.algomized.recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Write a method to return all subsets of a set.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC9Q4 {
	public static void main(String[] args) {
		Set<Integer> s = new HashSet<Integer>();
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);				
		System.out.println(getSubSetsTopDown(s));	
		System.out.println(getSubSetsRecursive(s));
		System.out.println(getSubSetsIterative(s));
		System.out.println(getSubSetsUsingBits(s));	
	}
	
	/**
	 * Time:  Worst = O(n(2^n)), n = size of set<br>
	 * Space: Worst = O(2^n) + O(n) = O(2^n)
	 */
	public static Set<Set<Integer>> getSubSetsRecursive(Set<Integer> set) {
		if (set == null) return null;
		return getSubSetsRecursive(set.toArray(new Integer[set.size()]), set.size());		
	}
	
	private static Set<Set<Integer>> getSubSetsRecursive(Integer[] set, int n) {
		if (n == 0) {
			Set<Set<Integer>> ss = new HashSet<Set<Integer>>();
			ss.add(new HashSet<Integer>());
			return ss;
		}
		
		Set<Set<Integer>> ss = getSubSetsRecursive(set, n - 1);
		ss.addAll(getMoreSubSets(ss, set[n - 1]));		
		return ss;
	}
	
	private static Set<Set<Integer>> getMoreSubSets(Set<Set<Integer>> sets, int x) {
		Set<Set<Integer>> ss = new HashSet<Set<Integer>>();		
		for (Set<Integer> s1 : sets) {
			Set<Integer> s2 = new HashSet<Integer>();
			s2.addAll(s1); // clone set
			s2.add(x);
			ss.add(s2);
		}		
 		return ss;
	}	
	
	/**
	 * Time:  Worst = O(n(2^n))<br>
	 * Space: Worst = O(2^n) 
	 */
	public static Set<Set<Integer>> getSubSetsIterative(Set<Integer> set) {
		if (set == null) return null;
		Integer[] arr = set.toArray(new Integer[set.size()]);
		Set<Set<Integer>> subSets = new HashSet<Set<Integer>>();
		subSets.add(new HashSet<Integer>()); // add empty set
		for (int i = 0; i < arr.length; i++) {
			Set<Set<Integer>> ss = new HashSet<Set<Integer>>();
			for (Set<Integer> s1 : subSets) {
				Set<Integer> s2 = new HashSet<Integer>();
				s2.addAll(s1); // clone set
				s2.add(arr[i]);
				ss.add(s2);
			}
			subSets.addAll(ss);
		}
		return subSets;
	}	
	
	/**
	 * Time:  Worst = O(n(2^n)), n = size of set<br>
	 * Space: Worst = O(2^n) + O(n) = O(2^n)
	 */
	public static Set<Set<Integer>> getSubSetsTopDown(Set<Integer> set) {
		if (set == null) return null;
		Set<Set<Integer>> ss = new HashSet<Set<Integer>>();
		getSubSetsTopDown(set, ss);
		return ss;
	}
	
	private static void getSubSetsTopDown(Set<Integer> set, Set<Set<Integer>> subSets) {
		subSets.add(set);
		if (set.isEmpty()) return;
		Set<Set<Integer>> ss = nextSubSets(set);		
		for (Set<Integer> s : ss) {
			if (!subSets.contains(s)) {
				getSubSetsTopDown(s, subSets);
			}
		}
	}
	
	private static Set<Set<Integer>> nextSubSets(Set<Integer> set) {
		Set<Set<Integer>> ss = new HashSet<Set<Integer>>();
		for (int i = 0; i < set.size(); i++) {
			int j = 0;
			Set<Integer> s = new HashSet<Integer>();
			for (int k : set) {
				if (i != j) {
					s.add(k);
				}
				j++;
			}
			ss.add(s);
		}
		return ss;
	}	
	
	/**
	 * Time:  Worst = O(n(2^n)), n = size of set<br>
	 * Space: Worst = O(2^n)
	 */
	public static Set<Set<Integer>> getSubSetsUsingBits(Set<Integer> set) {
		if (set == null) return null;
		int size = 1 << set.size(); // compute 2^n
		Set<Set<Integer>> ss = new HashSet<Set<Integer>>();
		for (int i = 0; i < size; i++) {
			int j = 0;
			Set<Integer> s = new HashSet<Integer>();
			for (int k : set) {
				if (getBit(i, j)) {
					s.add(k);
				}
				j++;
			}
			ss.add(s);
		}
		return ss;
	}
	
	private static boolean getBit(int n, int i) {
		return (n & (1 << i)) > 0;
	}
}