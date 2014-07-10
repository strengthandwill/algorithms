package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q7;

import java.util.ArrayList;
import java.util.List;

public class Move {
	public static enum Dir {
		NORTH(0, -1), SOUTH(0, 1), EAST(1, 0), WEST(-1, 0), 
		NORTHEAST(1, -1), NORTHWEST(-1, -1), SOUTHEAST(1, 1), SOUTHWEST(-1, 1);
		int dx;
		int dy;
		Dir(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
	};
	
	private int x;
	private int y;
	private List<Value> values;
	
	class Value {
		private Dir dir;
		private int dist;
		
		public Value(Dir dir, int dist) {
			this.dir = dir;
			this.dist = dist;
		}
		
		public Dir getDir() {
			return dir;
		}
		
		public int getDist() {
			return dist;
		}
	}
	
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
		values = new ArrayList<Value>();
	}
	
	public void add(Dir dir, int dist) {
		values.add(new Value(dir, dist));
	}
	
	public List<Value> getValues() {
		return values;
	}
	
	public boolean hasMove() {
		return !values.isEmpty();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void execute() {
	}
}
