package com.algomized.concepts.bitsmanipulation;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double,
 * print the binary representation. If the number cannot be represented accurately
 * in binary with at most 32 characters, print "ERROR."
 * </p>
 *
 */
public class CrackingTheCodingInterviewC5Q2 {
	public static void main(String[] args) {
		System.out.println(toBinaryString(0.375));
		System.out.println(toBinaryString(Math.PI));
	}
	
	public static String toBinaryString(double n) {
		if (n < 0 || n >= 1.0) {
			return "ERROR";
		}
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("0.");
		while (n > 0) {
			n *= 2.0;
			if (n >= 1.0) {
				strBuf.append(1);
				n -= 1.0; // ensure 0 <= n < 1
			} else {
				strBuf.append(0);
			}
			if (strBuf.length() > 32) { // exceeds length of 32
				return "ERROR";
			}
		}
		return strBuf.toString();			
	}	
}
