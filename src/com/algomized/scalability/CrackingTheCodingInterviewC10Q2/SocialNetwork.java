package com.algomized.scalability.CrackingTheCodingInterviewC10Q2;

import java.util.HashMap;

public class SocialNetwork {
	private HashMap<Long, Machine> machines;
	private HashMap<Long, Long> personToMachine;
	
	public SocialNetwork() {
		machines = new HashMap<Long, Machine>();
		personToMachine = new HashMap<Long, Long>();
	}
		
	public Machine getMachineFromID(long machineID) {
		return machines.get(machineID);
	}
	
	public long getMachineIDForPerson(long personID) {
		Long machineID = personToMachine.get(personID);		
		return machineID != null ? machineID : -1;
	}
	
	public Person getPersonWithID(long personID) {
		long machineID =  getMachineIDForPerson(personID);
		if (machineID == -1) return null;
		Machine machine = getMachineFromID(machineID);
		if (machine == null) return null;
		return machine.getPersonFromID(personID);
	}
	
	public void addConnection(long person1ID, long person2ID) {
		Person person1 = getPersonWithID(person1ID);
		if (person1 == null) return;
		Person person2 = getPersonWithID(person2ID);
		if (person2 == null) return;
		person1.addFriend(person2ID);
		person2.addFriend(person1ID);
	}
}