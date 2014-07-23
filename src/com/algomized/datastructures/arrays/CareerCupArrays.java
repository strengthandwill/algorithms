package com.algomized.datastructures.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given a NxN matrix which contains all distinct 1 to n^2 numbers, write code
 * to print sequence of increasing adjacent sequential numbers.<br>
 * <br>
 * E.g.<br>
 * 1 5 9<br>
 * 2 3 8<br>
 * 4 6 7<br>
 * Output: 6 7 8 9
 * </p>
 *
 */
public class CareerCupArrays {
	public static void main(String[] args) {
		int matrix[][] = {	{1, 5, 9},
							{2, 3, 8},
							{4, 6, 7}};		
		printLongestSequenceUsingHashMap(matrix);				
		printLongestSequenceUsingValueAsIndex(matrix);
	}
	
	public static void printLongestSequenceUsingHashMap(int[][] matrix) {
		HashMap<Integer, Position> map = new HashMap<Integer, Position>();    
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[0].length; c++) {
				map.put(matrix[r][c], new Position(r, c));
			}
		}

		ArrayList<List<Integer>> lists = new ArrayList<List<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		Position prev = null;
		int size = matrix.length * matrix.length;
		for (int k = 1; k <= size; k++) {
			Position p = map.get(k);    
			if (prev == null || 				
				((p.row == prev.row - 1 && p.col == prev.col) ||
				(p.row == prev.row + 1 && p.col == prev.col) ||
				(p.row == prev.row && p.col == prev.col + 1) ||
				(p.row == prev.row && p.col == prev.col- 1))) {
				list.add(k);
			} else {
				lists.add(list);
				list = new ArrayList<Integer>();
				list.add(k);
			}
			prev = p;		
		}
		lists.add(list);
		System.out.println(getLongestList(lists));            
	}

	private static List<Integer> getLongestList(ArrayList<List<Integer>> lists) {
		List<Integer> maxList = null;
		for (List<Integer> l : lists) {
			if (maxList == null || l.size() > maxList.size()) maxList = l;			
		}
		return maxList;
	}

	static class Position {
		int row;
		int col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void printLongestSequenceUsingValueAsIndex(int[][] matrix) {
		int n = matrix.length;
		int nSquared = n * n;
		Coordinate[] positions = new Coordinate[nSquared];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				positions[matrix[i][j] - 1] = new Coordinate(i, j);
			}
		}
		int maxLength = 1;
		int maxEnd = 1;
		int currentLength = 1;
		for (int i = 1; i < nSquared; ++i) {
			if (positions[i].isAdjacent(positions[i - 1])) {
				++currentLength;
			} else {
				if (currentLength > maxLength) {
					maxLength = currentLength;
					maxEnd = i;
				}
				currentLength = 1;
			}
		}
		if (currentLength > maxLength) {
			maxLength = currentLength;
			maxEnd = nSquared;
		}
		for (int i = maxEnd - maxLength + 1; i < maxEnd; ++i) {
			System.out.print(i + " ");
		}
		System.out.println(maxEnd);
		System.out.println();
	}

	static class Coordinate {
		private int x;
		private int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean isAdjacent(Coordinate c) {
			if (x == c.x && (y == c.y - 1 || y == c.y + 1)) {
				return true;
			}
			if (y == c.y && (x == c.x - 1 || x == c.x + 1)) {
				return true;
			}
			return false;
		}
	}	
}