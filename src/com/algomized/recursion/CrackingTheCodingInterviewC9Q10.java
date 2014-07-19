package com.algomized.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * You have a stack of n boxes, with widths w., heights hir and depths drThe boxes
 * cannot be rotated and can only be stacked on top of one another if each box
 * in the stack is strictly larger than the box above it in width, height, and depth.
 * Implement a method to build the tallest stack possible, where the height of a
 * stack is the sum of the heights of each box.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC9Q10 {	
	public static void main(String[] args) {
		List<Box> boxes = new ArrayList<Box>();
		boxes.add(new Box(5, 5, 5));
		boxes.add(new Box(4, 6, 4));
		boxes.add(new Box(3, 3, 3));
		boxes.add(new Box(2, 4, 2));
		boxes.add(new Box(1, 1, 1));
		System.out.println(getMaxStack(boxes));		
		System.out.println(getMaxStackRecursive(boxes));
		System.out.println(getMaxStackDP(boxes));
	}
	
	public static List<Box> getMaxStack(List<Box> boxes) {
		if (boxes == null) return null;
		Collections.sort(boxes);
		List<List<Box>> boxStacks = getBoxStacks(boxes.toArray(new Box[boxes.size()]), boxes.size());
		return max(boxStacks);		
	}
		
	private static List<List<Box>> getBoxStacks(Box[] boxes, int n) {
		if (n == 0) {
			List<List<Box>> moreBoxStacks = new ArrayList<List<Box>>();
			moreBoxStacks.add(new ArrayList<Box>());
			return moreBoxStacks;
		}
		
		List<List<Box>> boxStacks = getBoxStacks(boxes, n - 1);
		List<List<Box>> moreBoxStacks = new ArrayList<List<Box>>();
		Box box = boxes[n - 1];
		for (List<Box> s : boxStacks) {
			if (s.isEmpty() || canFit(s.get(s.size() - 1), box)) {
				List<Box> newBoxStack = new ArrayList<Box>();
				newBoxStack.addAll(s);
				newBoxStack.add(box);
				moreBoxStacks.add(newBoxStack);
			}
		}
		boxStacks.addAll(moreBoxStacks);
		return boxStacks;
	}	
	
	private static boolean canFit(Box b1, Box b2) {
		return b1.width < b2.width && b1.length < b2.length && b1.height < b2.height;
	}
	
	private static List<Box> max(List<List<Box>> boxStacks) {
		int max = 0;	
		List<Box> maxList  = null;
		for (List<Box> b : boxStacks) {
			int h = getHeight(b);
			if (maxList == null || h > max) {
				maxList = b;
				max = h;
			}
		}
		return maxList;
	}
	
	private static int getHeight(List<Box> boxStack) {
		if (boxStack == null) return 0;
		int height = 0;
		for (Box b : boxStack) {
			height += b.height;
		}
		return height;
	}
	
	public static List<Box> getMaxStackRecursive(List<Box> boxes) {
		if (boxes == null) return null;
		return getMaxStackRecursive(boxes.toArray(new Box[boxes.size()]), null);		
	}
	
	public static List<Box> getMaxStackRecursive(Box[] boxes, Box bottom) {		
		int maxHeight = 0;
		List<Box> maxStack = null;		
		for (int i = 0; i < boxes.length; i++) {
			if (bottom == null || (boxes[i] != bottom && canFit(boxes[i], bottom))) {
				List<Box> stack = getMaxStackRecursive(boxes, boxes[i]);
				int height = getHeight(stack);
				if (height > maxHeight) {
					maxStack = stack;
					maxHeight = height;							
				}
			}
		}
		
		if (maxStack == null) maxStack = new ArrayList<Box>();		
		if (bottom != null) maxStack.add(bottom);		
		return maxStack;		
	}	
	
	public static List<Box> getMaxStackDP(List<Box> boxes) {
		if (boxes == null) return null;
		return getMaxStackDP(boxes.toArray(new Box[boxes.size()]), 
				null, new HashMap<Box, List<Box>>());
	}
	
	@SuppressWarnings("unchecked")
	private static List<Box> getMaxStackDP(Box[] boxes, Box bottom, Map<Box, List<Box>> cache) {
		if (bottom != null && cache.containsKey(bottom)) {
			return (List<Box>) ((ArrayList<Box>) cache.get(bottom)).clone();
		}
		
		List<Box> maxStack = null;
		int maxHeight = 0;
		for (int i = 0; i < boxes.length; i++) {
			if (bottom == null || canFit(boxes[i], bottom)) {				
				List<Box >stack = getMaxStackDP(boxes, boxes[i], cache);				
				int height = getHeight(stack);
				if (height > maxHeight) {
					maxStack = stack;
					maxHeight = height;
				}
			}
		}
		if (maxStack == null) maxStack = new ArrayList<Box>();
		if (bottom != null) {
			maxStack.add(bottom);
			cache.put(bottom, maxStack);
		}			
		return maxStack;
	}
		
	static class Box implements Comparable<Box> {
		int width;
		int length;
		int height;
		
		public Box(int width, int length, int height) {
			this.width = width;
			this.length = length;
			this.height = height;
		}

		@Override
		public int compareTo(Box b) {
			int area1 = width * length;
			int area2 = b.width * b.length;
			if (area1 > area2) return 1;
			else if (area1 < area2) return -1;
			else return 0;					
		}
				
		@Override
		public String toString() {
			return "(" + width + "," + length + "," + height + ")";
		}
	}	
}