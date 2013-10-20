package junit;

import org.junit.*;
import static org.junit.Assert.*;

import jpl.chapter1.exercise9.*;

public class TestFibonacci {

	@Test
	public void testBuildFibonacci() {
		int[] fibonacci = Fibonacci.buildFibonacci(9);
		assertEquals(9, fibonacci.length);
		assertEquals(1, fibonacci[0]);
		assertEquals(1, fibonacci[1]);
		assertEquals(2, fibonacci[2]);
		assertEquals(3, fibonacci[3]);
		assertEquals(5, fibonacci[4]);
		assertEquals(8, fibonacci[5]);
		assertEquals(13, fibonacci[6]);
		assertEquals(21, fibonacci[7]);
		assertEquals(34, fibonacci[8]);
	}

	@Test
	public void testBuildFibonacciForMaxIndexIs1() {
		int[] fibonacci = Fibonacci.buildFibonacci(1);
		assertEquals(1, fibonacci.length);
		assertEquals(1, fibonacci[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuildFibonacciForTooSmallIndex() throws Exception {
		int[] fibonacci = Fibonacci.buildFibonacci(0);
	}
}
