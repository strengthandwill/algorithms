package com.algomized.concepts.sorting;

import java.util.Arrays;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Dutch national flag. Given an array of N buckets, each containing a red, white, or blue pebble, 
 * sort them by color. The allowed operations are:<br>
 * swap(i,j): swap the pebble in bucket i with the pebble in bucket j.<br>
 * color(i): color of pebble in bucket i.<br>
 * <br>
 * The performance requirements are as follows:<br>
 * At most N calls to color().<br>
 * At most N calls to swap().<br>
 * Constant extra space.<br>
 * </p>
 *
 */
public class CourseraAlgorithmsInterviewQuestionsQ3 {
	public static void main(String[] args) {
		String[] b = {"R", "B", "W", "R", "B", "W", "R", "B", "W"};
		sort(b);
		System.out.println(Arrays.toString(b));
	}
	
	public static void sort(String[] b) {
		if (b == null) {
			return;
		}
		int i = 0, j = 0, k = b.length - 1;
		while (i < k && j < k) {
			String color;
			if (j == 0) { // move R pointer forward
				while ((color = color(b, i)).equals("R")) {
					i++;
				}
				if (color.equals("W")) {
					j = i + 1;
					continue;
				}
			} else { // move W pointer forward
				while ((color = color(b, j)).equals("W")) {
					j++;
				}
				if (color.equals("R")) {
					swap(b, i++, j++);
					continue;
				}
			}
			while ((color = color(b, k)).equals("B")) { // move black pointer backward
				k--;
			}
			if (j == 0) { // swapping with R			
				if (color.equals("W")) {
					swap(b, i, k--);	
					j = i + 1;
				} else {
					swap(b, i++, k--);
				}
			} else { // swapping with W
				if (color.equals("R")) {
					swap(b, i++, k);
					swap(b, k--, j++);
				} else {
					swap(b, j++, k--);
				}
			}
		}
	}
	
	private static void swap(String[] b, int p, int q) {
		String t = b[p];
		b[p] = b[q];
		b[q] = t;
	}
	
	private static String color(String[] b, int i) {
		return b[i];
	}
}