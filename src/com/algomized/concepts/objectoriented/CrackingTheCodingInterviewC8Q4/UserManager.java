package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4;

import java.util.HashMap;

public class UserManager {
	private HashMap<Long, User> users;
	
	public UserManager() {
		users = new HashMap<Long, User>();
	}
	
	public boolean contains(User user) {
		return users.containsKey(user.getId());
	}	
	
	public boolean addUser(User user) {
		if (users.containsKey(user.getId())) {
			return false;
		}
		users.put(user.getId(), user);
		return true;
	}
	
	public User getUser(long id) {
		if (!users.containsKey(id)) {
			return null;
		}
		return users.get(id);
	}

	public User removeUser(long id) {
		if (!users.containsKey(id)) {
			return null;
		}
		return users.remove(id);
	}
	
	public Iterable<ReadBook> getBooks(long id) {
		if (!users.containsKey(id)) {
			return null;
		}
		return users.get(id).getBooks();
	}	
	
	public boolean contains(long id) {
		return users.containsKey(id);
	}	
	
	public Iterable<User> getUsers() {
		return users.values();
	}
}
