package jpl.chapter2.exercise10;

public class Vehicle {

	private static int nextId = 0;
	private final int id;
	public double velocity;
	public double direction;
	public String owner;

	public static int getMaximumId() throws IllegalStateException {
		if (Vehicle.nextId == 0) {
			throw new IllegalStateException();
		} else {
			return Vehicle.nextId - 1;
		}
	}

	public static void resetId() {
		Vehicle.nextId = 0;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return String.format("%d (%s)", this.getId(), this.owner);
	}

	public Vehicle() {
		this.id = Vehicle.nextId++;
	}

	public Vehicle(String owner) {
		this();
		this.owner = owner;
	}

}
