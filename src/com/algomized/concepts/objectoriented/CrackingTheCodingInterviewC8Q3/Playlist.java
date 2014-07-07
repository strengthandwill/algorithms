package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q3;

import java.util.LinkedList;
import java.util.Queue;

public class Playlist {
	private Queue<Song> songs;
	
	public Playlist() {
		songs = new LinkedList<Song>();
	}
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	public Song playSong() {
		if (songs.isEmpty()) {
			return null;
		}
		return songs.remove();
	}
	
	public Song nextSong() {
		if (songs.isEmpty()) {
			return null;
		}
		return songs.peek();		
	}
	
	public boolean isEmpty() {
		return songs.isEmpty();
	}
}
