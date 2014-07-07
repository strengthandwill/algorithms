package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4;

import java.util.HashMap;

public class Library {
	private HashMap<Long, Book> books;
	
	public Library() {
		books = new HashMap<Long, Book>();
	}
	
	public boolean addBook(Book book) {
		if (books.containsKey(book.getId())) {
			return false;
		}
		books.put(book.getId(), book);
		return true;
	}
	
	public Book getBook(long id) {
		if (!books.containsKey(id)) {
			return null;
		}
		return books.get(id);
	}
	
	public Book removeBook(long id) {
		if (!books.containsKey(id)) {
			return null;
		}
		return books.remove(id);
	}	
	
	public boolean contains(long id) {
		return books.containsKey(id);
	}
	
	public Iterable<Book> getBooks() {
		return books.values();
	}
}
