package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q1;

public class Card {
	public static enum Suit {
		SPADE (4), HEART (3), CLUB (2), DIAMOND (1);		
		int value;
		Suit(int value) {
			this.value = value;
		}
		
		public static Suit getSuit(int value) {
			for (Suit suit : Suit.values()) {
				if (suit.value == value) {
					return suit;
				}
			}
			return null;
		}
	};
	
	protected Suit suit;
	protected int value;
	
	public Card(Suit suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getValueStr() {
		String valueStr = null;
		switch (value) {
			case 1:  valueStr = "ACE"; break;
			case 11: valueStr = "JACK"; break;
			case 12: valueStr = "QUEEN"; break;
			case 13: valueStr = "KING"; break;
			default: valueStr = String.valueOf(value); break;
		}
		return valueStr;
	}
	
	@Override
	public String toString() {
		return "[" + getSuit().toString() + ":" + getValueStr() + "]";
	}
}