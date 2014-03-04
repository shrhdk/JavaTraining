package ex16_02;

import org.junit.Test;

public class TypeDescTest {

	public class hoge {
	}
	
	@Test
	public void test() {
		TypeDesc.main(new String[] { "ex16_02.TypeDescTest$hoge" });
	}

}
