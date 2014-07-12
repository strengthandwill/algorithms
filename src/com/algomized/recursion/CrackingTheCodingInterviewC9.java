package com.algomized.recursion;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * <b>Recursion</b>
 * Needs to store each call in stack, so high space complexity. 
 * Two approaches: Bottom-up, Top-down.<br>
 * <br>
 * <b>Dynamic Programming</b>
 * Recursion with caching of values of previous calls, so do not
 * need to execute the calls that have the value cached, therefore,
 * reduce time complexity but increase space complexity with caching.<br>
 * <br>
 * <b>Iteration</b>
 * All recursion can be convert to iteration. Iteration is only one call, 
 * so reduce the space complexity but it may be complex to implement 
 * as compare to recursion.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC9 {	
	public static void main(String[] args) {
		System.out.println(fibonacciRecursive(10));
		System.out.println(fibonacciDP(10));
		System.out.println(fibonacciIterative(10));
	}

	/**
	 * <b>Recursion</b><br>
	 * Time:  Worst = O(2^n)<br>
	 * Space: Worst = O(2^n) 
	 */
	public static int fibonacciRecursive(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
	}
	
	/**
	 * <b>Dynamic Programming</b><br>
	 * Time:  Worst = O(n)<br>
	 * Space: Worst = O(n) + O(n) = O(n)
	 */
	public static int fibonacciDP(int n) {
		return fibonacciDP(new int[n + 1], n);
	}
				
	private static int fibonacciDP(int[] fib, int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		if (fib[n] == 0) {
			fib[n] = fibonacciDP(fib, n - 1) + fibonacciDP(fib, n - 2);
		}
		return fib[n];
	}
	
	/**
	 * <b>Iterative</b><br>
	 * Time:  Worst = O(n)<br>
	 * Space: Worst = int array of n = O(n) 
	 */
	public static int fibonacciIterative(int n) {
		int[] fib = new int[n + 1];
		fib[0] = 0;
		fib[1] = 1;
		for (int i = 2; i <= n; i++) {
			if (fib[i] == 0) {
				fib[i] = fib[i - 1] + fib[i - 2];
			}
		}
		return fib[n];
	}
}
