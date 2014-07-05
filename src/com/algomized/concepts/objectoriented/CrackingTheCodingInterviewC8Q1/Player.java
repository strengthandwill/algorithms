package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q1;

public abstract class Player implements Comparable<Player> {
	protected String name;
	protected boolean playing = true;
	protected int points;

	public Player(String name) {
		this.name = name;
	}	
	
	public abstract void add(Card card);			

	public void stop() {
		playing = false;
	}	

	public String getName() {
		return name;
	}

	public boolean isPlaying() {
		return playing;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public int compareTo(Player player) {
		if (points > player.points) {
			return 1;
		} else if (points < player.points) {
			return -1;
		} else {
			return 0;
		}							 
	}
}
