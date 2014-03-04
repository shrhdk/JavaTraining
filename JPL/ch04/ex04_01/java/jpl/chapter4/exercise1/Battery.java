package jpl.chapter4.exercise1;

public class Battery implements EnergySource {

	private int ws = 0;
	public static final int maxWs = 100;
	public static final int unitWs = 1;

	@Override
	public void charge() {
		this.ws = maxWs;
	}

	@Override
	public int getEnergy() throws NoEnergyException {
		if (ws <= 0) {
			throw new NoEnergyException();
		} if (ws - unitWs < 0) {
			int temp = ws;
			ws = 0;
			return temp;
		} else {
			ws -= unitWs;
			return unitWs;
		}
	}

	@Override
	public boolean empty() {
		return ws <= 0;
	}

	public int checkWs() {
		return ws;
	}
	
}
