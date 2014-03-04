package ex09_01;

import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Double.NEGATIVE_INFINITY;

public class Infinity {
	public static void main(String[] args) {
		// Operator +
		System.out.printf("+Infinity + +Infinity = %f\n", POSITIVE_INFINITY + POSITIVE_INFINITY);
		System.out.printf("+Infinity + -Infinity = %f\n", POSITIVE_INFINITY + NEGATIVE_INFINITY);
		System.out.printf("-Infinity + +Infinity = %f\n", NEGATIVE_INFINITY + POSITIVE_INFINITY);
		System.out.printf("-Infinity + -Infinity = %f\n", NEGATIVE_INFINITY + NEGATIVE_INFINITY);

		// Operator -
		System.out.printf("+Infinity - +Infinity = %f\n", POSITIVE_INFINITY - POSITIVE_INFINITY);
		System.out.printf("+Infinity - -Infinity = %f\n", POSITIVE_INFINITY - NEGATIVE_INFINITY);
		System.out.printf("-Infinity - +Infinity = %f\n", NEGATIVE_INFINITY - POSITIVE_INFINITY);
		System.out.printf("-Infinity - -Infinity = %f\n", NEGATIVE_INFINITY - NEGATIVE_INFINITY);

		// Operator *
		System.out.printf("+Infinity * +Infinity = %f\n", POSITIVE_INFINITY * POSITIVE_INFINITY);
		System.out.printf("+Infinity * -Infinity = %f\n", POSITIVE_INFINITY * NEGATIVE_INFINITY);
		System.out.printf("-Infinity * +Infinity = %f\n", NEGATIVE_INFINITY * POSITIVE_INFINITY);
		System.out.printf("-Infinity * -Infinity = %f\n", NEGATIVE_INFINITY * NEGATIVE_INFINITY);

		// Operator /
		System.out.printf("+Infinity / +Infinity = %f\n", POSITIVE_INFINITY / POSITIVE_INFINITY);
		System.out.printf("+Infinity / -Infinity = %f\n", POSITIVE_INFINITY / NEGATIVE_INFINITY);
		System.out.printf("-Infinity / +Infinity = %f\n", NEGATIVE_INFINITY / POSITIVE_INFINITY);
		System.out.printf("-Infinity / -Infinity = %f\n", NEGATIVE_INFINITY / NEGATIVE_INFINITY);
	}
}
