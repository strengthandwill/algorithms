package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q1;

import java.util.Scanner;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Design the data structures for a generic deck of cards. Explain how you would
 * subclass the data structures to implement blackjack.
 * </p>
 *
 */
public class BlackJackGame {
	private final static int MAX_CARD_NUM = 52;
	private final static int SUIT_NUM = 4;
	private final static int CARD_PER_SUIT_NUM = 13;

	private Deck<BlackJackCard> deck;
	private Player one;
	private Player two;

	public static void main(String[] args) {
		BlackJackGame game = new BlackJackGame();
		game.play();
	}

	public BlackJackGame() {
		BlackJackCard[] cards = new BlackJackCard[MAX_CARD_NUM];
		for (int i = 1; i <= SUIT_NUM; i++) {
			for (int j = 1; j <= CARD_PER_SUIT_NUM; j++) {
				cards[(i - 1) * 13 + j - 1] = new BlackJackCard(Card.Suit.getSuit(i), j);
			}
		}			 		 
		deck = new Deck<BlackJackCard>(cards);

		one = new BlackJackPlayer("ONE");
		two = new BlackJackPlayer("TWO");
	}

	public void play() {
		Scanner sc = new Scanner(System.in);
		int t = 1;
		while (one.isPlaying() || two.isPlaying()) {
			System.out.println("*** Turn " + t + " ***");
			action(one, sc);
			System.out.println(one);
			action(two, sc);
			System.out.println(two);
			t++;
		}

		System.out.println("*** Game End ***");
		int cmp = one.compareTo(two);
		if (cmp > 0) {
			System.out.println("Player ONE wins!");
		} else if (cmp < 0) {
			System.out.println("Player TWO wins!");
		} else {
			System.out.println("Player ONE and TWO draws!");
		}		 
		System.out.println(one);
		System.out.println(two);
		sc.close();
	}

	private void action(Player player, Scanner sc) {
		if (!player.isPlaying()) {
			return;
		}		 
		String choice = null;
		while (choice == null || (!choice.equals("H") && !choice.equals("S"))) {
			System.out.print("Player " + player.name + " (H/S): ");
			choice = sc.nextLine();
		}
		if (choice.equals("H")) {
			player.add(deck.draw());
		} else {
			player.stop();
		}			 		 
	}
}
