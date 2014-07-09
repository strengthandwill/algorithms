package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q6A2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Explain how you would design a chat server. In particular, provide details about
 * the various backend components, classes, and methods. What would be the
 * hardest problems to solve?
 * </p>
 *
 */
public class ChatServer implements Runnable {	
	private UserManager userManager;
	private ArrayList<ChatHandler> handlers;
	private int ports;
	private boolean running = true;

	public static void main(String[] args) {
		ChatServer server = new ChatServer(1234);
		server.addUser(new User("a", "a", "Adam"));
		server.addUser(new User("b", "b", "Bob"));
		server.addUser(new User("c", "c", "Cal"));
		server.run();
	}		

	public ChatServer(int ports) {
		this.ports  = ports;
		userManager = UserManager.getInstance();		
		handlers = new ArrayList<ChatHandler>();
	}
	
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(ports);
			System.out.println("Listening to ports " + ports + ".");
			while (running) {
				Socket socket = serverSocket.accept();
				handlers.add(new ChatHandler(socket));
			}
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}	
	
	public boolean addUser(User user) {
		return userManager.addUser(user) != -1;
	}		

	class ChatHandler extends Thread {
		private User user = null;
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		
		public ChatHandler(Socket socket) {
			this.socket = socket;
			try {				
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.flush();				
				ois = new ObjectInputStream(socket.getInputStream());
				signInUser();
				start();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}	
		
		private void signInUser() {
			try {
				while (user == null) {
					User input = (User) ois.readObject();					
					user = userManager.signInUser(input.getUsername(), input.getPassword());
					writeObject(user == null ? input : user);
				}				
				System.out.println(user.getName() + " joined the chat");
			} catch (ClassNotFoundException | IOException e) {			
				try {
					ois.close();
					oos.close();
					socket.close();	
					handlers.remove(this);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}			
		}
		
		@Override
		public void run() {
			try {
				while (running) {
					Message message = (Message) ois.readObject();
					if (message.isBroadcast()) { // broadcast
						broadcast(message);
					} else { // point to point
						send(message.getRecipient(), message);
					}
				}				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println(user.getName() + " left the chat");
			} finally {
				try {
					ois.close();
					oos.close();
					socket.close();	
					handlers.remove(this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void send(String recipient, Object obj) {
			for (ChatHandler h : handlers) {
				if (!h.equals(this) && h.getUser().getName().equals(recipient)) {
					h.writeObject(obj);
					return;
				}
			}			
		}
						
		private void broadcast(Object obj) {
			for (ChatHandler h : handlers) {
				if (!h.equals(this)) {
					h.writeObject(obj);
				}
			}
		}
		
		public void writeObject(Object obj) {
			try {
				oos.writeObject(obj);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		public User getUser() {
			return user;
		}
	}
}
