package jpl.chapter1.exercise9;

public class Fibonacci {

	static final int MAX_INDEX = 9;
	
	public static void main(String[] args) {
		int[] fibonacci = buildFibonacci(MAX_INDEX);
		
		String mark = "";
		for(int i = 0; i < fibonacci.length; i++){
			if (fibonacci[i] % 2 == 0)
				mark = " *";
			else
				mark = "";
			System.out.println(i + ": " + fibonacci[i] + mark);
		}
	}

	public static int[] buildFibonacci(final int maxIndex) throws IllegalArgumentException {
		int[] fibonacci = new int[maxIndex];

		if (maxIndex < 1) {
			throw new IllegalArgumentException("maxIndex needs to be 1 and over.");
		}

		int lo = 1;
		int hi = 1;

		fibonacci[0] = lo;

		if (2 <= maxIndex) {
			for (int i = 1; i < maxIndex; i++) {
				fibonacci[i] = hi;
				hi = lo + hi;
				lo = hi - lo;
			}
		}

		return fibonacci;
	}
}
