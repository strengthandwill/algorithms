package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q6A1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
public class ChatServer {
	private ArrayList<ChatHandler> handlers;
	private boolean running = true;

	public static void main(String[] args) {
		new ChatServer(1234);		
	}

	public ChatServer(int ports) {
		handlers = new ArrayList<ChatHandler>();
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

	class ChatHandler extends Thread {
		private Socket socket;
		private BufferedInputStream bis;
		private DataInputStream dis;
		private BufferedOutputStream bos;
		private DataOutputStream dos;
		private String user;

		public ChatHandler(Socket socket) {
			try {
				this.socket = socket;
				bis = new BufferedInputStream(socket.getInputStream());
				dis = new DataInputStream(bis);
				bos = new BufferedOutputStream(socket.getOutputStream());
				dos = new DataOutputStream(bos);				
			} catch (IOException e) {
				e.printStackTrace();
			}
			start();
		}
		
		public void sendMessage(String sender, String message) {
			try {
				dos.writeUTF(String.format("%s: %s", sender, message));
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				user = dis.readUTF();
				System.out.println(user + " joined the chat.");				
				while (running) {
					String message = dis.readUTF();
					if (!pointToPoint(message)) {
						broadcast(message);
					}
				}
			} catch (IOException e) {
				System.out.println(user + " left the chat.");
			} finally {
				try {
					dis.close();
					dos.close();
					bis.close();
					bos.close();
					socket.close();
					handlers.remove(this);					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void broadcast(String message) {
			for (ChatHandler h : handlers) {
				if (!h.equals(this)) {
					h.sendMessage(user, message);
				}
			}
		}
		
		private boolean pointToPoint(String message) {
			if (!message.contains(">")) {
				return false;
			}
			
			int start = message.indexOf(" ");						
			String recipient = message.substring(1, start);
			message = message.substring(start + 1, message.length());			
			
			for (ChatHandler h : handlers) {
				if (!h.equals(this) && h.getUser().equals(recipient)) {
					h.sendMessage(user, message);
				}
			}
			return true;
		}
		
		public String getUser() {
			return user;
		}
	}
}
