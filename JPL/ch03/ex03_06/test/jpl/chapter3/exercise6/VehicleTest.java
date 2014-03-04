package jpl.chapter3.exercise6;

import org.junit.*;
import static org.junit.Assert.*;

public class VehicleTest {

	@Before
	public void setup() {
		Vehicle.resetId();
	}

	@SuppressWarnings("unused")
	@Test
	public void testResetId() {
		Vehicle vehicle1 = new Vehicle("hoge", new Battery());
		Vehicle vehicle2 = new Vehicle("fuga", new Battery());
		Vehicle.resetId();
		Vehicle vehicle3 = new Vehicle("piyo", new Battery());

		assertEquals(0, vehicle3.getId());
	}

	@Test
	public void testGetId() {
		Vehicle vehicle1 = new Vehicle("hoge", new Battery());
		Vehicle vehicle2 = new Vehicle("fuga", new Battery());

		assertEquals(0, vehicle1.getId());
		assertEquals(1, vehicle2.getId());
	}

	@Test
	public void testGetOwner() {
		Vehicle vehicle = new Vehicle("hoge", new Battery());
		vehicle.setOwner("hoge");

		assertEquals("hoge", vehicle.getOwner());
	}

	@Test
	public void testSetOwner() {
		Vehicle vehicle = new Vehicle("hoge", new Battery());
		vehicle.setOwner("hoge");

		assertEquals("hoge", vehicle.getOwner());
	}

	@Test
	public void testGetVelocity() {
		Vehicle vehicle = new Vehicle("hoge", new Battery());
		vehicle.setVelocity(100);

		assertEquals(100, vehicle.getVelocity(), 0);
	}

	@Test
	public void testSetVelocity() {
		Vehicle vehicle = new Vehicle("hoge", new Battery());
		vehicle.setVelocity(100);

		assertEquals(100, vehicle.getVelocity(), 0);
	}

	@Test
	public void testGetDirection() {
		Vehicle vehicle = new Vehicle("hoge", new Battery());
		vehicle.setDirection(100);

		assertEquals(100, vehicle.getDirection(), 0);
	}

	@Test
	public void testSetDirection() {
		Vehicle vehicle = new Vehicle("hoge", new Battery());
		vehicle.setDirection(100);

		assertEquals(100, vehicle.getDirection(), 0);
	}
	
	@Test
	public void testStart() {
		Vehicle vehicle = new Vehicle("hoge", new Battery());
		vehicle.getEnergy().charge();
		vehicle.start();
		assertEquals(Vehicle.BASE_VELOCITY, vehicle.getVelocity(), 0);
	}
	
	@Test(expected = EnergySource.NoEnergyException.class)
	public void testStartForEnergyIsEmpty() {
		Vehicle vehicle = new Vehicle("hoge", new Battery());
		vehicle.start();
	}

	@Test
	public void testStop() {
		Vehicle vehicle = new Vehicle("hoge", new Battery());
		vehicle.getEnergy().charge();
		vehicle.start();
		vehicle.stop();

		assertEquals(0, vehicle.getVelocity(), 0);
	}

	@Test
	public void testToString() {
		Vehicle vehicle1 = new Vehicle("hoge", new Battery());
		Vehicle vehicle2 = new Vehicle("fuga", new Battery());

		assertEquals("0 (hoge)", vehicle1.toString());
		assertEquals("1 (fuga)", vehicle2.toString());
	}

	@Test
	public void testVehicle() {
		Battery battery1 = new Battery();
		Battery battery2 = new Battery();
		GasTank gasTank1 = new GasTank();
		Vehicle vehicle1 = new Vehicle("hoge", battery1);
		Vehicle vehicle2 = new Vehicle("fuga", battery2);
		Vehicle vehicle3 = new Vehicle("piyo", gasTank1);

		assertEquals(0, vehicle1.getId());
		assertEquals("hoge", vehicle1.getOwner());
		assertEquals(battery1, vehicle1.getEnergy());
		
		assertEquals(1, vehicle2.getId());
		assertEquals("fuga", vehicle2.getOwner());
		assertEquals(battery2, vehicle2.getEnergy());
		
		assertEquals(2, vehicle3.getId());
		assertEquals("piyo", vehicle3.getOwner());
		assertEquals(gasTank1, vehicle3.getEnergy());
	}

}