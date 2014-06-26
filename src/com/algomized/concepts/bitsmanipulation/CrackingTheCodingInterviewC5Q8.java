package com.algomized.concepts.bitsmanipulation;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * A monochrome screen is stored as a single array of bytes, allowing eight consecutive
 * pixels to be stored in one byte.The screen has width w, where w is divisible
 * by 8 (that is, no byte will be split across rows).The height of the screen, of course,
 * can be derived from the length of the array and the width. Implement a function
 * drawHorizontall_ine(byte[] screen, int width, int xl, int x2,
 * int y) which draws a horizontal line from (xl, y)to(x2, y).
 * </p>
 *
 */
public class CrackingTheCodingInterviewC5Q8 {

	public static void main(String[] args) {
		byte[] screen = {0, 0, 0, 0, 0, 0, 0, 0, 
						 0, 0, 0, 0, 0, 0, 0, 0,
						 0, 0, 0, 0, 0, 0, 0, 0,
						 0, 0, 0, 0, 0, 0, 0, 0};
		print(screen, 64);
		drawHorizontalLine(screen, 64, 3, 61, 2);
		System.out.println();
		print(screen, 64);
	}
	
	public static void drawHorizontalLine(byte[] screen, int width, int x1, int x2, int y) {
		if (screen == null) {
			return;
		}		
		int row = width / 8 * y;
		int x1Start = x1 / 8 + row;
		int x2End = x2 / 8 + row;
		
		if (x1Start == x2End) { // x1 and x2 in the same byte
			screen[x1Start] |= ((1 << (x2 - x1 + 1)) - 1) << (8 - (x1 % 8) - (x2 - x1 + 1));
			return;
		}
		
		for (int i = x1Start; i <= x2End; i++) {
			if (i == x1Start) { // draw line start partial byte
				screen[i] |= ((1 << (8 - (x1 % 8))) - 1);
			} else if (i == x2End) { // draw line end partial bypte 
				screen[i] |= ((1 << (x2 % 8 + 1)) - 1) << (8 - (x2 % 8 + 1));				
			} else { // draw full byte
				screen[i] = (byte) 0xff;
			}
		}
	}
	
	public static void print(byte[] screen, int width) {		
		for (int i = 0; i < (screen.length / (width / 8)); i++) {
			for (int j = 0; j < (width / 8); j++) {
				printBits(screen[j + i * 8]);
			}
			System.out.println();
		}
	}
	
	private static void printBits(byte b) {
		for (int i = 7; i >= 0; i--) {
			if ((b & (1 << i)) > 0) {
				System.out.print(1);
			} else {
				System.out.print(0);
			}
		}
	}
}