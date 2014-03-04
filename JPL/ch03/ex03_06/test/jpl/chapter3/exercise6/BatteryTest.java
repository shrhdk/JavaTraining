package jpl.chapter3.exercise6;

import org.junit.*;
import static org.junit.Assert.*;

public class BatteryTest {

	private Battery battery;

	@Before
	public void setup() {
		battery = new Battery();
	}

	@Test
	public void testInitialState() {
		assertEquals(0, battery.checkWs());
	}

	@Test
	public void testCharge() {
		battery.charge();
		assertEquals(Battery.maxWs, battery.checkWs());
	}
	
	@Test
	public void testGetEnergy() {
		battery.charge();
		assertEquals(Battery.unitWs, battery.getEnergy());
		assertEquals(Battery.maxWs - Battery.unitWs, battery.checkWs());
	}
	@Test(expected = EnergySource.NoEnergyException.class)
	public void testGetEnergyForVolumeIs0() {
		battery.getEnergy();
	}

	@Test
	public void testEmptyForVolumeIs0() {
		assertEquals(true, battery.empty());
	}

	@Test
	public void testEmptyForVolumeIsNot0() {
		battery.charge();
		assertEquals(false, battery.empty());
	}

}
