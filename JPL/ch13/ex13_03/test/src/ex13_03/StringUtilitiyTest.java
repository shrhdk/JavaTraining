package ex13_03;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

import static ex13_03.StringUtility.delimitedString;

public class StringUtilitiyTest {

	@Test
	public void testDelimitedString() {
		String[] delimited = null;
		
		delimited = delimitedString("(<*>o<*>)", '<', '>');
		assertThat(delimited.length, is(2));
		assertThat(delimited[0], is("<*>"));
		assertThat(delimited[1], is("<*>"));
		
		delimited = delimitedString("<*>o<*>)", '<', '>');
		assertThat(delimited.length, is(2));
		assertThat(delimited[0], is("<*>"));
		assertThat(delimited[1], is("<*>"));
		
		delimited = delimitedString("(<*>o<*>", '<', '>');
		assertThat(delimited.length, is(2));
		assertThat(delimited[0], is("<*>"));
		assertThat(delimited[1], is("<*>"));
		
		delimited = delimitedString("<*>o<*>", '<', '>');
		assertThat(delimited.length, is(2));
		assertThat(delimited[0], is("<*>"));
		assertThat(delimited[1], is("<*>"));
		
		delimited = delimitedString("(<*>o<*", '<', '>');
		assertThat(delimited.length, is(2));
		assertThat(delimited[0], is("<*>"));
		assertThat(delimited[1], is("<*"));
		
		delimited = delimitedString("fuga<hoge", '<', '>');
		assertThat(delimited.length, is(1));
		assertThat(delimited[0], is("<hoge"));
		
		delimited = delimitedString("<hoge", '<', '>');
		assertThat(delimited.length, is(1));
		assertThat(delimited[0], is("<hoge"));
		
		delimited = delimitedString(">hoge<fuga>", '<', '>');
		assertThat(delimited.length, is(1));
		assertThat(delimited[0], is("<fuga>"));
		
		delimited = delimitedString(">hoge<fuga", '<', '>');
		assertThat(delimited.length, is(1));
		assertThat(delimited[0], is("<fuga"));
	}

}
