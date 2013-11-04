package jpl.chapter2.exercise18;

public class Vehicle {

	private static int nextId = 0;
	private final int id;
	private double velocity;
	private double direction;
	private String owner;

	public static void main(String[] args) {
		Vehicle[] vehicle;
		if (1 <= args.length) {
			vehicle = new Vehicle[args.length];
			for (int i = 0; i < args.length; i++) {
				vehicle[i] = new Vehicle(args[i]);
				System.out.println(vehicle[i]);
			}
		}
	}

	public enum Direction {
		TURN_LEFT, TURN_RIGHT
	}

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

	public double getVelocity() {
		return this.velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public void changeSpeed(double speed) {
		setVelocity(speed);
	}

	public void stop() {
		setVelocity(0);
	}

	public double getDirection() {
		return this.direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public void turn(double direction) {
		setDirection(direction);
	}

	public void turn(Direction direction) {
		switch (direction) {
		case TURN_LEFT:
			turn(getDirection() + Math.PI / 2);
			break;
		case TURN_RIGHT:
			turn(getDirection() - Math.PI / 2);
			break;
		default:
			throw new IllegalArgumentException();
		}
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

	public Vehicle() {
		this.id = Vehicle.nextId++;
	}

	public Vehicle(String owner) {
		this();
		this.setOwner(owner);
	}

}
