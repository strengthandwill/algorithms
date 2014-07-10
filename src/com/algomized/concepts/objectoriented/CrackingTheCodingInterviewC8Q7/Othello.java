package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q7;

import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Othello is played as follows: Each Othello piece is white on one side and black
 * on the other. When a piece is surrounded by its opponents on both the left and
 * right sides, or both the top and bottom, it is said to be captured and its color is
 * flipped. On your turn, you must capture at least one of your opponent's pieces.
 * The game ends when either user has no more valid moves. The win is assigned
 * to the person with the most pieces. Implement the object-oriented design for
 * Othello.
 * </p>
 * 
 */
public class Othello {
	private final static int SIZE = 8;
	
	private Board board;
	private boolean whiteTurn = true;
	private int turn = 1;
	private List<Move> moves;
	 			
	public static void main(String[] args) {
		Othello othello = new Othello(true);
		othello.run();
	}	
	
	public Othello(boolean whiteTurn) {
		this.whiteTurn = whiteTurn;		
		board = new Board(SIZE);	
	}
	
	public void run() {
		System.out.println(String.format("*** %s starts ***", whiteTurn ? "White" : "Black"));
		
		Scanner sc = new Scanner(System.in);
		while (moves == null || !moves.isEmpty()) {
			moves = board.checkTurn(whiteTurn);
			board.printBoard();
			System.out.println(String.format("White score: %d, Black score : %d", 
					board.getWhiteCount(), board.getBlackCount()));
			System.out.println(String.format("Turn %d: %s has %d move(s)", 
				turn, whiteTurn ? "White" : "Black", moves.size()));
			
			// input move
			Move m = null;
			while (m == null) {
				System.out.print("x: ");
				int x = sc.nextInt();
				System.out.print("y: ");
				int y = sc.nextInt();
				m = findMove(x, y);
				if (m == null) System.out.println("Invalid move, please try again."); 
			}
			
			// execute valid move
			board.execute(m, whiteTurn);
			whiteTurn = !whiteTurn;
			turn++;
			System.out.println();
		}
		sc.close();
		
		// determine the result
		System.out.println("*** Game ended ***");
		if (board.getWhiteCount() > board.getBlackCount()) {
			System.out.println("White wins!");
		} else if (board.getWhiteCount() < board.getBlackCount()) {
			System.out.println("Black wins!");
		} else {
			System.out.println("White and Black draws.");
		}
	}
			
	private Move findMove(int x, int y) {
		for (Move m : moves) {
			if (m.getX() == x && m.getY() == y) {
				return m;
			}
		}
		return null;
	}	
}
