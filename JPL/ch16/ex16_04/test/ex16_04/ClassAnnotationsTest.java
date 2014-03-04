package ex16_04;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClassAnnotationsTest {

	@Test
	public void test() {
		ClassAnnotations.main(new String[] { "ex16_04.annotatedClass" });
	}

}

class annotatedClass {
	
}