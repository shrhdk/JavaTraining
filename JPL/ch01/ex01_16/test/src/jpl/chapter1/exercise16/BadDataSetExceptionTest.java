package jpl.chapter1.exercise16;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;

public class BadDataSetExceptionTest {

	@Test
	public void testBadDataSetException() {
		String setName = "hgoe";
		IOException ioe = new IOException();
		BadDataSetException bdse = new BadDataSetException(setName, ioe);
		assertEquals(setName, bdse.setName);
		assertEquals(ioe, bdse.internalIOException);
	}
}
