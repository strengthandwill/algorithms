package com.algomized.datastructures.arrays;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given an image represented by an NxN matrix, where each pixel in the image is 
 * 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in 
 * place?
 * </p>
 */
public class CrackingTheCodingInterviewC1Q6 {
	public static void main(String[] args) {
		int[][] mat =  {{11, 12, 13, 14},
						{21, 22, 23, 24},
						{31, 32, 33, 34},
						{41, 42, 43, 44}};
		print(mat);
		System.out.println("---------------");
		rotate(mat);
		print(mat);		
	}
	
	/**
	 * Time:  Average = Worst = O(n^2)<br>
	 * Space: Worst = 2 int = O(1)
	 */
	public static void rotate(int[][] mat) {
		if (mat == null || mat.length != mat[0].length) {
			return;
		}		
		for (int i = 0; i < mat.length / 2; i++) {
			int last = mat.length - i - 1;
			for (int j = i; j < last; j++) {
				int temp = mat[i][j]; // move top to temp
				mat[i][j] = mat[last - j][i]; // move left to top
				mat[last - j][i] = mat[last][last - j]; // move bottom to left
				mat[last][last - j] = mat[j][last]; // move right to bottom
				mat[j][last] = temp; // move temp to left
			}
		}
	}
	
	public static void print(int[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}
}
