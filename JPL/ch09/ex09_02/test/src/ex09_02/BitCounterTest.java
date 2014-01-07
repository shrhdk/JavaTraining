package ex09_02;

import static org.junit.Assert.*;
import org.junit.Test;

public class BitCounterTest {

	@Test
	public void testBitCount() {
		assertEquals(0, BitCounter.bitCount(0));
		assertEquals(2, BitCounter.bitCount(3));
		assertEquals(32, BitCounter.bitCount(-1));
	}

}
