package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q1;

public class Deck<T extends Card>{	
	private T[] cards;
	private int size;
	
	public Deck(T[] cards) {
		this.cards = cards;
		this.size = cards.length;
	}
	
	public T draw() {
		if (isEmpty()) {
			return null;
		}
		T card  = null;
		int random = 0;
		while (card == null) {
			random = (int) (Math.random() * cards.length);
			card = cards[random];
		}
		cards[random] = null;
		size--;
		return card;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
}
