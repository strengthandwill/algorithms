package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4A1;

import java.util.ArrayList;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Design a parking lot using object-oriented principles.
 * </p>
 *
 */
public class ParkingLot {
	private final int BUS_SPOTS = 5;
	private Spot[][][] spots;
	private int size = 0;
	private final int maxSize;
	
	public static void main(String[] args) {
		Spot[][][] spots = new Spot[3][2][5];
		for (int l = 0; l < spots.length; l++) {
			for (int r = 0; r < spots[0].length; r++) {
				for (int c = 0; c < spots[0][0].length; c++) {
					switch (l) {
					case 0: spots[l][r][c] = new MotorcycleSpot((l+1), (r+1), (c+1)); break;
					case 1: spots[l][r][c] = new CompactSpot((l+1), (r+1), (c+1)); break;
					case 2: spots[l][r][c] = new LargeSpot((l+1), (r+1), (c+1)); break;
					}
				}
			}
		}
		ParkingLot parkingLot = new ParkingLot(spots);
		System.out.println(parkingLot);
		printSpots(parkingLot.getAvailableSpots(new Motorcycle("M1")));
		printSpots(parkingLot.getAvailableSpots(new Car("C1")));
		printSpots(parkingLot.getAvailableSpots(new Bus("B1")));	
		System.out.println();
		
		System.out.println(parkingLot.addVehicle(1, 1, 1, new Motorcycle("M1")));
		System.out.println(parkingLot);
		System.out.println(parkingLot.addVehicle(2, 1, 1, new Car("C1")));
		System.out.println(parkingLot);		
		System.out.println(parkingLot.addVehicle(3, 1, 1, new Bus("B1")));
		System.out.println(parkingLot);	
		
		System.out.println(parkingLot.removeVehicle(1, 1, 1));
		System.out.println(parkingLot);
		System.out.println(parkingLot.removeVehicle(2, 1, 1));
		System.out.println(parkingLot);		
		System.out.println(parkingLot.removeVehicle(3, 1, 1));
		System.out.println(parkingLot);			
	}	
	
	private static void printSpots(Iterable<Spot> spots) {
		for (Spot s : spots) {
			System.out.print(s);
		}
		System.out.println();
	}
	
	public ParkingLot(Spot[][][] spots) {
		this.spots = spots;
		this.maxSize = spots.length * spots[0].length * spots[0][0].length;
	}
	
	public Iterable<Spot> getAvailableSpots(Vehicle vehicle) {
		ArrayList<Spot> available = new ArrayList<Spot>();
		for (int l = 0; l < spots.length; l++) {
			for (int r = 0; r < spots[0].length; r++) {
				for (int c = 0; c < spots[0][0].length; c++) {
					if (isAvailable(spots[l][r], c, vehicle)) {
						available.add(spots[l][r][c]);
					}
				}
			}
		}
		return available;
	}
	
	public Iterable<Spot> getOccupiedSpots() {
		ArrayList<Spot> occupied = new ArrayList<Spot>();
		for (int l = 0; l < spots.length; l++) {
			for (int r = 0; r < spots[0].length; r++) {
				for (int c = 0; c < spots[0][0].length; c++) {
					if (!spots[l][r][c].isEmpty()) {
						occupied.add(spots[l][r][c]);
					}
				}
			}
		}
		return occupied;
	}	
	
	public Iterable<Spot> getEmptySpots() {
		ArrayList<Spot> empty = new ArrayList<Spot>();
		for (int l = 0; l < spots.length; l++) {
			for (int r = 0; r < spots[0].length; r++) {
				for (int c = 0; c < spots[0][0].length; c++) {
					if (spots[l][r][c].isEmpty()) {
						empty.add(spots[l][r][c]);
					}
				}
			}
		}
		return empty;
	}		
	
	private boolean isAvailable(Spot[] spots, int c, Vehicle vehicle) {
		if ((vehicle.getType() == Vehicle.Type.BUS)) { // is bus
			if ((c + BUS_SPOTS) > spots.length) {
				return false;
			}
			for (int i = c; i < (c + BUS_SPOTS); i++) {
				if (!spots[c].isAvailable(vehicle)) {
					return false;
				}
			}
			return true;
		}  else {// is motorcycle or car
			return spots[c].isAvailable(vehicle);
		}		
	}
	
	public boolean addVehicle(int level, int row, int col, Vehicle vehicle) {
		if (vehicle == null || !checkRange(--level, --row, --col)) {
			return false;
		}
		return addVehicle(spots[level][row], col, vehicle);
	}
	
	private boolean addVehicle(Spot[] spots, int c, Vehicle vehicle) {
		if (!isAvailable(spots, c, vehicle)) {
			return false;
		}		
		if ((vehicle.getType() == Vehicle.Type.BUS)) { // is bus
			for (int i = c; i < (c + BUS_SPOTS); i++) {
				spots[i].add(vehicle);
			}			
		} else { // is motorcycle or car
			spots[c].add(vehicle);
		}
		size++;
		return true;
	}
	
	public Vehicle removeVehicle(int level, int row, int col) {
		if (!checkRange(--level, --row, --col)) {
			return null;
		}
		return removeVehicle(spots[level][row], col);
	}
	
	private Vehicle removeVehicle(Spot[] spots, int c) {
		Vehicle removed = spots[c].getVehicle();
		if (removed == null) {
			return null;
		}
		if (removed.getType() == Vehicle.Type.BUS) {
			if ((c + BUS_SPOTS) > spots.length) {
				return null;
			}
			for (int i = c + 1; i < (c + BUS_SPOTS); i++) {
				if (!spots[i].getVehicle().equals(removed)) { // not one bus
					return null;
				}
			}
			for (int i = c + 1; i < (c + BUS_SPOTS); i++) {
				spots[i].remove();
			}			
		} else {
			spots[c].remove();
		}
		size--;
		return removed;
	}
		
	private boolean checkRange(int level, int row, int col) {
		return level < spots.length && row < spots[0].length && col < spots[0][0].length;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}	
	
	public boolean isFull() {
		return size == maxSize;
	}
	
	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		for (int l = 0; l < spots.length; l++) {
			for (int r = 0; r < spots[0].length; r++) {
				for (int c = 0; c < spots[0][0].length; c++) {
					strBuf.append(spots[l][r][c]);
				}
			}
			strBuf.append("\n");
		}
		return strBuf.toString();
	}
}
