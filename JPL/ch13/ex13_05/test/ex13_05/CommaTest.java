package ex13_05;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class CommaTest {

	@Test
	public void test() {
		assertThat(Comma.insert(100), is("100"));
		assertThat(Comma.insert(1000), is("1,000"));
		assertThat(Comma.insert(1000000), is("1,000,000"));
		assertThat(Comma.insert(1000000000), is("1,000,000,000"));
	}

}
