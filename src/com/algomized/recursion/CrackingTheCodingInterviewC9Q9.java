package com.algomized.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Write an algorithm to print all ways of arranging eight queens on an 8x8 chess
 * board so that none of them share the same row, column or diagonal. In this case,
 * "diagonal" means all diagonals, not just the two that bisect the board.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC9Q9 {
	public static final int SIZE = 8;
	
	public static void main(String[] args) {
		System.out.println(getQueenPositions());
	}

	public static List<List<Queen>> getQueenPositions() {
		List<List<Queen>> qq = new ArrayList<List<Queen>>();
		getQueenPositions(0, new Stack<Queen>(), qq);
		return qq;
	}
	
	@SuppressWarnings("unchecked")
	private static void getQueenPositions(int y, Stack<Queen> s, List<List<Queen>> qq) {
		if (y == SIZE) {
			qq.add((List<Queen>) s.clone());
			return;
		}
		for (int x = 0; x < SIZE; x++) {
			if (!collide(s, x, y)) {
				s.push(new Queen(x, y));
				getQueenPositions(y + 1, s, qq);
				s.pop();
			}
		}							
	}
	
	private static boolean collide(Stack<Queen> s, int x, int y) {
		for (Queen q : s) {
			if (q.collide(x, y)) return true;			
		}
		return false;
	}
	
	static class Queen {
		int x;
		int y;	
		
		public Queen(int x, int y) {
			this.x = x;
			this.y = y;				
		}
		
		public boolean collide(int x, int y) {
			return this.x == x || this.y == y || 
				Math.abs(this.x - x) == Math.abs(this.y - y);					 
		}
		
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}		
}