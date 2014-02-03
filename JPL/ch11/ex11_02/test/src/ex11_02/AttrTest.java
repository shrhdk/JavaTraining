package ex11_02;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class AttrTest {

	@Test
	public void construct() {
		Attr<String> attr = new Attr<String>("hoge", "fuga");
		assertThat(attr.getName(), is("hoge"));
		assertThat(attr.getValue(), is("fuga"));
	}
	
	@Test
	public void constructWithNoValue() {
		Attr<Object> attr = new Attr<Object>("hoge");
		assertThat(attr.getName(), is("hoge"));
		assertThat(attr.getValue(), nullValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructWithNullName() {
		new Attr<Object>(null, new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructWithEmptyName() {
		new Attr<Object>("", new Object());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithNullNameAndNoValue() {
		new Attr<Object>(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithEmptyNameAndNoValue() {
		new Attr<Object>("");
	}
	
	@Test
	public void setValue() {
		Attr<String> attr = new Attr<String>("hoge");
		assertThat(attr.getName(), is("hoge"));
		assertThat(attr.getValue(), nullValue());
		attr.setValue("fuga");
		assertThat(attr.getValue(), is("fuga"));
	}
	
	@Test
	public void testToString() {
		Attr<String> attr = new Attr<String>("hoge", "fuga");
		assertThat(attr.toString(), is("hoge='fuga'"));
	}
}
