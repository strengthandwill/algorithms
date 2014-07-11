package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q8;

import java.util.Scanner;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Explain the data structures and algorithms that you would use to design an
 * in-memory file system. Illustrate with an example in code where possible.
 * </p>
 *
 */
public class FileSystem {
	public static FileSystem instance = null;
	
	private final Directory root;
	private Directory current;
	
	public static void main(String[] args) {
		FileSystem fs = FileSystem.getInstance();		
		fs.run();
	}	
	
	private FileSystem() {
		root = new Directory("home", null);
		current = root;
	}
	
	public static FileSystem getInstance() {
		if (instance == null) {
			instance = new FileSystem();
		}
		return instance;
	}
	
	public void run() {
		Scanner sc = new Scanner(System.in);
		String input = null;
		while (input == null || !input.equals("exit")) {
			System.out.print(current.getPath() + "> ");
			input = sc.nextLine();
			String[] tokens = input.split(" ");
			if (tokens[0].equals("dir")) {
				System.out.println(getDirectory());
			} else if (tokens[0].equals("cd")) { 
				if (!changeDirectory(tokens[1])) {
					System.out.println("The system cannot find the path specified.");
				}
			} else if (tokens[0].equals("cd..")) {
				changeDirectory();
			} else if (tokens[0].equals("mkdir")) {
				if (!makeDirectory(tokens[1])) {
					System.out.println("A subdirectory " + tokens[1] + " already exists.");
				}
			} else if (tokens[0].equals("rmdir")) {
				if (!removeDirectory(tokens[1])) {
					System.out.println("The system cannot find the directory specified.");
				}
			} else if (tokens[0].equals("mkfile")) {
				if (!makeFile(tokens[1], Integer.parseInt(tokens[2]))) {
					System.out.println("A file " + tokens[1] + " already exists.");
				}
			} else if (tokens[0].equals("rmfile")) {
				if (!removeFile(tokens[1])) {
					System.out.println("The system cannot find the file specified.");
				}
			} else if (!input.equals("exit")){
				System.out.println("'" + input + "' is not recognized as a command.");
			}
		}
		sc.close();
	}
		
	public boolean changeDirectory(String name) {
		Directory child = current.getDirectory(name);
		if (child == null) {
			return false;
		}
		current = child;
		return true;
	}
	
	public void changeDirectory() {
		Directory parent = current.getParent();
		if (parent != null) {
			current = parent;
		}
	}
	
	public String getDirectory() {
		int i = 0;
		int j = 0;
		StringBuffer strBuf = new StringBuffer();
		for (Directory d : current.getDirectories()) {
			strBuf.append(d.getCreated() + "\t" + d.getModified() + "\t" + 
					d.getAccess() + "\t[DIR]\t" + d.getName() + "\n");
			i++;
		}
		for (File f : current.getFiles()) {
			strBuf.append(f.getCreated() + "\t" + f.getModified() + "\t" + 
					f.getAccess() + "\t[FILE]\t" + f.getName() + "\n");
			j++;
		}
		strBuf.append(i + " Dir(s)\t" + j + " File(s)\t" + current.size() + " bytes");
		return strBuf.toString();
	}
	
	public boolean makeDirectory(String name) {
		return current.addDirectory(new Directory(name, current));
	}
	
	public boolean removeDirectory(String name) {
		return current.removeDirectory(name) != null;
	}
	
	public boolean makeFile(String name, int size) {
		return current.addFile(new File(name, current, size));
	}	
	
	public boolean removeFile(String name) {
		return current.removeFile(name) != null;
	}
}