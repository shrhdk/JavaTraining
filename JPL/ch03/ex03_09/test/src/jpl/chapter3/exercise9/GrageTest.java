package jpl.chapter3.exercise9;

import static org.junit.Assert.*;

import org.junit.Test;

public class GrageTest {

	@Test
	public void testGarage() {
		Garage garage = new Garage(3);
		assertEquals(3, garage.getVehicles().length);
		assertEquals(null, garage.getVehicles()[0]);
		assertEquals(null, garage.getVehicles()[1]);
		assertEquals(null, garage.getVehicles()[2]);
	}
	
	@Test
	public void testClone() {
		Vehicle vehicle1 = new Vehicle("hoge");
		Vehicle vehicle2 = new Vehicle("fuga");
		Vehicle vehicle3 = new Vehicle("piyo");
		
		Garage garage = new Garage(4);
		garage.getVehicles()[0] = vehicle1;
		garage.getVehicles()[1] = vehicle2;
		garage.getVehicles()[2] = vehicle3;
		garage.getVehicles()[3] = null;
		
		Garage cloneGarage = garage.clone();
		
		assertNotEquals(vehicle1, cloneGarage.getVehicles()[0]);
		assertNotEquals(vehicle2, cloneGarage.getVehicles()[1]);
		assertNotEquals(vehicle3, cloneGarage.getVehicles()[2]);
		assertEquals(null, cloneGarage.getVehicles()[3]);
		
		cloneGarage.getVehicles()[2] = null;
		cloneGarage.getVehicles()[1].setOwner("fugafuga");
		
		assertEquals(vehicle1, garage.getVehicles()[0]);
		assertEquals(vehicle2, garage.getVehicles()[1]);
		assertEquals(vehicle3, garage.getVehicles()[2]);
		assertEquals(null, garage.getVehicles()[3]);
		
		assertEquals("hoge", cloneGarage.getVehicles()[0].getOwner());
		assertEquals("fugafuga", cloneGarage.getVehicles()[1].getOwner());
		assertEquals(null, cloneGarage.getVehicles()[2]);
		assertEquals(null, cloneGarage.getVehicles()[3]);
	}
}
