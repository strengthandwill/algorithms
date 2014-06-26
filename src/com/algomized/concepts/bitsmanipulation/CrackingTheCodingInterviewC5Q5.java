package com.algomized.concepts.bitsmanipulation;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Write a function to determine the number of bits required to convert integer A
 * to integer B.<br>
 * EXAMPLE<br>
 * Input: 31,14<br>
 * Output: 2<br>
 * </p>
 *
 */
public class CrackingTheCodingInterviewC5Q5 {
	public static void main(String[] args) {
		int a = 31;
		int b = 14;
		System.out.println(countBitsDifference(a, b));
		System.out.println(countBitsDifferenceUsingFlippingLSB(a, b));
	}
	
	public static int countBitsDifference(int a, int b) {
		int d = a ^ b; // set bit difference to to one 
		int count = 0;
		while (d > 0) { // count one bits
			if ((d & 1) > 0) {
				count++;
			}
			d = d >> 1;
		}
		return count;
	}
	
	public static int countBitsDifferenceUsingFlippingLSB(int a, int b) {
		int d = a ^ b; // set bit difference to to one 
		int count = 0;
		while (d > 0) { // count one bits
			count++;			
			d = d & (d - 1);
		}
		return count;
	}	
}
