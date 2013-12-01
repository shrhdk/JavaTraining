package jpl.chapter4.exercise1;


public interface EnergySource {

	void charge();
	int getEnergy() throws NoEnergyException;
	boolean empty();

	class NoEnergyException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

}
