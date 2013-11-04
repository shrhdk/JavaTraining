package jpl.chapter3.exercise1;

public class PassengerVehicle extends Vehicle {

	private final int capacity;
	private int numberOfPassengers = 0;

	public PassengerVehicle(String owner, final int capacity) {
		super(owner);
		if (capacity < 1)
			throw new IllegalArgumentException("capacity must be 1 and over.");
		this.capacity = capacity;
	}

	public PassengerVehicle(final int capacity) {
		this(null, capacity);
	}

	public int getCapacity() {
		return capacity;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		if (numberOfPassengers < 0 || getCapacity() < numberOfPassengers)
			throw new IllegalArgumentException(
					"numberOfPassengers must be 0 and over, and capacity and less. You can know the capacity as the return of getCapacity method.");
		this.numberOfPassengers = numberOfPassengers;
	}

	@Override
	public String toString() {
		return String.format("%d %s (%d/%d)", getId(), getOwner(),
				getNumberOfPassengers(), getCapacity());
	}

	public static void main(String[] args) {
		PassengerVehicle passengerVehicle1 = new PassengerVehicle("watagashi",
				1);
		PassengerVehicle passengerVehicle2 = new PassengerVehicle("fujii", 2);
		PassengerVehicle passengerVehicle3 = new PassengerVehicle("kanematsu",
				2);

		System.out.println(passengerVehicle1);
		System.out.println(passengerVehicle2);
		System.out.println(passengerVehicle3);
	}

}
