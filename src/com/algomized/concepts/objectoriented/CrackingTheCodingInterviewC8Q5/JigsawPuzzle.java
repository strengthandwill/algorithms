package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q5;

import java.util.ArrayList;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Implement a jigsaw puzzle. Design the data structures and explain an algorithm
 * to solve the puzzle. You can assume that you have a f itsWith method which,
 * when passed two puzzle pieces, returns true if the two pieces belong together
 * </p>
 *
 */
public class JigsawPuzzle {
	private ArrayList<Piece> corners;
	private ArrayList<Piece> sides;
	private ArrayList<Piece> inners;
	private Piece[][] solution;
	
	public static void main(String[] args) {		
		Piece p1 = new CornerPiece(1);
		Piece p2 = new SidePiece(2);
		Piece p3 = new CornerPiece(3);
		Piece p4 = new SidePiece(4);
		Piece p5 = new InnerPiece(5);
		Piece p6 = new SidePiece(6);
		Piece p7 = new CornerPiece(7);
		Piece p8 = new SidePiece(8);
		Piece p9 = new CornerPiece(9);	
		p1.setPieces(new Piece[]{p2, p4});
		p2.setPieces(new Piece[]{p1, p3, p5});
		p3.setPieces(new Piece[]{p2, p6});
		p4.setPieces(new Piece[]{p1, p5, p7});
		p5.setPieces(new Piece[]{p2, p4, p6, p8});
		p6.setPieces(new Piece[]{p3, p5, p9});
		p7.setPieces(new Piece[]{p4, p8});
		p8.setPieces(new Piece[]{p5, p7, p9});
		p9.setPieces(new Piece[]{p6, p8});
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		pieces.add(p3);
		pieces.add(p6);
		pieces.add(p8);
		pieces.add(p1);
		pieces.add(p7);
		pieces.add(p2);
		pieces.add(p9);
		pieces.add(p4);
		pieces.add(p5);	
		
		JigsawPuzzle puzzle = new JigsawPuzzle(3, 3, pieces);
		printSolution(puzzle.getSolution());
	}
	
	public static void printSolution(Piece[][] solution) {
		for (int y = 0; y < solution.length; y++) {
			for (int x = 0; x < solution[0].length; x++) {
				System.out.print(solution[y][x]);
			}
			System.out.println();
		}
	}
	
	public JigsawPuzzle(int w, int h, Iterable<Piece> pieces) {
		solution = new Piece[h][w];		
		corners = new ArrayList<Piece>();
		sides = new ArrayList<Piece>();
		inners = new ArrayList<Piece>();
		for (Piece p : pieces) {
			switch (p.getType()) {
				case CORNER:  corners.add(p);  break;
				case SIDE:  sides.add(p);  break;
				case INNER:   inners.add(p);   break;
			}
		}
		solve();
	}
	
	private void solve() {	
		solution[0][0] = corners.remove(corners.size() - 1); // first corner
		int n = (Math.min(solution[0].length, solution.length) + 1) / 2;
		for (int i = 0; i < n; i++) {
			int top = i;
			int bottom = solution.length - i - 1;
			int left = i;
			int right = solution[0].length - i - 1;
			for (int x = left; x <= (left == right ? right : right - 1); x++) {
				insertTop(x, top);
			}
			for (int y = top; y <= (top == bottom ? bottom : bottom - 1); y++) {
				insertRight(right, y);
			}
			for (int x = right; x >= (left + 1); x--) {
				insertBottom(x, bottom);
			}
			for (int y = bottom; y >= (top + 1); y--) {
				insertLeft(left, y);
			}
		}
	}
	
	private void insertTop(int x, int y) {
		if (solution[x][y] != null) {
			return;
		}		
		if (y == 0) { // outer layer
			Piece p = solution[x-1][y];
			solution[x][y] = findAndRemove(p, sides);
		} else {
			Piece p1 = solution[x-1][y];
			Piece p2 = solution[x][y-1];
			solution[x][y] = findAndRemove(p1, p2, inners);
		}
	}
	
	private void insertRight(int x, int y) {
		if (solution[x][y] != null) {
			return;
		}		
		if (x == (solution[0].length - 1) && y == 0) { // corner
			Piece p = solution[x-1][y];
			solution[x][y] = findAndRemove(p, corners);
			return;
		}		
		if (x == (solution[0].length - 1)) { // outer layer
			Piece p = solution[x][y-1];
			solution[x][y] = findAndRemove(p, sides);
		} else {
			Piece p1 = solution[x][y-1];
			Piece p2 = solution[x+1][y];
			solution[x][y] = findAndRemove(p1, p2, inners);
		}
	}	
	
	private void insertBottom(int x, int y) {
		if (solution[x][y] != null) {
			return;
		}				
		if (x == (solution[0].length - 1) && y == (solution.length - 1)) { // corner
			Piece p = solution[x][y-1];
			solution[x][y] = findAndRemove(p, corners);
			return;
		}		
		if (y == (solution.length - 1)) { // outer layer
			Piece p = solution[x+1][y];
			solution[x][y] = findAndRemove(p, sides);
		} else {
			Piece p1 = solution[x+1][y];
			Piece p2 = solution[x][y+1];
			solution[x][y] = findAndRemove(p1, p2, inners);
		}
	}
	
	private void insertLeft(int x, int y) {
		if (solution[x][y] != null) {
			return;
		}		
		if (x == 0 && y == (solution.length - 1)) { // corner
			Piece p = solution[x+1][y];
			solution[x][y] = findAndRemove(p, corners);
			return;
		}		
		if (x == 0) { // outer layer
			Piece p = solution[x][y+1];
			solution[x][y] = findAndRemove(p, sides);
		} else {
			Piece p1 = solution[x][y+1];
			Piece p2 = solution[x-1][y];
			solution[x][y] = findAndRemove(p1, p2, inners);
		}
	}	
	
	private Piece findAndRemove(Piece p1, ArrayList<Piece> list) {
		for (Piece q : list) {
			if (q.fitsWith(p1)) {
				list.remove(q);
				return q;
			}
		}
		return null;
	}
	
	private Piece findAndRemove(Piece p1, Piece p2, ArrayList<Piece> list) {
		for (Piece q : list) {
			if (q.fitsWith(p1) && q.fitsWith(p2)) {
				list.remove(q);
				return q;
			}
		}
		return null;
	}
	
	public Piece[][] getSolution() {
		return solution;
	}
}
