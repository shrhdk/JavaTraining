package ex14_02;

import org.junit.Test;

public class PrintServerTest {

	@Test
	public void construct() {
		new PrintServer();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void runFromOutside() {
		PrintServer printServer = new PrintServer();
		printServer.run();
	}

}
