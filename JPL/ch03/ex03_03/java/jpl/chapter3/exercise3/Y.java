package jpl.chapter3.exercise3;

public class Y extends X {

	protected final int xMask = 0x000f;
	protected final int yMask = 0xff00;

	@Override
	protected int getXMask() {
		return xMask;
	}

	protected int getYMask() {
		return yMask;
	}

	public Y() {
		System.out.printf("== Constructor of Y ==%n");
		System.out
				.printf("# fullMask => 0x%04x, Y.xMask => 0x%04x, getXmask() => 0x%04x%n",
						fullMask, xMask, getXMask());
		System.out
				.printf("# fullMask => 0x%04x, Y.yMask => 0x%04x, getYmask() => 0x%04x%n",
						fullMask, yMask, getYMask());
		System.out.printf("> fullMask |= getYMask();%n");
		fullMask |= getYMask();
		System.out.printf("# fullMask => 0x%04x", fullMask);
	}

	public static void main(String[] args) {
		System.out.println("Exercise 3.3");
		System.out
				.println("Answer: Get field via getter method instead of immediate access, and override that getter method in subclasses.");
		System.out.println();
		System.out
				.println("Example: Please note that difference of xMask and getXMask()");
		System.out.printf("== main ==%n");
		System.out.printf("> Y y = new Y()%n");
		@SuppressWarnings("unused")
		Y y = new Y();
	}
}
