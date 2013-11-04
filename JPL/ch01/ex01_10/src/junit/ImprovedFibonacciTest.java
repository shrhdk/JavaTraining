package junit;

import static org.junit.Assert.*;
import jpl.chapter1.exercise10.*;

import org.junit.Test;

public class ImprovedFibonacciTest {

    @Test
    public void testBuildForNumbers() {
	ImprovedFibonacci.FibonacciNumber[] fibonacci = ImprovedFibonacci.build(9);
	assertEquals(9, fibonacci.length);
	assertEquals(1, fibonacci[0].getNumber());
	assertEquals(1, fibonacci[1].getNumber());
	assertEquals(2, fibonacci[2].getNumber());
	assertEquals(3, fibonacci[3].getNumber());
	assertEquals(5, fibonacci[4].getNumber());
	assertEquals(8, fibonacci[5].getNumber());
	assertEquals(13, fibonacci[6].getNumber());
	assertEquals(21, fibonacci[7].getNumber());
	assertEquals(34, fibonacci[8].getNumber());
    }

    @Test
    public void testBuildForEvenMarks() {
	ImprovedFibonacci.FibonacciNumber[] fibonacci = ImprovedFibonacci.build(9);
	assertEquals(9, fibonacci.length);
	assertEquals(false, fibonacci[0].isEven()); 	// 1
	assertEquals(false, fibonacci[1].isEven()); 	// 1
	assertEquals(true, fibonacci[2].isEven()); 	// 2
	assertEquals(false, fibonacci[3].isEven()); 	// 3
	assertEquals(false, fibonacci[4].isEven()); 	// 5
	assertEquals(true, fibonacci[5].isEven()); 	// 8
	assertEquals(false, fibonacci[6].isEven()); 	// 13
	assertEquals(false, fibonacci[7].isEven()); 	// 21
	assertEquals(true, fibonacci[8].isEven()); 	// 34
    }

    @Test
    public void testBuildForMaxIndexIs1() {
	ImprovedFibonacci.FibonacciNumber[] fibonacci = ImprovedFibonacci.build(1);
	assertEquals(1, fibonacci.length);
	assertEquals(1, fibonacci[0].getNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuildForMaxIndexIs0() throws Exception {
	@SuppressWarnings("unused")
	ImprovedFibonacci.FibonacciNumber[] fibonacci = ImprovedFibonacci.build(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuildForMaxIndexIsNegativeValue() throws Exception {
	@SuppressWarnings("unused")
	ImprovedFibonacci.FibonacciNumber[] fibonacci = ImprovedFibonacci.build(-1);
    }
}
