package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q6A2;

import java.io.Serializable;

public class Message implements Serializable {
	private String sender;
	private String recipient;
	private String text;
	
	public Message(String sender, String text) {
		this.sender = sender;
		this.text = text;
	}
	
	public Message(String sender, String recipient, String text) {
		this.sender = sender;
		this.recipient = recipient;
		this.text = text;
	}	
	
	public String getSender() {
		return sender;
	}
	
	public String getRecipient() {
		return recipient;
	}	

	public String getText() {
		return text;
	}
	
	public boolean isBroadcast() {
		return recipient == null;
	}
}
