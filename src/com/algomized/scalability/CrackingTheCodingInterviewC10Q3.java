package com.algomized.scalability;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given an input file with four billion non-negative integers, provide an algorithm
 * to generate an integer which is not contained in the file. Assume you have 1 GB
 * of memory available for this task.<br>
 * FOLLOW UP<br>
 * What if you have only 10 MB of memory? Assume that all the values are distinct
 * and we now have no more than one billion non-negative integers<br>
 * <br>
 * Integer => 4 bytes => 32 bits<br>
 * Non-negative integer => 31 bits => 2 ^ 31 values => 2 ^ 31 bits for bit vector<br>
 * 1 GB => 2 ^ 30 bytes = 2 ^ 30 * 8 bits = 2 ^ 33 bits<br>
 * So memory is able to hold the bit vector for all values of integer<br> 
 * <br>
 * FOLLOW UP<br>
 * 10 MB => 2 ^ 20 * 10 bytes ~= 2 ^ 23 bytes<br>
 * So memory is not able to hold the bit vector for all values of integer<br>
 * Therefore, bit vector can only hold block of values => array of blocks needed<br>
 * Assuming all values of the file are distinct:<br>
 * <ol>
 * <li>Split the values in file into blocks.</li>
 * <li>Increment the corresponding block using value of the file as value.</li> 
 * <li>
 * Block value which has value less that the bit vector size contains the missing integer.<br>
 * Run the block with the bit vector to determine the missing integer
 * </li>
 * </ol>
 * </p>
 *
 */
public class CrackingTheCodingInterviewC10Q3 {
	public final static int INTEGER_MAX = 16;
	
	public static void main(String[] args) {
		int[] file = {0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		System.out.println(getNewInteger(file));
		System.out.println(getNewIntegerUsingMultiplePasses(file, 1));
		System.out.println(getNewIntegerUsingTwoPasses(file, 10));
	}
	
	/**
	 * Memory can hold the bit vector for all the values of integers.
	 */
	public static int getNewInteger(int[] file) {
		byte[] bv = new byte[INTEGER_MAX / 8];
		for (int i = 0; i < file.length; i++) { // update bit vector
			setBit(bv, file[i]);
		}		
		for (int i = 0; i < INTEGER_MAX; i++) { // find first false bit
			if (!getBit(bv, i)) return i;
		}						
		return -1;
	}

	/**
	 * Memory is not able to hold the bit vector for all the values of integers.<br>
	 * Need (number of values of integer) / (capacity of memory) passes.<br>
	 * File may contain duplicate values.
	 * @param capacity in bytes
	 */
	public static int getNewIntegerUsingMultiplePasses(int[] file, int capacity) {
		int bitCapacity = capacity * 8;
		int n = INTEGER_MAX / bitCapacity;
		for (int i = 0; i < n; i++) {
			int j = getNewInteger(file, i * bitCapacity, bitCapacity);
			if (j != -1) return j;
		}
		return -1;
	}
	
	private static int getNewInteger(int[] file, int index, int bitCapacity) {
		byte[] bv = new byte[bitCapacity];
		for (int i = 0; i < file.length; i++) { // update bit vector
			setBit(bv, file[i] - index * bitCapacity);
		}		
		for (int i = 0; i < bitCapacity; i++) { // find first false bit
			if (!getBit(bv, i)) return i + index * bitCapacity;
		}						
		return -1;
	}
	
	/**
	 * Memory is not able to hold the bit vector for all the values of integers.<br>
	 * Need (number of values of integer) / (capacity of memory) passes.<br>
	 * File must only contain distinct values.
	 * Integer array of size n = 4n bytes<br>
	 * Bit Vector of m bits = m / 8 bytes<br>
	 * Capacity >= 4n + (m / 8) bytes 
	 * @param capacity in bytes
	 */
	public static int getNewIntegerUsingTwoPasses(int[] file, int capacity) {
		int blockNum = 2;
		int bitSize = 8;
		if ((blockNum * 4 + bitSize / 8) > capacity) return -1;
		
		// first pass of file using int array
		int[] blocks = new int[blockNum];
		byte[] bv = new byte[bitSize / 8];
		for (int i = 0; i < file.length; i++) {
			blocks[file[i] / bitSize]++;
		}
		int start = -1;
		for (int i = 0; i < blockNum; i++) {
			if (blocks[i] < bitSize) {
				start = i;
				break;
			}
		}
		if (start == -1) return -1; // all the numbers are taken
		
		// second pass of file using bit vector 
		for (int i = 0; i < file.length; i++) {
			int v = file[i];
			if ((v >= bitSize * start) && (v < bitSize * (start + 1))) {
				setBit(bv, v % bitSize);
			}
		}
		for (int i = 0; i < bitSize; i++) {
			if (!getBit(bv, i)) {
				return i + start * bitSize;
			}
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
