package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4A2;

public class Lot {
	private String location;
	private Car car;
	private boolean reserved;
	
	public Lot(String location) {
		this.location = location;
	}
	
	public boolean add(Car car) {
		if (!isEmpty()) {
			return false;
		}
		this.car = car;
		this.reserved = false;
		return true;
	}
	
	public boolean reserved(Car car) {
		if (!isEmpty()) {
			return false;
		}
		this.car = car;
		this.reserved = true;
		return true;
	}
	
	public boolean arrive() {
		if (isEmpty()) {
			return false;
		}
		reserved = false;
		return true;
	}
	
	public void remove() {
		car = null;
	}
	
	public boolean isEmpty() {
		return car == null;
	}
	
	public boolean isReserved() {
		return reserved;
	}
	
	public String getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		if (isEmpty()) {
			return "[" + location + "]";
		} else {
			return "[" + location + ":" + car.getPlateNum() + "]";
		}
	}
}