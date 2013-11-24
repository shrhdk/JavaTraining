package jpl.chapter3.exercise9;

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
		assertEquals("hoge", vehicle1.getOwner());
		assertEquals(1, vehicle2.getId());
		assertEquals("fuga", vehicle2.getOwner());
		assertEquals(2, vehicle3.getId());
		assertEquals("piyo", vehicle3.getOwner());
	}

	@SuppressWarnings("unused")
	@Test
	public void testGetNextId() {
		Vehicle vehicle1 = new Vehicle();
		Vehicle vehicle2 = new Vehicle();
		Vehicle vehicle3 = new Vehicle();

		assertEquals(3, Vehicle.getNextId());
	}

	@Test(expected = IllegalStateException.class)
	public void testGetMaximumIdForFirstTime() {
		Vehicle.getNextId();
	}

	@SuppressWarnings("unused")
	@Test
	public void testResetId() {
		Vehicle vehicle1 = new Vehicle();
		Vehicle vehicle2 = new Vehicle();
		Vehicle.resetId();
		Vehicle vehicle3 = new Vehicle();

		assertEquals(0, vehicle3.getId());
	}

	@Test
	public void testGetId() {
		Vehicle vehicle1 = new Vehicle();
		Vehicle vehicle2 = new Vehicle();

		assertEquals(0, vehicle1.getId());
		assertEquals(1, vehicle2.getId());
	}

	@Test
	public void testGetVelocity() {
		Vehicle vehicle = new Vehicle();
		vehicle.setVelocity(100);

		assertEquals(100, vehicle.getVelocity(), 0);
	}

	@Test
	public void testSetVelocity() {
		Vehicle vehicle = new Vehicle();
		vehicle.setVelocity(100);

		assertEquals(100, vehicle.getVelocity(), 0);
	}

	@Test
	public void testGetDirection() {
		Vehicle vehicle = new Vehicle();
		vehicle.setDirection(100);

		assertEquals(100, vehicle.getDirection(), 0);
	}

	@Test
	public void testSetDirection() {
		Vehicle vehicle = new Vehicle();
		vehicle.setDirection(100);

		assertEquals(100, vehicle.getDirection(), 0);
	}

	@Test
	public void testGetOwner() {
		Vehicle vehicle = new Vehicle();
		vehicle.setOwner("hoge");

		assertEquals("hoge", vehicle.getOwner());
	}

	@Test
	public void testSetOwner() {
		Vehicle vehicle = new Vehicle();
		vehicle.setOwner("hoge");

		assertEquals("hoge", vehicle.getOwner());
	}

	@Test
	public void testToString() {
		Vehicle vehicle1 = new Vehicle("hoge");
		Vehicle vehicle2 = new Vehicle("fuga");

		assertEquals("0 (hoge)", vehicle1.toString());
		assertEquals("1 (fuga)", vehicle2.toString());
	}

	@Test
	public void testClone() {
		Vehicle vehicle = new Vehicle();
		vehicle.setOwner("watagashi");
		vehicle.setVelocity(100);
		vehicle.setDirection(3);
		
		Vehicle cloneVehicle = vehicle.clone();
		assertEquals(1, cloneVehicle.getId());
		assertEquals(vehicle.getOwner(), cloneVehicle.getOwner());
		assertEquals(vehicle.getVelocity(), cloneVehicle.getVelocity(), 0);
		assertEquals(vehicle.getDirection(), cloneVehicle.getDirection(), 0);

		assertEquals(2, Vehicle.getNextId());

		cloneVehicle.setOwner("hakase");
		cloneVehicle.setVelocity(150);
		cloneVehicle.setDirection(5);
		
		assertEquals(0, vehicle.getId());
		assertEquals("watagashi", vehicle.getOwner());
		assertEquals(100, vehicle.getVelocity(), 0);
		assertEquals(3, vehicle.getDirection(), 0);
		
		assertEquals(1, cloneVehicle.getId());
		assertEquals("hakase", cloneVehicle.getOwner());
		assertEquals(150, cloneVehicle.getVelocity(), 0);
		assertEquals(5, cloneVehicle.getDirection(), 0);
	}
}