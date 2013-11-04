package jpl.chapter2.exercise5;

import java.lang.Math;

public class Vehicle {
    private static int nextId = 0;
    private final int id = nextId++;
    public double velocity;
    public double direction;
    public String owner;

    public static void main(String[] args) {
	Vehicle vehicle1 = new Vehicle();
	vehicle1.velocity = 45.0;
	vehicle1.direction = Math.PI;
	vehicle1.owner = "watagashi";

	Vehicle vehicle2 = new Vehicle();
	vehicle2.velocity = 60.0;
	vehicle2.direction = Math.PI / 2;
	vehicle2.owner = "fujii";

	System.out.println("*** Vehicle " + vehicle1.getId() + " ***");
	System.out.println("velocity: " + vehicle1.velocity);
	System.out.println("direction:" + vehicle1.direction / Math.PI * 180);
	System.out.println("owner: " + vehicle1.owner);
	System.out.println("");

	System.out.println("*** Vehicle " + vehicle2.getId() + " ***");
	System.out.println("velocity: " + vehicle2.velocity);
	System.out.println("direction:" + vehicle2.direction / Math.PI * 180);
	System.out.println("owner: " + vehicle2.owner);
	System.out.println("");
    }

    public int getId() {
	return this.id;
    }
}
