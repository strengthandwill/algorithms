package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q7;

import java.util.ArrayList;
import java.util.List;

import com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q7.Move.Dir;
import com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q7.Move.Value;

public class Board {
	private Piece[][] pieces;
	private int whiteCount;
	private int blackCount;

	public Board(int size) {
		pieces = new Piece[size][size];

		// init pieces
		pieces[3][3] = new Piece(true);
		pieces[3][4] = new Piece(false);
		pieces[4][3] = new Piece(false);
		pieces[4][4] = new Piece(true);
		whiteCount = 2;
		blackCount = 2;
	}

	public List<Move> checkTurn(boolean whiteTurn) {
		List<Move> moves = new ArrayList<Move>();
		for (int y = 0; y < pieces.length; y++) {
			for (int x = 0; x < pieces[0].length; x++) {
				if (pieces[y][x] == null) {
					Move m = new Move(x, y);
					for (Dir dir : Dir.values()) {
						int dist = checkPosition(x, y, dir, whiteTurn);
						if (dist != -1) {
							m.add(dir, dist);
						}		
					}
					if (m.hasMove()) {
						moves.add(m);
					}
				}
			}
		}
		return moves;
	}
	
	private int checkPosition(int x, int y, Dir dir, boolean whiteTurn) {
		int x2 = x + dir.dx;
		int y2 = y + dir.dy;
		if (!checkRange(x2, y2)) {
			return -1;
		}				
		Piece p = pieces[y2][x2];
		if (p == null || (whiteTurn == p.isWhite())) {
			return -1;
		}
		int dist = 0;
		x2 += dir.dx;
		y2 += dir.dy;					
		while (p != null && checkRange(x2, y2) && (whiteTurn != p.isWhite())) {			
			p = pieces[y2][x2];
			dist++;
			x2 += dir.dx;
			y2 += dir.dy;			
		}
		if (p == null || (whiteTurn != p.isWhite())) { // not valid move
			return -1;
		}
		return dist;
	}
	
	public void execute(Move move, boolean whiteTurn) {
		if (whiteTurn) {
			whiteCount++;
		} else {
			blackCount++;			
		}		
		for (Value v : move.getValues()) {
			filpPosition(move.getX(), move.getY(), v.getDir(), v.getDist(), whiteTurn);		
			if (whiteTurn) {
				whiteCount += v.getDist();
				blackCount -= v.getDist();
			} else {
				blackCount += v.getDist();
				whiteCount -= v.getDist();			
			}
		}		
	}
	
	private void filpPosition(int x, int y, Dir dir, int dist, boolean whiteTurn) {
		if (pieces[y][x] == null) {
			pieces[y][x] = new Piece(whiteTurn);
		}
		for (int i = 0; i < dist; i++) { // valid move and flip color
			pieces[y + (i + 1) * dir.dy][x + (i + 1) * dir.dx].flipColor();
		}		
	}

	private boolean checkRange(int x, int y) {
		return x >= 0 && x < pieces[0].length && 
				y >= 0 && y < pieces.length;
	}	
	
	public int getWhiteCount() {
		return whiteCount;
	}
	
	public int getBlackCount() {
		return blackCount;
	}
	
	public void printBoard() {
		for (int y = 0; y < pieces.length; y++) {
			for (int x = 0; x < pieces[0].length; x++) {
				Piece p = pieces[y][x];
				if (p == null) System.out.print("[" + x + "," + y + "]");
				else if (p.isWhite()) System.out.print("[ W ]");
				else System.out.print("[ B ]");
			}
			System.out.println();
		}
	}	
}
