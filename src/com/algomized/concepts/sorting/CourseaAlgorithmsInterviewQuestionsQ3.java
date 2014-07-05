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
public class CourseaAlgorithmsInterviewQuestionsQ3 {
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
		while (true) {
			int l;
			if (j == 0) { // move R pointer forward
				String color;
				while ((color = color(b, i)).equals("R")) {
					i++;
				}
				if (color.equals("W")) {
					j = i + 1;
					continue;
				} else { // color is B
					l = i;
				}
			} else { // move W pointer forward
				String color;
				while ((color = color(b, j)).equals("W")) {
					j++;
				}
				if (color.equals("R")) {
					swap(b, i, j);
					i++;
					j++;
					continue;
				} else { // color is W
					l = j;
				}
			}
			while (color(b, k).equals("B")) { // move black pointer backward
				k--;
			}
			if (l >= k) { // is sorted
				return;
			}
			swap(b, l, k);
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