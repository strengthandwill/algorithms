package com.algomized.concepts.bitsmanipulation;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * You are given two 32-bit numbers, N and M, and two bit positions, land j. Write
 * a method to insert M into N such that M starts at bit j and ends at bit i. You can
 * assume that the bits j through i have enough space to fit all of M. That is, if
 * M = 10011, you can assume that there are at least 5 bits between j and i. You
 * would not, for example, have j = 3 and i = 2, because M could not fully fit
 * between bit 3 and bit 2.<br>
 * EXAMPLE<br>
 * Input: N = 10000000000, M = 10011, i = 2, j = 6<br>
 * Output: N = 10001001100<br>
 * </p>
 *
 */
public class CrackingTheCodingInterviewC5Q1 {
	public static void main(String[] args) {
		int m = 1024;
		int n = 19;
		System.out.println(Integer.toBinaryString(m));
		System.out.println(Integer.toBinaryString(n));
		System.out.println(Integer.toBinaryString(insert(m, n, 2, 6)));
	}
	
	/**
	 * 1. Clear bits of m from j through i.<br>
	 * 1.1 Shift 1 left by (j - i + 2).<br>
	 * 1.2 Subtract 1 from the result.<br>
	 * 1.3 Shift 1 left by i to create the mask.<br>
	 * 1.4 AND n with the mask.<br>
	 * 2. Shift n right by i.<br>
	 * 3. OR masked n and shifted n.
	 */
	public static int insert(int m, int n, int i, int j) {
		int masked = ~(((1 << (j - i + 2)) - 1) << i);
		return (m & masked) | (n << i); 
	}
}
