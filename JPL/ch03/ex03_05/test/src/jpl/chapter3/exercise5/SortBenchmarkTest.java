package jpl.chapter3.exercise5;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

public class SortBenchmarkTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void testMain() {
		SortBenchmark.main(new String[] { "1000", "1100" });
		String result = outContent.toString();

		String regex = "1,000 random numbers \\(0~1,000\\) were sorted 1,100 times in \\b\\d{1,3}(,\\d{3})*\\b nsec\\.\\n";
		if (!Pattern.matches(regex, result)) {
			fail(String
					.format("\"%s\" is not match with \"%s\"", result, regex));
		}
	}
}
