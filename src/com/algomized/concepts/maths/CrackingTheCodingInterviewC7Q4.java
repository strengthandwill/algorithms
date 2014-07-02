package com.algomized.concepts.maths;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Write methods to implement the multiply, subtract, and divide operations for
 * integers. Use only the add operator
 * </p>
 *
 */
public class CrackingTheCodingInterviewC7Q4 {
	public static void main(String[] args) {
		System.out.println(substract(10, 2));
		System.out.println(multiply(10, 3));
		System.out.println(divide(10, 2));
		
		System.out.println(substractBitwise(10, 2));
		System.out.println(multiplyBitwise(10, 3));
		System.out.println(divideBitwise(10, 2));
	}
	
	public static int substract(int a, int b) {
		return a + negate(b);
	}
	
	private static int negate(int n) {
		int r = 0;
		int s = n > 0 ? -1 : 1;
		for (int i = 0; i < Math.abs(n); i++) {
			r += s;
		}
		return r;
	}
	
	public static int multiply(int a, int b) {
		if (b > a) { // faster with b smaller than a
			return multiply(b, a);
		}
		
		int r = 0;
		for (int i = 0; i < Math.abs(b); i++) {
			r += a;
		}
		return b > 0 ? r : negate(r);
	}
	
	public static int divide(int a, int b) {
		if (b == 0) { // divide by zero
			return -1;
		}
		// result will be between 0 and 1, so will be zero 
		// for an integer value
		if (a < b) {
			return 0;
		}
		
		int r = 0;
		int x = 0;
		while (Math.abs(r) < Math.abs(a)) {
			r += b;
			x++;
		}
		return (a > 0 && b > 0) || (a < 0 && b < 0) ? x : negate(x);
	}
		
	public static int substractBitwise(int a, int b) {
		int b2 = ~b + 1; // compute two's complement of b
		return a + b2;
	}
	
	public static int multiplyBitwise(int a, int b) {
		int r = 0;
		int i = 0;
		while (b > 0) {
			if ((b & 1) == 1) {
				r += a << i;
			}
			b = b >> 1;
			i++;
		}
		return r;
	}
	
	public static int divideBitwise(int a, int b) {
		int aLen = length(a);
		int bLen = length(b);
		if (aLen < bLen) {
			return -1;
		}		
		int b2 = b << (substractBitwise(aLen, bLen)); // align b to a
		int q = 0;
		while (b2 >= b) {
			int t = substractBitwise(a, b2);
			if (t >= 0) {
				q++;
				a = t;
			}
			q = q << 1;
			b2 = b2 >> 1;			
		}
		return q >> 1;
	}
	
	private static int length(int n) {
		int length = 0;
		while (n > 0) {
			length++;
			n = n >> 1;
		}
		return length;		
	}
}