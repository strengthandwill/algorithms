package com.algomized.concepts.maths;

import com.algomized.datastructures.arrays.ArrayList;
import com.algomized.datastructures.hashtables.SeparateChainingHashtable;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given a two-dimensional graph with points on it, find a line which passes the 
 * most number of points.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC7Q6 {

	public static void main(String[] args) {
		Point[] points = new Point[10];
		points[0] = new Point(0, 1);
		points[1] = new Point(1, 2);
		points[2] = new Point(2, 3);
		points[3] = new Point(4, 5);
		points[4] = new Point(3, 5);
		points[5] = new Point(1, 0);
		points[6] = new Point(3, 4);
		points[7] = new Point(5, 2);
		points[8] = new Point(1, 3);
		points[9] = new Point(5, 3);
		
		Line maxLine = getMaxLine(points);
		System.out.println(maxLine.start.x + ", " + maxLine.start.y);
		System.out.println(maxLine.end.x + ", " + maxLine.end.y);
		System.out.println(maxLine.size());
	}
	
	public static Line getMaxLine(Point[] points) {
		if (points == null) {
			return null;
		}
		
		// compute all possible lines
		SeparateChainingHashtable<CompositeKey, Line> hashtable = new SeparateChainingHashtable<CompositeKey, Line>();
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				Line line = new Line(points[i], points[j]);
				CompositeKey key = new CompositeKey(line.gradient, line.yIntercept);				
				if (!hashtable.contains(key)) { // new line
					hashtable.put(key, line);
				} else { // add new line segment
					hashtable.get(key).add(line);
				}					
			}
		}
			
		// find line with the max number of points
		Line maxLine = null;
		for (CompositeKey key : hashtable.keys()) {
			Line line = hashtable.get(key);						
			if (maxLine == null || (line.size() > maxLine.size())) {
				maxLine = line;
			}
		}
		return maxLine;
	}
	
	static class Line {
		final double eslipson = 0.0001;
		final double gradient;
		final double yIntercept;
		final boolean isInfinite;
		Point start;
		Point end;
		ArrayList<Point> points = new ArrayList<Point>();
		
		public Line(Point start, Point end) {
			if (end.x - start.x == 0) {				
				gradient = 0;
				isInfinite = true;
			} else {			
				gradient = round((end.y - start.y) / (end.x - start.x));
				isInfinite = false;
			}
			yIntercept = round(start.y - gradient * start.x);			
			if (start.y < end.y) {
				this.start = start;
				this.end = end;				
			} else {
				this.start = end;
				this.end = start;				
			}
			points.add(start);
			points.add(end);
		}
		
		public void add(Line line) {
			if (line.start.y < start.y) {
				start = line.start;
			} else if (line.end.y > end.y) {
				end = line.end;
			}
			if (!points.contains(line.start)) {
				points.add(line.start);
			}
			if (!points.contains(line.end)) {
				points.add(line.end);
			}
		}
		
		public int size() {
			return points.size();
		}	
		
		private double round(double value) {
			return ((int)(value / eslipson)) * eslipson;
		}
		
		public String toString() {
			return "[" + gradient + "][" + yIntercept + "][" + points + "]"; 
		}
	}
	
	static class Point {
		double x;
		double y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return "[" + x + ", " + y + "]";
		}
	}
	
	/**
	 * 
	 * A line is identified by its gradient and y-intercept, so a composite key
	 * comprising of gradient and y-intercept is used in the hashtable.
	 * 
	 */
	static class CompositeKey {
		final double gradient;
		final double yIntercept;
		
		public CompositeKey(double gradient, double yIntercept) {
			this.gradient = gradient;
			this.yIntercept = yIntercept;			
		}

		public boolean equals(Object object) {
			if (!(object instanceof CompositeKey)) {
				return false;
			}
			CompositeKey key2 = (CompositeKey) object;
			return (gradient == key2.gradient) && (yIntercept == key2.yIntercept);
		}
		
		public int hashCode() {	
			String str = String.valueOf(gradient) + String.valueOf(yIntercept);
			return str.hashCode(); 
		}
	}
}
