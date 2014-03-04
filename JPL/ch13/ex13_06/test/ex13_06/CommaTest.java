package ex13_06;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class CommaTest {

	@Test
	public void test() {
		assertThat(Comma.insert(100, "4", 5), is("100"));
		assertThat(Comma.insert(1000, "a", 2), is("10a00"));
		assertThat(Comma.insert(1000000, "b", 1), is("1b0b0b0b0b0b0"));
	}

}
