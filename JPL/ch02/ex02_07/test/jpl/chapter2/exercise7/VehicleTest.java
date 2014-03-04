package jpl.chapter2.exercise7;

import org.junit.*;
import static org.junit.Assert.*;

public class VehicleTest {

	@Before
	public void setup() {
		Vehicle.resetId();
	}

	@Test
	public void testVehicle() {
		Vehicle vehicle1 = new Vehicle();
		Vehicle vehicle2 = new Vehicle();
		Vehicle vehicle3 = new Vehicle();

		assertEquals(0, vehicle1.getId());
		assertEquals(1, vehicle2.getId());
		assertEquals(2, vehicle3.getId());
	}

	@Test
	public void testVehicleWithOwner() {
		Vehicle vehicle1 = new Vehicle("hoge");
		Vehicle vehicle2 = new Vehicle("fuga");
		Vehicle vehicle3 = new Vehicle("piyo");

		assertEquals(0, vehicle1.getId());
		assertEquals("hoge", vehicle1.owner);
		assertEquals(1, vehicle2.getId());
		assertEquals("fuga", vehicle2.owner);
		assertEquals(2, vehicle3.getId());
		assertEquals("piyo", vehicle3.owner);
	}

}
