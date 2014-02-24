package ex14_07;

import org.junit.Test;

public class BabbleTest {

	private static final int REPEAT_COUNT = 3;
	
	@Test
	public void test() {
		for(int i = 0; i < REPEAT_COUNT; i++) {
			System.out.printf("= %02d ========\n", i + 1);
			Babble.main(new String[] { "false", "10", "Did", "DidNot" });
		}
	}

}
