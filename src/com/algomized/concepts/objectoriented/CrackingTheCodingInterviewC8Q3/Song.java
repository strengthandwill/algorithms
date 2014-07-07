package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q3;

public class Song {
	private String name;
	private String album;
	private int length;
	
	public Song(String name, String album, int length) {
		this.name = name;
		this.album = album;
		this.length = length;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public int getLength() {
		return length;
	}
	
	public String toString() {
		return "[" + album + ":" + name + "]";
	}
}
