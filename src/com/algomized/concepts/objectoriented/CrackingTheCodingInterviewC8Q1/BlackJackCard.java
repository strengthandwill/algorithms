package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q1;

public class BlackJackCard extends Card {
	private static final int PICTURE_VALUE = 10;
	private static final int ACE_MIN_VALUE = 1;
	private static final int ACE_MAX_VALUE = 11;
	private static final int MAX_POINTS = 21;

	public BlackJackCard(Suit suit, int value) {
		super(suit, value);
	}
	
	@Override
	public int getValue() {
		return value <= 11 && value >= 13 ? PICTURE_VALUE : value;		
	}
	
	public int getValue(int points) {
		return (points + ACE_MAX_VALUE) <= MAX_POINTS ? 
				ACE_MAX_VALUE : ACE_MIN_VALUE;		
	}
	
	public boolean isAce() {
		return value == 1;
	}
}
