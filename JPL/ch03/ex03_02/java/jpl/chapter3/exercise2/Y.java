package jpl.chapter3.exercise2;

public class Y extends X {

	protected int yMask = 0xff00;

	{
		Observer.add("Init Y field", xMask, yMask, fullMask);
	}

	public Y() {
		fullMask |= yMask;
		Observer.add("Exec Y constructor", xMask, yMask, fullMask);
	}

}
