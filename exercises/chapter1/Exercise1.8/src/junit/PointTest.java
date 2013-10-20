package junit;

import org.junit.Test;
import junit.framework.TestCase;
import jpl.chapter1.exercise8.Point;

public class PointTest extends TestCase {

	@Test
	public void testPoint() {
		Point point = new Point(3.0, 8.0);
		assertEquals(3.0, point.x);
		assertEquals(8.0, point.y);
	}

}
