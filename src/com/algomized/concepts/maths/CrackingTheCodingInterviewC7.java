package com.algomized.concepts.maths;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Common methods to determine whether a number is a prime.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC7 {

	public static void main(String[] args) {
		System.out.println(primeSimple(11));
		System.out.println(primeWithSqrt(11));
		System.out.println(primeUsingSieveOfEratosthenes(11));
		System.out.println(primeUsingSieveOfEratosthenesOddNumbers(11));
	}
	
	public static boolean primeSimple(int n) {
		if (n < 2) {
			return false;
		}		
		for (int i = 2; i < n; i++) {
			if ((n % i) == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Reduces time complexity by terminating loop at sqrt of n. 
	 */
	public static boolean primeWithSqrt(int n) {
		if (n < 2) {
			return false;
		}
		int m = (int) Math.sqrt(n);
		for (int i = 2; i < m; i++) {
			if ((n % i) == 0) {
				return false;
			}
		}
		return true;
	}	
	
	public static boolean primeUsingSieveOfEratosthenes(int n) {
		boolean[] flags = sieveOfEratosthenes(n);
		for (int i = 0; i < flags.length; i++) {
			if (flags[i] && ((n % i) == 0)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean[] sieveOfEratosthenes(int max) {
		boolean[] flags = new boolean[max];
		for (int i = 2; i < flags.length; i++) { // init flags
			flags[i] = true;
		}
		
		int prime = 2;
		int end = (int) Math.sqrt(max);
		while (prime <= end) {
			crossOff(flags, prime);
			prime = getNextPrime(flags, prime);
		}
		return flags;
	}
	
	private static void crossOff(boolean[] flags, int prime) {
		for (int i = prime * prime; i < flags.length; i+=prime) {
			flags[i] = false;
		}
	}
	
	private static int getNextPrime(boolean[] flags, int prime) {
		int next = prime + 1;
		while (next < flags.length && !flags[next]) {
			next++;
		}
		return next;
	}
	
	/**
	 * Reduces space complexity by using only odd numbers, since all even numbers
	 * except 2 are not prime number. 
	 */
	public static boolean primeUsingSieveOfEratosthenesOddNumbers(int n) {
		if (n == 2) {
			return true;
		}
		
		boolean[] flags = sieveOfEratosthenesUsingOddNumbers(n);
		for (int i = 0; i < flags.length; i++) {
			if (flags[i] && ((n % ((2 * i) + 3)) == 0)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean[] sieveOfEratosthenesUsingOddNumbers(int max) {
		boolean[] flags = new boolean[(max / 2) - 1];
		for (int i = 1; i < flags.length; i++) { // init flags
			flags[i] = true;
		}
		
		int prime = 3;
		int end = (int) Math.sqrt(max);
		while (prime <= end) {
			crossOffUsingOddNumbers(flags, prime);
			prime = getNextPrimeUsingOddNumbers(flags, prime);
		}
		return flags;
	}
	
	private static void crossOffUsingOddNumbers(boolean[] flags, int prime) {
		for (int i = prime * prime; i < flags.length; i+=prime) {
			flags[(i - 3) / 2] = false;
		}
	}
	
	private static int getNextPrimeUsingOddNumbers(boolean[] flags, int prime) {
		int next = prime + 1;
		while (next < flags.length && !flags[(next - 3) / 2]) {
			next++;
		}
		return next;
	}
}
