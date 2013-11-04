package jpl.chapter2.exercise6;

public class Vehicle {
    private static int nextId = 0;
    private final int id = nextId++;
    public double velocity;
    public double direction;
    public String owner;

    public int getId() {
	return this.id;
    }
}