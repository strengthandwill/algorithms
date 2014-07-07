package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4A2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Design a parking lot using object-oriented principles.<br>
 * <br>
 * Assumptions:<br>
 * <ul>
 * <li>The parking lot has multiple levels. Each level has multiple rows of spots.</li>
 * <li>The parking lot can park motorcycles, cars, and buses.</li>
 * <li>The parking lot has motorcycle spots, compact spots, and large spots.</li>
 * <li>A motorcycle can park in any spot.</li>
 * <li>A car can park in either a single compact spot or a single large spot.</li>
 * <li>A bus can park in five large spots that are consecutive and within the same row. It
 * cannot park in small spots.</li>
 * </ul>
 * </p>
 *
 */
public class ParkingLot {
	private ArrayList<Lot> unoccupied;
	private HashMap<String, Lot> occupied;
	private HashMap<String, Lot> reserved;
		
	public static void main(String[] args) {
		Car c1 = new Car("c1", new User("Adam"));
		Car c2 = new Car("c2", new User("Bob"));
		Car c3 = new Car("c3", new User("Cal"));
		ParkingLot parkingLot = new ParkingLot(5);
		System.out.println(parkingLot);
		parkingLot.add(c1);
		System.out.println(parkingLot);
		parkingLot.reserve(c2);
		System.out.println(parkingLot);
		parkingLot.add(c3);
		System.out.println(parkingLot);
		parkingLot.arrive(c2.getPlateNum());
		System.out.println(parkingLot);
		parkingLot.remove(c1.getPlateNum());
		System.out.println(parkingLot);
		parkingLot.remove(c3.getPlateNum());
		System.out.println(parkingLot);
		parkingLot.remove(c2.getPlateNum());
		System.out.println(parkingLot);				
	}
	
	public ParkingLot(int number) {
		unoccupied = new ArrayList<Lot>();
		occupied = new HashMap<String, Lot>();
		reserved = new HashMap<String, Lot>();		
		
		for (int i = 0; i < number; i++) {
			unoccupied.add(new Lot("l" + (i + 1)));
		}
	}
	
	public Lot add(Car car) {
		if (isFull()) {
			return null;
		}
		Lot lot = unoccupied.remove(0);
		lot.add(car);
		occupied.put(car.getPlateNum(), lot);
		return lot;
	}
	
	public Lot reserve(Car car) {
		if (isFull()) {
			return null;
		}
		Lot lot = unoccupied.remove(0);
		lot.add(car);
		reserved.put(car.getPlateNum(), lot);
		return lot;
	}	
		
	public boolean remove(String plateNum) {	
		HashMap<String, Lot> map = null;
		if (occupied.containsKey(plateNum)) {
			map = occupied;
		} else if (reserved.containsKey(plateNum)) {
			map = reserved;
		}
		if (map == null) {
			return false;
		}		
		Lot lot = map.remove(plateNum);
		lot.remove();
		unoccupied.add(lot);
		return true;		
	}
	
	public boolean arrive(String plateNum) {
		if (!reserved.containsKey(plateNum)) {
			return false;
		}
		Lot lot = reserved.remove(plateNum);
		lot.arrive();
		occupied.put(plateNum, lot);
		return true;
	}
	
	public Iterable<Lot> getUnoccupiedLots() {
		return unoccupied;
	}
	
	public Iterable<Lot> getOccupiedLots() {
		return occupied.values();
	}
	
	public Iterable<Lot> getReservedLots() {
		return reserved.values();
	}
	
	public int getUnccupiedLotsNum() {
		return unoccupied.size();
	}
	
	public int getOccupiedLotsNum() {
		return occupied.size();
	}
	
	public int getReservedLotsNum() {
		return reserved.size();
	}
	
	public int getTotalLotsNum() {
		return unoccupied.size() + occupied.size() + reserved.size();
	}
	
	public boolean isEmpty() {
		return occupied.isEmpty() && reserved.isEmpty();
	}
	
	public boolean isFull() {
		return unoccupied.isEmpty();
	}
	
	@Override
	public String toString() {
		return "{unoccupied:" + unoccupied + "}{occupied:" + 
				occupied + "}{reserved:" + reserved + "}"; 
	}

}
