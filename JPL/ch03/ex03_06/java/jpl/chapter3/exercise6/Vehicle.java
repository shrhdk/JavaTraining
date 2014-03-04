package jpl.chapter3.exercise6;

public class Vehicle {

	private static int nextId = 0;
	private final int id;
	private String owner;
	private final EnergySource energy;
	private double velocity;
	private double direction;

	public static final double BASE_VELOCITY = 50.0;

	public Vehicle(String owner, EnergySource energy) {
		this.id = Vehicle.nextId++;
		this.setOwner(owner);
		this.energy = energy;
	}

	@Override
	public String toString() {
		return String.format("%d (%s)", this.getId(), this.owner);
	}

	public String getOwner() {
		return this.owner;
	}

	public EnergySource getEnergy() {
		return this.energy;
	}

	public void setOwner(String owner) {
		if (owner == null || owner.equals("")) {
			throw new IllegalArgumentException();
		}
		this.owner = owner;
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

	public void start() {
		if (energy.empty())
			throw new EnergySource.NoEnergyException();
		setVelocity(BASE_VELOCITY);
	}

	public void stop() {
		setVelocity(0);
	}

}
