package jpl.chapter3.exercise1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PassengerVehicleTest {

    @Before
    public void setup() {
	PassengerVehicle.resetId();
    }
    
    @Test
    public void PassengerVehicleWithOwnerAndCapacity() {
	PassengerVehicle passengerVehicle = new PassengerVehicle("watagashi", 10);
	
	assertEquals(0, passengerVehicle.getId());
	assertEquals("watagashi", passengerVehicle.getOwner());
	assertEquals(10, passengerVehicle.getCapacity());
    }
    
    @Test
    public void PassengerVehicleWithOwnerAndCapacityForOwnerIsNull() {
	PassengerVehicle passengerVehicle = new PassengerVehicle(null, 10);
	
	assertEquals(0, passengerVehicle.getId());
	assertEquals(null, passengerVehicle.getOwner());
	assertEquals(10, passengerVehicle.getCapacity());
    }

    @Test(expected=IllegalArgumentException.class)
    public void PassengerVehicleWithOwnerAndCapacityForCapacityIs0() {
	@SuppressWarnings("unused")
	PassengerVehicle passengerVehicle = new PassengerVehicle("wtagashi", 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void PassengerVehicleWithOwnerAndCapacityForCapacityIsNegativeValue() {
	@SuppressWarnings("unused")
	PassengerVehicle passengerVehicle = new PassengerVehicle("watagashi", -1);
    }
    
    @Test
    public void PassengerVehicleWithCapacity() {
	PassengerVehicle passengerVehicle = new PassengerVehicle(5);
	
	assertEquals(0, passengerVehicle.getId());
	assertEquals(null, passengerVehicle.getOwner());
	assertEquals(5, passengerVehicle.getCapacity());
    }

    @Test(expected=IllegalArgumentException.class)
    public void PassengerVehicleWithCapacityForCapacityIs0() {
	@SuppressWarnings("unused")
	PassengerVehicle passengerVehicle = new PassengerVehicle(0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void PassengerVehicleWithCapacityForCapacityIsNegativeValue() {
	@SuppressWarnings("unused")
	PassengerVehicle passengerVehicle = new PassengerVehicle(-1);
    }

    @Test
    public void setNumberOfPassengers() {
	PassengerVehicle passengerVehicle = new PassengerVehicle(2);
	passengerVehicle.setNumberOfPassengers(1);
	
	assertEquals(1, passengerVehicle.getNumberOfPassengers());
    }
    
    @Test
    public void setNumberOfPassengersForNumberIs0() {
	PassengerVehicle passengerVehicle = new PassengerVehicle(1);
	passengerVehicle.setNumberOfPassengers(0);
	
	assertEquals(0, passengerVehicle.getNumberOfPassengers());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void setNumberOfPassengersForNumberIsNegativeValue() {
	PassengerVehicle passengerVehicle = new PassengerVehicle(1);
	passengerVehicle.setNumberOfPassengers(-1);
    }
    
    @Test
    public void setNumberOfPassengersForNumberEqualsCapacity() {
	PassengerVehicle passengerVehicle = new PassengerVehicle(3);
	passengerVehicle.setNumberOfPassengers(3);
	
	assertEquals(3, passengerVehicle.getNumberOfPassengers());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void setNumberOfPassengersForNumberIsOverCapacity() {
	PassengerVehicle passengerVehicle = new PassengerVehicle(3);
	passengerVehicle.setNumberOfPassengers(4);
    }
}
