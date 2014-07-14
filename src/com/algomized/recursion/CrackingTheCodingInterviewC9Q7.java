package com.algomized.recursion;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Implement the "paint fill" function that one might see on many image editing
 * programs. That is, given a screen (represented by a two-dimensional array of
 * colors), a point, and a new color, fill in the surrounding area until the color
 * changes from the original color.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC9Q7 {
	public static void main(String[] args) {
		int[][] screen = {	{0, 0, 0, 0, 0},
							{0, 1, 1, 0, 0},
							{0, 0, 1, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0}};
		printScreen(screen);
		System.out.println(paintFill(screen, 1, 1, 5));
		printScreen(screen);
	}
	
	public static void printScreen(int[][] screen) {
		for (int y = 0; y < screen.length; y++) {
			for (int x = 0; x < screen[0].length; x++) {
				System.out.print(screen[y][x] + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * Time:  Worst = O(mn), m = width, n = height of screen<br>
	 * Space: Worst = O(mn) 
	 */
	public static boolean paintFill(int[][] screen, int x, int y, int newColor) {
		if (!checkRange(x, y, screen[0].length, screen.length) ||
			screen[y][x] == newColor) return false;
		paintFill(screen, x, y, screen[y][x], newColor);
		return true;
	}
	
	private static void paintFill(int[][] screen, int x, int y, 
			int changeColor, int newColor) {
		if (!checkRange(x, y, screen[0].length, screen.length) || 
				screen[y][x] != changeColor) return;
		screen[y][x] = newColor;
		paintFill(screen, x - 1, y, changeColor, newColor);
		paintFill(screen, x + 1, y, changeColor, newColor);
		paintFill(screen, x, y + 1, changeColor, newColor);
		paintFill(screen, x, y - 1, changeColor, newColor);		
	}
	
	private static boolean checkRange(int x, int y, int width, int height) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}
}