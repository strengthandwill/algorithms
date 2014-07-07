package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4A2;

public class Car {
	private String plateNum;
	private User user;
	
	public Car(String plateNum, User user) {
		this.plateNum = plateNum;
		this.user = user;
	}

	public String getPlateNum() {
		return plateNum;
	}
	
	public User getUser() {
		return user;
	}
}
