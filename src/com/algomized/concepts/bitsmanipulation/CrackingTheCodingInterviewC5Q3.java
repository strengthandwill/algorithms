package com.algomized.concepts.bitsmanipulation;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given a positive integer, print the next smallest and the next largest number 
 * that have the same number of 1 bits in their binary representation.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC5Q3 {

	public static void main(String[] args) {
		int n = 13948;
		//int n = 10115;
		Result result = getNextMinMax(n);
		System.out.println(Integer.toBinaryString(n));
		System.out.println(Integer.toBinaryString(result.nextMin));
		System.out.println(Integer.toBinaryString(result.nextMax));
	}
		
	public static Result getNextMinMax(int n) {
		if (n < 0) {
			return null;
		}
		Result result = new Result();
		result.nextMin = getNextMin(n);
		result.nextMax = getNextMax(n);
		return result;
	}	
	
	/**
	 * 1. Count in n the number of ones until the first 01 bits are found.<br>
	 * 2. Swap the bits to 10 bits.<br>
	 * 3. Clear bits in n after the 10 bits to zeros.<br> 
	 * 4. Create the one bits counted and shift them to left.<br>
	 * 5. OR n with the one bits created. 
	 */
	private static int getNextMin(int n) {
		int count = 0;		
		for (int i = 0; i < 31; i++) {
			boolean current = getBit(n, i);
			boolean next = getBit(n, i + 1);						
			if (!current && next) {
				// swap bits
				n = updateBit(n, i, 1);
				n = updateBit(n, i + 1, 0);
				
				// shift the counted ones to left
				n = clearBitsLSB(n, i - 1);
				n |= setBitsLeft(count, i);
				return n;
			}
			if (current) {
				count++;
			}			
		}
		return n; // already the min number for the one bits
	}

	/**
	 * 1. Count in n the number of ones until the first 10 bits are found.<br>
	 * 2. Swap the bits to 01 bits.<br>
	 * 3. Clear bits in n after the 01 bits to zeros.<br> 
	 * 4. Create the one bits counted and shift them to right.<br>
	 * 5. OR n with the one bits created. 
	 */	
	private static int getNextMax(int n) {
		int count = 0;
		for (int i = 0; i < 31; i++) {
			boolean current = getBit(n, i);
			boolean next = getBit(n, i + 1);			
			if (current && !next) {
				// swap bits
				n = updateBit(n, i, 0);
				n = updateBit(n, i + 1, 1);
				
				// shift the counted ones to right
				n = clearBitsLSB(n, i - 1);
				n |= setBitsRight(count);
				return n;
			}
			if (current) {
				count++;
			}			
		}
		return n; // already the max number for the one bits
	}
	
	private static boolean getBit(int n, int i) {
		return (n & (1 << i)) > 0;		
	}	
	
	private static int updateBit(int n, int i, int v) {
		int masked = ~(1 << i);
		return (n & masked) | (v << i);
	}
	
	private static int clearBitsLSB(int n, int i) {
		int mask = ~((1 << (i + 1)) - 1);
		return n & mask;
	}	
	
	private static int setBitsRight(int count) {
		return (1 << count) - 1;
	}	
	
	private static int setBitsLeft(int count, int length) {
		int n = (1 << count) - 1;
		return n << (length - count);
	}
	
	static class Result {
		int nextMin;
		int nextMax;
	}	
}