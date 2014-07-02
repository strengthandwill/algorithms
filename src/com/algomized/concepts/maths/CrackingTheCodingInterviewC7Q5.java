package com.algomized.concepts.maths;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given two squares on a two-dimensional plane, find a line that would cut these
 * two squares in half. Assume that the top and the bottom sides of the square run
 * parallel to the x-axis.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC7Q5 {

	public static void main(String[] args) {
		Square s1 = new Square();
		s1.top.x = 1;
		s1.top.y = 2;
		s1.bottom.x = 3;
		s1.bottom.y = 4;
		Square s2 = new Square();
		s2.top.x = 6;
		s2.top.y = 7;
		s2.bottom.x = 8;
		s2.bottom.y = 9;
		Line line = s1.cut(s2);
		System.out.println(line.start.x + ", " + line.start.y);
		System.out.println(line.end.x + ", " + line.end.y);
	}
	
	static class Square {
		Point top;
		Point bottom;
		
		public Square() {
			top = new Point();
			bottom = new Point();			
		}
		
		public Line cut(Square s2) {
			if (s2 == null) {
				return null;
			}			
			Point m1 = new Point(); // compute middle point of square
			m1.x = (top.x + bottom.x) / 2;
			m1.y = (top.y + bottom.y) / 2;
			
			Point m2 = new Point(); // compute middle point of s2
			m2.x = (s2.top.x + s2.bottom.x) / 2;
			m2.y = (s2.top.y + s2.bottom.y) / 2;			
			
			Line line = new Line();
			if (m1.x == m2.x) { // vertical line
				line.start.x = m1.x;
			} else if (m1.y == m2.y) { // horizontal line
				line.start.y = m1.y;
			} else {
				double gradient = (m2.y - m1.y) / (m2.x - m1.x);
				double yIntercept = m1.y - gradient * m1.x;				
				if (Math.abs(gradient) > 1) { // steep slope
					line.start.x = (top.x - yIntercept) / gradient;
					line.start.y= top.y;
					line.end.x = (s2.bottom.y - yIntercept) / gradient;
					line.end.y = s2.bottom.y;
				} else { // gentle slope
					line.start.x = top.x;
					line.start.y = gradient * top.x + yIntercept;
					line.end.x = s2.bottom.x;
					line.end.y = gradient * s2.bottom.x + yIntercept;
				}
			}
			return line;
		}
	}
	
	static class Line {
		Point start;
		Point end;
		
		public Line() {
			start = new Point();
			end = new Point();			
		}
	}
	
	static class Point {
		double x;
		double y;
	}
}
