package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q3;

import java.util.ArrayList;

public class Album {
	private String name;
	private ArrayList<Song> songs;
	
	public Album(String name) {
		this.name = name;
		this.songs = new ArrayList<Song>();
	}
	
	public void addSong(Song song) {
		if (song == null) {
			return;
		}
		songs.add(song);
	}
	
	public String getName() {
		return name;
	}
	
	public Iterable<Song> getSongs() {
		return songs;
	}
}
