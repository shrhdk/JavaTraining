package ex06_04;

import static org.junit.Assert.*;
import jpl.chapter6.exercise4.Signal;

import org.junit.Test;

public class SignalTest {

	@Test
	public void testRed() {
		Signal.Color color = Signal.RED.getColor();
		assertEquals(255, color.r);
		assertEquals(0, color.g);
		assertEquals(0, color.b);
	}

	@Test
	public void testGreen() {
		Signal.Color color = Signal.GREEN.getColor();
		assertEquals(0, color.r);
		assertEquals(255, color.g);
		assertEquals(0, color.b);
	}

	@Test
	public void testYellow() {
		Signal.Color color = Signal.YELLOW.getColor();
		assertEquals(255, color.r);
		assertEquals(255, color.g);
		assertEquals(0, color.b);
	}

}
