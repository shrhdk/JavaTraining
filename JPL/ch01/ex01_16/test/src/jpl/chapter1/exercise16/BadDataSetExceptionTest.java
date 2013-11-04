package jpl.chapter1.exercise16;

import java.io.IOException;

import org.junit.*;
import static org.junit.Assert.*;
import jpl.chapter1.exercise16.*;

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
