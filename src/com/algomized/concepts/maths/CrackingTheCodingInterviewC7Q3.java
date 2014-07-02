package com.algomized.concepts.maths;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given two lines on a Cartesian plane, determine whether the two lines would
 * intersect<br>
 * <br>
 * Uses of data structure for the problem if possible to demonstrate 
 * object-oriented design.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC7Q3 {

	public static void main(String[] args) {
		Line l1 = new Line(1, 2);
		Line l2 = new Line(3, 4);		
		System.out.println(l1.intersect(l2));
	}
		
	static class Line {
		double gradient;
		double yIntercept;
		
		public Line(double gradient, double yIntercept) {
			this.gradient = gradient;
			this.yIntercept = yIntercept;
		}		
		
		public boolean intersect(Line line2) {
			// different gradient or same gradient and same y-intercept
			return gradient != line2.gradient || yIntercept == line2.yIntercept;
		}
	}

}
