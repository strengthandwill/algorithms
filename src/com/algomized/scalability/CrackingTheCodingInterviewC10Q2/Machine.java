package com.algomized.scalability.CrackingTheCodingInterviewC10Q2;

import java.util.HashMap;

public class Machine {
	private final long id;
	private HashMap<Long, Person> persons; 
	
	public Machine(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	public Person getPersonFromID(long personID) {
		return persons.get(personID);
	}
}
