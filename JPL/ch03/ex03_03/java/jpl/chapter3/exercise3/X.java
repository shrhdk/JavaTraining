package jpl.chapter3.exercise3;

public class X {

	protected int fullMask = 0;
	protected final int xMask = 0x00ff;

	protected int getXMask() {
		return xMask;
	}

	public X() {
		System.out.printf("== Constructor of X ==%n");
		System.out
				.printf("# fullMask => 0x%04x, X.xMask => 0x%04x, getXmask() => 0x%04x%n",
						fullMask, xMask, getXMask());
		System.out.printf("> fullMask |= getXMask();%n");
		fullMask |= getXMask();
		System.out.printf("# fullMask => 0x%04x%n", fullMask);
	}

}
