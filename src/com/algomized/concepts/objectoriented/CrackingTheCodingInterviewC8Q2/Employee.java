package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q2;

public abstract class Employee {
	public static final int MAX_LEVEL = 2;
	
	protected Call call;
	protected String name;
	
	public Employee(String name) {
		this.name = name;
	}
	
	public void receiveCall(Call call) {
		this.call = call;
	}
	
	public void completeCall() {
		call = null;
	}
	
	public Call escalateCall() {
		if (call == null) {
			return null;
		}
		Call escalatedCall = call;
		escalatedCall.escalate();
		call = null;
		return escalatedCall;
	}	
	
	public boolean isFree() {
		return call == null;
	}
	
	public abstract int getLevel();
	
	@Override
	public String toString() {
		return "[" + name + "][" + call + "]";
	}
}

class Respondent extends Employee {
	public Respondent(String name) {
		super(name);
	}

	@Override
	public int getLevel() {
		return 0;
	}	
	
	@Override
	public String toString() {
		return "[Respondent]" + super.toString();
	}	
}

class Manager extends Employee {
	public Manager(String name) {
		super(name);
	}

	@Override
	public int getLevel() {
		return 1;
	}	
	
	@Override
	public String toString() {
		return "[Manager]" + super.toString();
	}
}

class Director extends Employee {
	public Director(String name) {
		super(name);
	}

	@Override
	public int getLevel() {
		return 2;
	}
	
	@Override
	public Call escalateCall() {
		call = null;
		return null;
	}
	
	@Override
	public String toString() {
		return "[Director]" + super.toString();
	}	
}