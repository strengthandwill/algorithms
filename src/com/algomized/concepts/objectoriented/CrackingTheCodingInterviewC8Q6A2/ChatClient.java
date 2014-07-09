package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q6A2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
	private User user = null;
	private Thread listener;
	private Thread 	eventHandler;
	private boolean running = true;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Scanner sc;

	public static void main(String[] args) {
		new ChatClient("127.0.0.1", 1234);		
	}

	public ChatClient(String serverIP, int serverPorts) {		
		sc = new Scanner(System.in);
		try {
			socket = new Socket(serverIP, serverPorts);
			System.out.println("Connected to " + serverIP + ":" + serverPorts + ".");			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();			
			ois = new ObjectInputStream(socket.getInputStream());
			listener = new Listener();
			eventHandler = new EventHandler();

			
			signInUser();			
			listener.start();			
			eventHandler.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void signInUser() {
		try {
			while (user == null || user.getId() == -1) {
				if (user != null) System.out.println("Sorry please login again.");				
				System.out.print("Username: ");
				String username = sc.nextLine();
				System.out.print("Password: ");
				String password = sc.nextLine();					
				oos.writeObject(new User(username, password));
				oos.flush();				
				user = (User) ois.readObject();
			}
			System.out.println("Welcome " + user.getName() + "!");
			System.out.println("Please type your message.");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			try {
				ois.close();
				oos.close();
				socket.close();	
				sc.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}			
		}
	}

	class Listener extends Thread {
		@Override
		public void run() {
			try {
				while (running) {
					Message message = (Message) ois.readObject();
					System.out.println(String.format("%s: %s", 
							message.getSender(), message.getText()));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Chatroom is closed.");
			} finally {
				try {
					ois.close();
					oos.close();
					socket.close();	
					sc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class EventHandler extends Thread {
		@Override
		public void run() {
			try {
				while (running) {					
					String input = sc.nextLine();					
					Message message;
					if (input.charAt(0) != '>') { // broadcast
						message = new Message(user.getName(), input);
					} else { // point to point
						int i = input.indexOf(" ");
						String recipient = input.substring(1, i);
						input = input.substring(i + 1, input.length());
						message = new Message(user.getName(), recipient, input);						
					}					
					oos.writeObject(message);
					oos.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					ois.close();
					oos.close();						
					socket.close();
					sc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}					
			}			
		}
	}
}
