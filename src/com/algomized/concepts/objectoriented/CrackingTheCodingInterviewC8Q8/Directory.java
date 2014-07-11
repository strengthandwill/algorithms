package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q8;

import java.util.HashMap;
import java.util.Map;

public class Directory extends Entry {
	private Map<String, Directory> directories;
	private Map<String, File> files;
	
	public Directory(String name, Directory parent) {
		super(name, parent);
		directories = new HashMap<String, Directory>();
		files = new HashMap<String, File>();
	}
		
	public Iterable<Directory> getDirectories() {
		setAccess();
		return directories.values();
	}
	
	public boolean addDirectory(Directory directory) {
		setModified();
		if (directory == null || directories.containsKey(directory.getName())) {
			return false;
		}
		directories.put(directory.getName(), directory);
		return true;
	}
	
	public Directory getDirectory(String name) {
		setAccess();
		if (name == null || !directories.containsKey(name)) {
			return null;
		}
		return directories.get(name);
	}	
	
	public Directory removeDirectory(String name) {
		setModified();
		if (name == null || !directories.containsKey(name)) {
			return null;
		}
		return directories.remove(name);
	}
	
	public Iterable<File> getFiles() {
		setAccess();
		return files.values();
	}
	
	public boolean addFile(File file) {
		setModified();
		if (file == null || files.containsKey(file.getName())) {
			return false;
		}
		files.put(file.getName(), file);
		return true;
	}
	
	public File getFile(String name) {
		setAccess();
		if (name == null || !files.containsKey(name)) {
			return null;
		}
		return files.get(name);
	}	
	
	public File removeFile(String name) {
		setModified();
		if (name == null || !files.containsKey(name)) {
			return null;
		}
		return files.remove(name);
	}

	@Override
	public int size() {
		int size = 0;
		for (Directory d : directories.values()) {
			size += d.size();
		}
		for (File f : files.values()) {
			size += f.size();
		}
		return size;
	}	
}
