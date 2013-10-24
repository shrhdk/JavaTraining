package jpl.chapter2.exercise5;

import java.lang.Math;

public class Vehicle {
    private static int nextId = 0;
    private final int id = nextId++;
    public double currentVelocity;
    public double currentDirection;
    public String owner;

    public static void main(String[] args) {
	Vehicle vehicle1 = new Vehicle();
	vehicle1.currentVelocity = 45.0;
	vehicle1.currentDirection = Math.PI;
	vehicle1.owner = "watagashi";

	Vehicle vehicle2 = new Vehicle();
	vehicle2.currentVelocity = 60.0;
	vehicle2.currentDirection = Math.PI / 2;
	vehicle2.owner = "fujii";

	System.out.println("*** Vehicle " + vehicle1.getId() + " ***");
	System.out.println("currentVelocity: " + vehicle1.currentVelocity);
	System.out.println("currentDirection:" + vehicle1.currentDirection / Math.PI * 180);
	System.out.println("owner: " + vehicle1.owner);
	System.out.println("");

	System.out.println("*** Vehicle " + vehicle2.getId() + " ***");
	System.out.println("currentVelocity: " + vehicle2.currentVelocity);
	System.out.println("currentDirection:" + vehicle2.currentDirection / Math.PI * 180);
	System.out.println("owner: " + vehicle2.owner);
	System.out.println("");
    }

    public int getId() {
	return this.id;
    }
}
