package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q6A2;

import java.util.HashMap;

public class UserManager {
	private static UserManager instance = null;	
	private long id = 1;
	private HashMap<Long, User> users;
	
	private UserManager() {
		users = new HashMap<Long, User>();
	}
	
	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	public long addUser(User user) {
		if (users.containsKey(user.getId())) {
			return -1;
		}
		user.setId(id);
		users.put(id, user);
		return id++;		
	}
	
	public User getUser(long id) {
		if (!users.containsKey(id)) {
			return null;
		}
		return users.get(id);
	}
	
	public User getUserByName(String name) {
		for (User u : users.values()) {
			if (u.getName().equals(name)) {
				return u;
			}
		}
		return null;
	}	
	
	public User removeUser(long id) {
		if (!users.containsKey(id)) {
			return null;
		}
		return users.remove(id);
	}
	
	public User signInUser(String username, String password) {
		if (username == null || password == null) {
			return null;
		}
		for (User u : users.values()) {
			if (u.getUsername().equals(username) && 
				u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
}
