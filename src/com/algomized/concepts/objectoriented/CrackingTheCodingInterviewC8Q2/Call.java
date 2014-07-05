package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q2;

public class Call {
	private int level = 0;
	private String message;

	public Call(String message) {
		this.message = message;
	}
	
	public void escalate() {
		if (level >= Employee.MAX_LEVEL) {
			return;
		}
		level++;
	}
	
	public int getLevel() {
		return level;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "[" + level + ":" + message + "]";
	}
}
