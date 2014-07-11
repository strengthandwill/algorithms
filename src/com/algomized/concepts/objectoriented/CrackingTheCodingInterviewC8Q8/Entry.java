package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q8;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Entry {
	protected String name;
	protected Directory parent;
	protected String path;	
	protected String created;
	protected String modified;
	protected String access;
	
	public Entry(String name, Directory parent) {
		this.name = name;
		this.parent = parent;
		setCreated();
		setPath();
	}
		
	public String getName() {
		setAccess();
		return name;
	}

	public void setName(String name) {
		setModified();
		this.name = name;
	}
	
	public Directory getParent() {
		setAccess();
		return parent;
	}
	
	public void setParent(Directory parent) {
		setModified();
		this.parent = parent;
		setPath();
	}	

	protected String getPath() {
		setAccess();
		return path;
	}
	
	private void setPath() {
		path = (parent != null ? parent.getPath() : "") + "\\" + name;
	}	
	
	public String getCreated() {
		return created;
	}
			
	public String getModified() {
		return modified;
	}
		
	public String getAccess() {		
		return access;
	}
		
	public void setCreated() {
		created = getDate();
		modified = created;
		access = created;
	}

	public void setModified() {
		modified = getDate();
		access = modified;
	}

	public void setAccess() {
		access = getDate();
	}
	
	public abstract int size();
		
	private static String getDate() {
		return (new SimpleDateFormat("dd/MM/YY hh:mm:ss a")).format(new Date());
	}	
}