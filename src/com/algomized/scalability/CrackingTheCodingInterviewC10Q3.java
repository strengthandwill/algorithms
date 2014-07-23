package com.algomized.scalability;

public class CrackingTheCodingInterviewC10Q3 {
	public final static int INTEGER_MAX = 16;
	
	public static void main(String[] args) {
		int[] file = {0, 1, 3, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		System.out.println(getNewIntegerUsingBitVector(file));
		System.out.println(getNewIntegerUsingBitVector(file, 8));
	}
	
	public static int getNewIntegerUsingBitVector(int[] file) {
		byte[] bv = new byte[INTEGER_MAX / 8];
		for (int i = 0; i < file.length; i++) { // update bit vector
			setBit(bv, file[i]);
		}		
		for (int i = 0; i < INTEGER_MAX; i++) { // find first false bit
			if (!getBit(bv, i)) return i;
		}						
		return -1;
	}
	
	public static int getNewIntegerUsingBitVector(int[] file, int capacity) {
		int n = INTEGER_MAX / capacity;
		for (int i = 0; i < n; i++) {
			int j = getNewIntegerUsingBitVector(file, i * capacity, capacity);
			if (j != -1) return j;
		}
		return -1;
	}
	
	private static int getNewIntegerUsingBitVector(int[] file, int index, int capacity) {
		byte[] bv = new byte[capacity];
		for (int i = 0; i < file.length; i++) { // update bit vector
			setBit(bv, file[i] - index * capacity);
		}		
		for (int i = 0; i < capacity; i++) { // find first false bit
			if (!getBit(bv, i)) return i + index * capacity;
		}						
		return -1;
	}	
	
	private static void setBit(byte[] bv, int i) {
		bv[i / 8] |= (1 << (i % 8));
	}
	
	private static boolean getBit(byte[] bv, int i) {
		return (bv[i / 8] & (1 << (i % 8))) > 0;
	}
}
