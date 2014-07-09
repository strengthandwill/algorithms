package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q5;

public abstract class Piece {
	public static enum Type {CORNER, SIDE, INNER};
	
	protected final int value;
	protected Piece[] pieces;
	
	public Piece(int value) {
		this.value = value;
	}
	
	public void setPieces(Piece[] pieces) {
		this.pieces = pieces;
	}
	
	public boolean fitsWith(Piece p2) {
		for (Piece p1 : pieces) {
			if (p1.equals(p2)) {
				return true;
			}
		}
		return false;
	}
	
	public abstract Type getType();
	
	@Override
	public String toString() {
		return String.format("[%d]", value);
	}
}

class CornerPiece extends Piece {
	public CornerPiece(int value) {
		super(value);
	}

	@Override
	public Type getType() {
		return Type.CORNER;
	}
}

class SidePiece extends Piece {
	public SidePiece(int value) {
		super(value);
	}

	@Override
	public Type getType() {
		return Type.SIDE;
	}
}

class InnerPiece extends Piece {
	public InnerPiece(int value) {
		super(value);
	}

	@Override
	public Type getType() {
		return Type.INNER;
	}
}