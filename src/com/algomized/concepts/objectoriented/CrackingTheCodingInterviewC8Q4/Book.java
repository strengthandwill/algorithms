package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4;

public class Book {
	private final long id;
	private String title;
	private int pages;
	
	public Book(long id, String title, int pages) {
		this.id = id;
		this.title = title;
		this.pages = pages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("[%d:%s:%d]", id, title, pages);
	}
}
