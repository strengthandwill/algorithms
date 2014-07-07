package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4;

import java.util.HashMap;

public class User {
	private final long id;
	private String name;
	private HashMap<Long, ReadBook> books;
	
	public User(long id, String name) {
		this.id = id;
		this.name = name;
		this.books = new HashMap<Long, ReadBook>();
	}
	
	public boolean addBook(Book book) {
		if (books.containsKey(book.getId())) {
			return false;
		}
		books.put(book.getId(), new ReadBook(this, book));
		return true;
	}
	
	public ReadBook getBook(long bookId) {
		if (!books.containsKey(bookId)) {
			return null;
		}
		return books.get(bookId);
	}
	
	public boolean updateBook(ReadBook book) {
		if (!books.containsKey(book.getBook().getId())) {
			return false;
		}
		books.get(book.getBook().getId()).setBookmarked(book.getBookmarked());
		return true;
	}	
	
	public ReadBook removeBook(long bookId) {
		if (!books.containsKey(bookId)) {
			return null;
		}		
		return books.remove(bookId);
	}	
	
	public Iterable<ReadBook> getBooks() {
		return books.values();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setBooks(HashMap<Long, ReadBook> books) {
		this.books = books;
	}	
	
	@Override
	public String toString() {
		return String.format("[%d:%s]", id, name);
	}
}
