package jpl.chapter3.exercise9;

public class Vehicle implements Cloneable {

	private static int nextId = 0;
	private final int id;
	private String owner;
	private double velocity;
	private double direction;

	public Vehicle() {
		this.id = Vehicle.nextId++;
	}

	public Vehicle(String owner) {
		this();
		this.setOwner(owner);
	}

	protected Vehicle(Vehicle vehicle) {
		this();
		this.setOwner(vehicle.owner);
		this.velocity = vehicle.getVelocity();
		this.direction = vehicle.getDirection();
	}

	public static int getNextId() throws IllegalStateException {
		if (Vehicle.nextId == 0) {
			throw new IllegalStateException();
		} else {
			return Vehicle.nextId;
		}
	}

	public static void resetId() {
		Vehicle.nextId = 0;
	}

	public int getId() {
		return this.id;
	}

	public double getVelocity() {
		return this.velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getDirection() {
		return this.direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return String.format("%d (%s)", this.getId(), this.owner);
	}
	
	@Override
	public Vehicle clone() {
		return new Vehicle(this);
	}
}
