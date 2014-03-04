package jpl.chapter3.exercise2;

public class X {

	protected int xMask = 0x00ff;
	protected int fullMask;

	{
		Observer.add("Init X field", xMask, 0, fullMask);
	}

	public X() {
		Observer.add("Call X constructor", xMask, 0, fullMask);
		fullMask = xMask;
		Observer.add("Exec X constructor", xMask, 0, fullMask);
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}

}
