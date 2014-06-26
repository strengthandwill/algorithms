package com.algomized.concepts.bitsmanipulation;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Common bit tasks. Get, set, clear and update bit(s).
 * </p>
 *
 */
public class CrackingTheCodingInterviewC5 {

	public static void main(String[] args) {
		System.out.println(getBit(5, 0));
		System.out.println(getBit2(5, 0));
		System.out.println(setBit(5, 1));
		System.out.println(clearBit(5, 0));
		System.out.println(clearBitsLSB(5, 2));
		System.out.println(clearBitsLSB2(5, 2));
		System.out.println(clearBitsMSB(5, 3));
		System.out.println(updateBit(5, 1, 1));
	}
	
	/**
	 * <b>Get Bit</b><br>
	 * 1. Shift 1 left by i.<br>
	 * 2. And the result with n.
	 */
	public static boolean getBit(int n, int i) {
		return (n & (1 << i)) > 0;		
	}
	
	/**
	 * <b>Get Bit</b><br>
	 * 1. Shift n right by i.<br>
	 * 2. AND the result with 1.
	 */
	public static boolean getBit2(int n, int i) {
		return ((n >> i) & 1) > 0;		
	}

	/**
	 * <b>Set Bit</b><br>
	 * 1. Shift 1 left by i.<br>
	 * 2. OR the result with n. 
	 */
	public static int setBit(int n, int i) {
		return n | (1 << i);
	}
	
	/**
	 * <b>Clear Bit</b><br>
	 * 1. Shift 1 right by i.<br>
	 * 2. NEGATE the result to create the mask.<br>
	 * 3. AND n with the mask. 
	 */
	public static int clearBit(int n, int i) {
		int mask = ~(1 << i);
		return n & mask;
	}
	
	/**
	 * <b>Clear Bits from i (inclusive) through LSB</b><br>
	 * 1. Shift 1 left by i + 1.<br>
	 * 2. Subtract 1 from the result.<br>
	 * 3. NEGATE the result to create the mask.<br>
	 * 4. AND n with the mask.
	 */
	public static int clearBitsLSB(int n, int i) {
		int mask = ~((1 << (i + 1)) - 1);
		return n & mask;
	}	
	
	/**
	 * <b>Clear Bits from i (inclusive) through through LSB</b><br>
	 * 1. Shift ~0 left by i + 1 to create the mask.<br>
	 * 2. AND n with the mask.
	 */
	public static int clearBitsLSB2(int n, int i) {
		int mask = (~0 << (i + 1));
		return n & mask;
	}
	
	/**
	 * <b>Clear Bits from MSB through i (inclusive)</b><br>
	 * 1. Shift 1 left by 1.<br>
	 * 2. Subtract 1 from the result to create the mask.<br>
	 * 3. AND n with the mask.
	 */
	public static int clearBitsMSB(int n, int i) {
		int mask = (1 << i) - 1;
		return n & mask;
	}
	
	/**
	 * <b>Update Bit</b><br>
	 * 1. Shift 1 right by i.<br>
	 * 2. NEGATE result to create the mask.<br>
	 * 3. AND n with the mask to clear bit i.<br>
	 * 4. Shift v right by i to create the update mask.<br>
	 * 5. OR the masked n with the update mask to set bit i to the value.
	 */
	public static int updateBit(int n, int i, int v) {
		int mask = ~(1 << i); // clear bit i
		return (n & mask) | (v << i); // set bit i to the value
	}
}