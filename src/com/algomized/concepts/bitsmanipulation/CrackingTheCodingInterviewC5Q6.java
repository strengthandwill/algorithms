package com.algomized.concepts.bitsmanipulation;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Write a program to swap odd and even bits in an integer with as few instructions
 * as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and
 * soon).
 * </p>
 *
 */
public class CrackingTheCodingInterviewC5Q6 {
	public static void main(String[] args) {
		int n = 13948;
		System.out.println(Integer.toBinaryString(n));
		System.out.println(Integer.toBinaryString(swapBits(n)));
	}
	
	/**
	 * 1. AND n with odd mask to create extract odd bits a.<br>
	 * 2. Shift left by 1 to move into even position.<br>
	 * 3. AND n with even mask to extract even bits b.<br>
	 * 4. Shift right by 1 to move into odd position.<br>
	 * 5. OR a and b to merge them. 
	 */
	public static int swapBits(int n) {
		int a = (n & getMask(false)) << 1;
		int b = (n & getMask(true)) >> 1;
		return a | b;		
	}
	
	public static int getMask(boolean even) {
		int n = 0;
		for (int i = 0; i < 32; i++) {
			if (i % 2 == (even ? 1 : 0)) {
				n |= 1 << i;
			}
		}
		return n;
	}
}
