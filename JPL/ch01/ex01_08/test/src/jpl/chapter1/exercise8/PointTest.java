package jpl.chapter1.exercise8;

import org.junit.*;
import static org.junit.Assert.*;

import jpl.chapter1.exercise8.Point;

public class PointTest {

	@Test
	public void testPoint() {
		Point point = new Point(3.0, 8.0);
		assertEquals(3.0, point.x, 0);
		assertEquals(8.0, point.y, 0);
	}

}
