package jpl.chapter3.exercise6;

public abstract class EnergySource {

	public abstract void charge();
	public abstract int getEnergy() throws NoEnergyException;
	public abstract boolean empty();

	public static class NoEnergyException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

}
