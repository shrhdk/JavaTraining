package jpl.chapter3.exercise6;

import org.junit.*;
import static org.junit.Assert.*;

public class GasTankTest {

	private GasTank gasTank;

	@Before
	public void setup() {
		gasTank = new GasTank();
	}

	@Test
	public void testInitialState() {
		assertEquals(0, gasTank.checkVolume());
	}

	@Test
	public void testCharge() {
		gasTank.charge();
		assertEquals(GasTank.maxVolume, gasTank.checkVolume());
	}
	
	@Test
	public void testGetEnergy() {
		gasTank.charge();
		assertEquals(GasTank.unitVolume, gasTank.getEnergy());
		assertEquals(GasTank.maxVolume - GasTank.unitVolume, gasTank.checkVolume());
	}
	@Test(expected = EnergySource.NoEnergyException.class)
	public void testGetEnergyForVolumeIs0() {
		gasTank.getEnergy();
	}

	@Test
	public void testEmptyForVolumeIs0() {
		assertEquals(true, gasTank.empty());
	}

	@Test
	public void testEmptyForVolumeIsNot0() {
		gasTank.charge();
		assertEquals(false, gasTank.empty());
	}
	
}
