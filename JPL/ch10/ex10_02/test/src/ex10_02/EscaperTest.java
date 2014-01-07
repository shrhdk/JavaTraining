package ex10_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class EscaperTest {

	@Test
	public void testEscape() {
		String escaped = Escaper.escape("\n\t\b\r\f\\\'\"hoge");
		assertEquals("\\n\\t\\b\\r\\f\\\\\\\'\\\"hoge", escaped);
	}

}
