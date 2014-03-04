package jpl.chapter3.exercise6;

public class GasTank extends EnergySource {

	private int volume = 0;
	public static final int maxVolume = 100;
	public static final int unitVolume = 1;

	@Override
	public void charge() {
		this.volume = maxVolume;
	}

	@Override
	public int getEnergy() throws NoEnergyException {
		if (volume <= 0) {
			throw new NoEnergyException();
		} if (volume - unitVolume < 0) {
			int temp = volume;
			volume = 0;
			return temp;
		} else {
			volume -= unitVolume;
			return unitVolume;
		}
	}

	@Override
	public boolean empty() {
		return volume <= 0;
	}

	public int checkVolume() {
		return volume;
	}
	
}
