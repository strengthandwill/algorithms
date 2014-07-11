package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q8;

public class File extends Entry {
	private int size;
	private String content;
	
	public File(String name, Directory parent, int size) {
		super(name, parent);
		this.size = size;
	}
	
	public File(String name, Directory parent, String content) {
		super(name, parent);
		this.content = content;
	}
		
	public String getContent() {
		setAccess();
		return content;
	}
	
	public void setContent(String content) {
		setModified();
		this.content = content;
	}

	@Override
	public int size() {
		return size;
	}
}
