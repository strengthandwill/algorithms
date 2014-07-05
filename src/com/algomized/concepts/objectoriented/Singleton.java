package com.algomized.concepts.objectoriented;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Ensure that a class has only one instance by using a static object.
 * </p>
 *
 */
public class Singleton {
	private static Singleton instance = null;
	private int number;

	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		s1.setNumber(10);
		System.out.println(s1.getNumber());
		Singleton s2 = Singleton.getInstance();
		s2.setNumber(100);
		System.out.println(s2.getNumber());
		System.out.println(s1.getNumber());
	}
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
}
