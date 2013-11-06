package jpl.chapter1.exercise12;

import org.junit.*;
import static org.junit.Assert.*;

public class ImprovedFibonacciTest {

	@Test
	public void testBuild() {
		String[] fibonacci = ImprovedFibonacci.build(9);
		assertEquals(9, fibonacci.length);
		assertEquals("1: 1", fibonacci[0]);
		assertEquals("2: 1", fibonacci[1]);
		assertEquals("3: 2 *", fibonacci[2]);
		assertEquals("4: 3", fibonacci[3]);
		assertEquals("5: 5", fibonacci[4]);
		assertEquals("6: 8 *", fibonacci[5]);
		assertEquals("7: 13", fibonacci[6]);
		assertEquals("8: 21", fibonacci[7]);
		assertEquals("9: 34 *", fibonacci[8]);
	}

	@Test
	public void testBuildForMaxIndexIs1() {
		String[] fibonacci = ImprovedFibonacci.build(1);
		assertEquals(1, fibonacci.length);
		assertEquals("1: 1", fibonacci[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuildForMaxIndexIs0() throws Exception {
		@SuppressWarnings("unused")
		String[] fibonacci = ImprovedFibonacci.build(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuildForMaxIndexIsNegativeValue() throws Exception {
		@SuppressWarnings("unused")
		String[] fibonacci = ImprovedFibonacci.build(-1);
	}
}
