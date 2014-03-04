package jpl.chapter2.exercise6;

import org.junit.*;
import static org.junit.Assert.*;

public class VehicleTest {

	@Test
	public void testVehicleId() {
		Vehicle vehicle1 = new Vehicle();
		Vehicle vehicle2 = new Vehicle();
		Vehicle vehicle3 = new Vehicle();
		assertEquals(0, vehicle1.getId());
		assertEquals(1, vehicle2.getId());
		assertEquals(2, vehicle3.getId());
	}

}
