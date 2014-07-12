package com.algomized.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Imagine a robot sitting on the upper left corner of an X by Y grid. The robot can
 * only move in two directions: right and down. How many possible paths are there
 * for the robot to go from (0,0) to (X,Y)?<br>
 * FOLLOW UP<br>
 * Imagine certain spots are "off limits," such that the robot cannot step on them.
 * Design an algorithm to find a path for the robot from the top left to the bottom
 * right.<br>
 * </p>
 *
 */
public class CrackingTheCodingInterviewC9Q2 {
	public static void main(String[] args) {
		System.out.println(movesUsingFactorialRecursive(5, 5));
		System.out.println(movesUsingFactorialIterative(5, 5));
		System.out.println(movesRecursive(5, 5));
		System.out.println(movesDP(5, 5));
		System.out.println(movesIterative(5, 5));
		
		List<Point> occupied = new ArrayList<Point>();
		occupied.add(new Point(5, 0));
		occupied.add(new Point(4, 0));
		occupied.add(new Point(3, 0));
		System.out.println(findPath(5, 5, occupied));
	}
	
	public static int movesUsingFactorialRecursive(int x, int y) {
		return  factorialRecursive(x + y) / 
				(factorialRecursive(x) * factorialRecursive(y));
	}
		
	public static int movesUsingFactorialIterative(int x, int y) {
		return  factorialIterative(x + y) / 
				(factorialIterative(x) * factorialIterative(y));
	}
	
	/**
	 * Time:  Worst = O(n)<br>
	 * Space: Worst = 1 int = O(1) 
	 */
	private static int factorialIterative(int n) {
		if (n < 1) {
			return -1;
		}
		int r = 1;
		for (int i = 2; i <= n; i++) {
			r *= i;
		}
		return r;
	}
	
	/**
	 * Time:  Worst = O(n)<br>
	 * Space: Worst = O(n) 
	 */
	private static int factorialRecursive(int n) {
		if (n == 1) return 1;
		return factorialRecursive(n - 1) * n;
	}

	/**
	 * Time:  Worst = O(2^max(x, y))<br>
	 * Space: Worst = O(2^max(x, y)) 
	 */
	public static int movesRecursive(int x, int y) {
		if (x <= 0 || y <= 0) return 1;
		return movesRecursive(x, y - 1) + movesRecursive(x - 1, y);
	}
	
	/**
	 * Time:  Worst = O(xy)<br>
	 * Space: Worst = O(xy)  
	 */
	public static int movesDP(int x, int y) {
		return movesDP(new int[x + 1][y + 1], x, y);
	}
	
	private static int movesDP(int[][] s, int x, int y) {
		if (x <= 0 || y <= 0) return 1;
		if (s[x][y - 1] == 0) {
			s[x][y - 1] = movesDP(s, x, y - 1);
		}
		if (s[x - 1][y] == 0) {
			s[x - 1][y] = movesDP(s, x - 1, y);
		}
		s[x][y] = s[x][y - 1] + s[x - 1][y];
		return s[x][y];
 	}
	
	/**
	 * Time:  Worst = O(xy)<br>
	 * Space: Worst = O(xy) 
	 */
	public static int movesIterative(int x, int y) {
		int[][] s = new int[x + 1][y + 1];
		for (int i = 0; i <= x; i++) {
			s[i][0] = 1;
		}
		for (int j = 0; j <= y; j++) {
			s[0][j] = 1;
		}
		for (int i = 1; i <= x; i++) {
			for (int j = 1; j <= y; j++) {
				s[i][j] = s[i][j - 1] + s[i - 1][j];
			}
		}
		return s[x][y];
	}	
	
	/**
	 * Time:  Worst = O(x + y)<br>
	 * Space: Worst = O(x + y) + O(x + y) = O(x + y)  
	 */
	public static List<Point> findPath(int x, int y, List<Point> occupied) {
		ArrayList<Point> path = new ArrayList<Point>();
		if (!findPath(new HashMap<Point, Boolean>(), x, y, occupied, path)) return null;
		else return path;
	}
	
	private static boolean findPath(Map<Point, Boolean> s, int x, int y, 
			List<Point> occupied, List<Point> path) {
		Point p = new Point(x, y);
		if (x == 0 && y == 0) {
			path.add(new Point(x, y));
			return true;		
		}
		if (x < 0 || y < 0) return false;
		
		boolean result = false;		
		if (y > 0 && !containsPoint(occupied, x, y - 1) && !s.containsKey(p)) {
			result = findPath(s, x, y - 1, occupied, path);			
		}
		if (!result && x > 0 && !containsPoint(occupied, x - 1, y) && !s.containsKey(p)) {
			result = findPath(s, x - 1, y, occupied, path);
		}
		s.put(p, result);
		if (result) path.add(new Point(x, y));
		return result;
 	}
	
	private static boolean containsPoint(List<Point> points, int x, int y) {
		for (Point s : points) {
			if ((s.x == x) && (s.y == y)) {
				return true;
			}
		}
		return false;
	}
	
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Point)) {
				return false;
			}
			Point s = (Point) o;
			return (x == s.x) && (y == s.y);			
		}
		
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}
}