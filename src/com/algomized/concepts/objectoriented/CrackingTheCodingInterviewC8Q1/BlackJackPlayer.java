package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q1;

import java.util.ArrayList;

public class BlackJackPlayer extends Player {
	private static final int MAX_POINTS = 21;
	private ArrayList<BlackJackCard> cards;

	public BlackJackPlayer(String name) {
		super(name);
		this.cards = new ArrayList<BlackJackCard>();
	}

	@Override
	public void add(Card card) {
		if (!playing || !(card instanceof BlackJackCard)) {
			return;
		}
		BlackJackCard bjc = (BlackJackCard) card;
		cards.add(bjc);
		points = calculatePoints();
	}
	
	public boolean isBusted() {
		return points > MAX_POINTS;
	}

	public int calculatePoints() {
		int points = 0;			 
		ArrayList<BlackJackCard> aces = new ArrayList<BlackJackCard>();			 
		for (BlackJackCard card : cards) {
			if (card.isAce()) {
				aces.add(card);
			} else {
				points += card.getValue();
			}
		}
		for (BlackJackCard card : aces) {
			points += card.getValue(points);
		}			 
		return points;
	}

	@Override
	public int compareTo(Player player) {
		if (!(player instanceof BlackJackPlayer)) {
			return super.compareTo(player);
		}
		
		BlackJackPlayer bjp = (BlackJackPlayer) player;		
		if (isBusted() && bjp.isBusted()) {
			return 0;
		} else if (isBusted()) {
			return -1;
		} else if (bjp.isBusted()) {
			return 1;
		}
		return super.compareTo(player);
	}
	
	@Override
	public String toString() {
		return "[Player " + name + "][Points:" + points + "]" + cards; 
	}	
}
