package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q6A1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
	private Thread listener;
	private Thread 	eventHandler;
	private boolean running = true;
	private Socket socket;
	private String name;

	public static void main(String[] args) {
		new ChatClient("127.0.0.1", 1234);		
	}

	public ChatClient(String serverIP, int serverPorts) {		
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		name = sc.nextLine();
		
		try {
			socket = new Socket(serverIP, serverPorts);
			System.out.println("Connected to " + serverIP + ":" + serverPorts + ".");
			listener = new Listener(socket.getInputStream());
			eventHandler = new EventHandler(socket.getOutputStream(), sc);
			eventHandler.start();
			listener.start();			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Listener extends Thread {
		private BufferedInputStream bis;
		private DataInputStream dis;

		public Listener(InputStream in) {
			bis = new BufferedInputStream(in);
			dis = new DataInputStream(bis);			
		}

		@Override
		public void run() {
			try {
				while (running) {
					System.out.println(dis.readUTF());
				}	
			} catch (IOException e) {
				System.out.println("Chatroom is closed.");
			} finally {
				try {
					dis.close();
					bis.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class EventHandler extends Thread {
		private Scanner sc;
		private BufferedOutputStream bos;
		private DataOutputStream dos;

		public EventHandler(OutputStream out, Scanner sc) {
			bos = new BufferedOutputStream(out);
			dos = new DataOutputStream(bos);
			this.sc = sc;		
		}

		@Override
		public void run() {
			try {
				dos.writeUTF(name); // write name
				dos.flush();
				while (running) {
					String message = sc.nextLine();
					dos.writeUTF(message);
					dos.flush();					
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					sc.close();
					dos.close();				
					bos.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
