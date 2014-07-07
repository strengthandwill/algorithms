package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4A1;

public abstract class Spot {
	public static enum Type {MOTORCYCLE, COMPACT, LARGE};
	
	private final int level;
	private final int row;
	private final int col;
	private Vehicle vehicle;
	
	public Spot(int level, int row, int col) {
		this.level = level;
		this.row = row;
		this.col = col;
	}
	
	public boolean add(Vehicle vehicle) {
		if (!isAvailable(vehicle)) {
			return false;
		}
		this.vehicle = vehicle;
		return true;
	}
	
	public Vehicle remove() {
		Vehicle removed = vehicle;
		vehicle = null;
		return removed;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	// can park and is empty
	public boolean isAvailable(Vehicle vehicle) {
		return canPark(vehicle) && isEmpty();
	}
	
	public abstract boolean canPark(Vehicle vehicle);
	
	public abstract Type getType(); 
	
	public boolean isEmpty() {
		return vehicle == null;
	}
	
	public int getLevel() {
		return level;		
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	@Override
	public String toString() {
		return String.format("[L%dR%dC%d-%s:%s]", level, row, col, 
				getType().toString().substring(0, 1), 
				(vehicle != null ? vehicle.getPlateNumber() : ""));
	}
}

class MotorcycleSpot extends Spot {
	public MotorcycleSpot(int level, int row, int col) {
		super(level, row, col);
	}

	@Override
	public boolean canPark(Vehicle vehicle) {
		return vehicle.getType() == Vehicle.Type.MOTOCYCLE;
	}

	@Override
	public Type getType() {
		return Spot.Type.MOTORCYCLE;
	}	
}

class CompactSpot extends Spot {
	public CompactSpot(int level, int row, int col) {
		super(level, row, col);
	}

	@Override
	public boolean canPark(Vehicle vehicle) {
		return vehicle.getType() == Vehicle.Type.MOTOCYCLE ||
			   vehicle.getType() == Vehicle.Type.CAR;
	}

	@Override
	public Type getType() {
		return Spot.Type.COMPACT;
	}
}

class LargeSpot extends Spot {
	public LargeSpot(int level, int row, int col) {
		super(level, row, col);
	}

	@Override
	public boolean canPark(Vehicle vehicle) {
		return true;
	}

	@Override
	public Type getType() {
		return Spot.Type.LARGE;
	}	
}
