package com.algomized.recursion;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * A child is running up a staircase with n steps, and can hop either 1 step, 2 steps,
 * or 3 steps at a time. Implement a method to count how many possible ways the
 * child can run up the stairs.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC9Q1 {	
	public static void main(String[] args) {
		System.out.println(stepsRecursive(20));
		System.out.println(stepsDP(20));
		System.out.println(stepsIterative(20));
	}

	/**
	 * Time:  Worst = O(3^n)<br>
	 * Space: Worst = O(3^n) 
	 */
	public static int stepsRecursive(int n) {
		if (n == 0) return 1;
		if (n == 1) return 1;
		if (n == 2) return 2;
		return stepsRecursive(n - 1) + stepsRecursive(n - 2) + stepsRecursive(n - 3);
	}
	
	/**
	 * Time:  Worst = O(n)<br>
	 * Space: Worst = O(n) + O(n) = O(n) 
	 */
	public static int stepsDP(int n) {
		return stepsDP(new int[n + 1], n);
	}
	
	private static int stepsDP(int[] s, int n) {
		if (n == 0) return 1;
		if (n == 1) return 1;
		if (n == 2) return 2;
		if (s[n] == 0) {
			s[n] = stepsDP(s, n - 1) + stepsDP(s, n - 2) + stepsDP(s, n - 3);
		}
		return s[n];
	}
	
	/**
	 * Time:  Worst = O(n)<br>
	 * Space: Worst = O(n) 
	 */
	public static int stepsIterative(int n) {
		int[] s = new int[n + 1];
		s[0] = 1;
		s[1] = 1;
		s[2] = 2;
		for (int i = 3; i <= n; i++) {			
			s[i] = s[i - 1] + s[i - 2] + s[i - 3]; 			
		}
		return s[n];
	}
}