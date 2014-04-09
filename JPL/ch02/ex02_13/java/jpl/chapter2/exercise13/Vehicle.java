package jpl.chapter2.exercise13;

/**
 * nextId�͊O������֒m���ׂ����̂ł͂Ȃ��̂ŁAgetter�Asetter�Ƃ��ɒ�`���܂���B
 * id�͊O������ݒ肷����̂ł͂���܂��񂪁A�ǂ܂��K�v�͂���̂�getter�̂ݒ�`���܂��B velocity, direction,
 * owner�͂�������O������ݒ肷�邱�Ƃ��z�肳���̂ŁAgetter�Asetter�Ƃ��ɒ�`���܂��B
 */
public class Vehicle {

	private static int nextId = 0;
	private final int id;
	private double velocity;
	private double direction;
	private String owner;

	public static int getMaximumId() throws IllegalStateException {
		if (Vehicle.nextId == 0) {
			throw new IllegalStateException();
		} else {
			return Vehicle.nextId - 1;
		}
	}

	public static void resetId() {
		Vehicle.nextId = 0;
	}

	public int getId() {
		return this.id;
	}

	public double getVelocity() {
		return this.velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getDirection() {
		return this.direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return String.format("%d (%s)", this.getId(), this.owner);
	}

	public Vehicle() {
		this.id = Vehicle.nextId++;
	}

	public Vehicle(String owner) {
		this();
		this.setOwner(owner);
	}

}
