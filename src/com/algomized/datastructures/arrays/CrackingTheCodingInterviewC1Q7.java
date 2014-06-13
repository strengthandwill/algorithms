package com.algomized.datastructures.arrays;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row 
 * and column are set to 0.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC1Q7 {
	public static void main(String[] args) {
		int[][] mat = {	{1, 1, 1, 1, 1, 1, 1, 1},
						{1, 0, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 0, 1}};
		print(mat);
		System.out.println("---------------");
		setZeros(mat);
		print(mat);
	}
	
	/**
	 * Time:  Average = Worst = O(nm) + O(nm) = O(nm)<br>
	 * Space: Worst = Worst = 2 boolean arrays = O(n) + O(m)
	 * 
	 */
	public static void setZeros(int[][] mat) {
		if (mat == null) {
			return;
		}		
		
		boolean[] rows = new boolean[mat.length];
		boolean[] cols = new boolean[mat[0].length];
		for (int i = 0; i < mat.length; i ++) { // find zero rows and cols
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == 0) {
					rows[i] = true;
					cols[j] = true;
				}
			}
		}
		
		for (int i = 0; i < mat.length; i++) { // set rows or cols to zero
			for (int j = 0; j < mat[0].length; j++) {
				if (rows[i] || cols[j]) {
					mat[i][j] = 0;
				}
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
