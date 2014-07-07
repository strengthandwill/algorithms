package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4;

public class ReadBook {
	private final User user;
	private final Book book;
	private int bookmarked;

	public ReadBook(User user, Book book) {
		this.user = user;
		this.book = book;
	}

	public int getBookmarked() {
		return bookmarked;
	}

	public void setBookmarked(int bookmarked) {
		this.bookmarked = bookmarked;
	}

	public User getUser() {
		return user;
	}

	public Book getBook() {
		return book;
	}
	
	@Override
	public String toString() {
		return String.format("[%s:%s:%d]", user.getName(), book.getTitle(), bookmarked);
	}
}
