package jpl.chapter2.exercise13;

import org.junit.*;
import static org.junit.Assert.*;

public class VehicleTest {

    @Before
    public void setup() {
	Vehicle.resetId();
    }

    @SuppressWarnings("unused")
    @Test
    public void testGetMaximumId() {
	Vehicle vehicle1 = new Vehicle();
	Vehicle vehicle2 = new Vehicle();
	Vehicle vehicle3 = new Vehicle();

	assertEquals(2, Vehicle.getMaximumId());
    }

    @Test(expected = IllegalStateException.class)
    public void testGetMaximumIdForFirstTime() {
	Vehicle.getMaximumId();
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

}
