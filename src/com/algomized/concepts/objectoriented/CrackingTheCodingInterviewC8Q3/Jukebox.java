package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q3;

import java.util.ArrayList;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Design a musical jukebox using object-oriented principles.
 * </p>
 *
 */
public class Jukebox {
	private ArrayList<Album> albums;
	private Playlist playlist;
	private Song playingSong;
	private int playedTime;
	private int coins = 0;
		
	public static void main(String[] args) {
		// init albums
		Album a1 = new Album("a1");
		a1.addSong(new Song("s1", a1.getName(), 5));
		a1.addSong(new Song("s2", a1.getName(), 3));
		a1.addSong(new Song("s3", a1.getName(), 4));
		Album a2 = new Album("a2");
		a2.addSong(new Song("sa", a2.getName(), 5));
		a2.addSong(new Song("sb", a2.getName(), 3));
		Jukebox jukebox = new Jukebox();
		jukebox.addAlbum(a1);
		jukebox.addAlbum(a2);
		
		// print albums and songs
		for (Album a : jukebox.getAlbums()) {
			for (Song s : jukebox.getSongs(a.getName())) {
				System.out.println(s);
			}
		}		
		
		// add and play songs
		jukebox.insertCoin();
		jukebox.insertCoin();
		jukebox.addSong("a1", "s1");
		jukebox.addSong("a2", "sb");
		jukebox.playSong();
		while (jukebox.isPlaying()) {
			System.out.println(jukebox.getPlayingSong().toString() + "->" + 
					jukebox.getPlayingSongPlayedTime() + "/" + 
					jukebox.getPlayingSongLength());
			jukebox.playSong();
		}
	}
		
	public Jukebox() {
		albums = new ArrayList<Album>();
		playlist = new Playlist();
	}
	
	public void addAlbum(Album album) {
		if (album == null) {
			return;
		}
		albums.add(album);
	}
	
	public Iterable<Album> getAlbums() {
		return albums;
	}
	
	public Iterable<Song> getSongs(String albumName) {
		if (albumName == null) {
			return null;
		}
		for (Album a : albums) {
			if (a.getName().equals(albumName)) {
				return a.getSongs();
			}
		}
		return null;
	}
	
	public void addSong(String albumName, String songName) {
		if (coins == 0) { // does not allow to add songs if coins is used up
			return;
		}		
		for (Song song : getSongs(albumName)) { // find song from album, add to playlist and decrement the coins if found
			if (song.getName().equals(songName)) {
				playlist.addSong(song);
				coins--;
				return;
			}
		}
	}
	
	public Song getPlayingSong() {
		return playingSong;
	}
	
	public void playSong() {
		if (playingSong != null) {
			playedTime++;			
			if (playedTime > playingSong.getLength()) { // playing song finished
				playingSong = null;
			}
		}
		if (playingSong == null && !playlist.isEmpty()) { // add song from playlist if playlist has songs
			playingSong = playlist.playSong();
			playedTime = 0;
		}		
	}	
	
	public int getPlayingSongLength() {
		if (!isPlaying()) {
			return -1;
		}		
		return playingSong.getLength();
	}
	
	public int getPlayingSongPlayedTime() {
		if (!isPlaying()) {
			return -1;
		}		
		return playedTime;
	}
	
	public boolean isPlaying() {
		return playingSong != null;
	}
	
	public Song getNextSong() {
		return playlist.nextSong();
	} 	
	
	public void insertCoin() {
		coins++;
	}
	
	public int getCoins() {
		return coins;
	}
}