package jpl.chapter1.exercise10;

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		ImprovedFibonacci.FibonacciNumber[] fibonacci = ImprovedFibonacci
				.build(MAX_INDEX);

		for (int i = 0; i < fibonacci.length; i++) {
			String mark = fibonacci[i].isEven() ? " *" : "";
			String line = String.format("%d: %d%s", i + 1,
					fibonacci[i].getNumber(), mark);
			System.out.println(line);
		}
	}

	public static FibonacciNumber[] build(final int maxIndex)
			throws IllegalArgumentException {

		if (maxIndex <= 0)
			throw new IllegalArgumentException(
					"maxIndex needs to be 1 and over.");

		int lo = 1;
		int hi = 1;

		ImprovedFibonacci.FibonacciNumber[] fibonacci = new ImprovedFibonacci.FibonacciNumber[maxIndex];

		fibonacci[0] = new ImprovedFibonacci.FibonacciNumber(1);

		if (2 <= maxIndex) {
			for (int i = 1; i < maxIndex; i++) {
				fibonacci[i] = new ImprovedFibonacci.FibonacciNumber(hi);
				hi = lo + hi;
				lo = hi - lo;
			}
		}

		return fibonacci;
	}

	public static class FibonacciNumber {
		int number;

		public FibonacciNumber(final int number) {
			this.number = number;
		}

		public int getNumber() {
			return number;
		}

		public boolean isEven() {
			return number % 2 == 0;
		}
	}
}
