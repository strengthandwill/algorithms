package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4A1;

public abstract class Vehicle {
	public static enum Type {MOTOCYCLE, CAR, BUS};
	private String plateNumber;
	
	public Vehicle(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public String getPlateNumber() {
		return plateNumber;
	}
	
	public abstract Type getType();	
	
	@Override
	public String toString() {
		return plateNumber;
	}
}

class Motorcycle extends Vehicle {
	public Motorcycle(String plateNumber) {
		super(plateNumber);
	}

	@Override
	public Type getType() {
		return Type.MOTOCYCLE;
	}	
}

class Car extends Vehicle {
	public Car(String plateNumber) {
		super(plateNumber);
	}

	@Override
	public Type getType() {
		return Type.CAR;
	}	
}

class Bus extends Vehicle {
	public Bus(String plateNumber) {
		super(plateNumber);
	}

	@Override
	public Type getType() {
		return Type.BUS;
	}	
}