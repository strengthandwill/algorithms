package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q7;

public class Piece {
	
	private boolean white;
	
	public Piece(boolean white) {
		this.white = white;
	}
		
	public void flipColor() {
		white = !white;
	}

	public boolean isWhite() {
		return white;
	}	
}
