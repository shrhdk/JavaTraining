package jpl.chapter1.exercise12;

public class ImprovedFibonacci {

    static final int MAX_INDEX = 9;

    public static void main(String[] args) {
	String[] fibonacci = ImprovedFibonacci.build(MAX_INDEX);
	for (String line : fibonacci) {
	    System.out.println(line);
	}
    }

    public static String[] build(final int maxIndex) throws IllegalArgumentException {
	if (maxIndex < 1)
	    throw new IllegalArgumentException("maxIndex needs to be 1 and over.");

	int lo = 1;
	int hi = 1;

	String[] fibonacci = new String[maxIndex];

	fibonacci[0] = "1: 1";

	if (2 <= maxIndex) {
	    for (int i = 1; i < maxIndex; i++) {
		String mark = (hi % 2 == 0) ? " *" : "";
		fibonacci[i] = String.format("%d: %d%s", i + 1, hi, mark);
		hi = lo + hi;
		lo = hi - lo;
	    }
	}

	return fibonacci;
    }
}
