package jpl.chapter3.exercise9;

public class Garage implements Cloneable {

	private Vehicle[] vehicles;

	public Garage(int size) {
		vehicles = new Vehicle[size];
	}

	private Garage(Garage garage) {
		vehicles = new Vehicle[garage.vehicles.length];
		for (int i = 0; i < garage.vehicles.length; i++) {
			if (garage.vehicles[i] != null)
				vehicles[i] = garage.vehicles[i].clone();
			else
				vehicles[i] = null;
		}
	}

	public Vehicle[] getVehicles() {
		return vehicles;
	}

	@Override
	public Garage clone() {
		return new Garage(this);
	}
}
