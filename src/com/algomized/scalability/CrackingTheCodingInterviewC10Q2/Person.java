package com.algomized.scalability.CrackingTheCodingInterviewC10Q2;

import java.util.ArrayList;

public class Person {
	private final long id;
	private String name;
	private ArrayList<Long> friendIDs;
	
	public Person(long id) {
		this.id = id;
		friendIDs = new ArrayList<Long>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}
	
	public void addFriend(long friendID) {
		if (friendID == -1) return;
		friendIDs.add(friendID);		
	}
}
