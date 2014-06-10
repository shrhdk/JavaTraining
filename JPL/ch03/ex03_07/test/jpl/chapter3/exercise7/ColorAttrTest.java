package jpl.chapter3.exercise7;

import org.junit.*;
import static org.junit.Assert.*;

public class ColorAttrTest {

	@Test
	public void testEquals() {
		
		/*
		 * +----+------+-------+-------------+
		 * |    | Name | Value | a.equals(*) |
		 * +----+------+-------+-------------+
		 * | a  | hoge |       |    true     |
		 * +----+------+-------+-------------+
		 * | a1 | fuga |       |    false    |
		 * | a2 | hoge |       |    true     |
		 * +----+------+-------+-------------+
		 * 
		 * */
		ColorAttr a = new ColorAttr("hoge");
		ColorAttr a1 = new ColorAttr("fuga");
		ColorAttr a2 = new ColorAttr("hoge");
		
		assertEquals(true, a.equals(a));
		assertEquals(false, a.equals(a1));
		assertEquals(true, a.equals(a2));
		
		/*
		 * +----+-------+-------+-------------+
		 * |    | Name  | Value | b.equals(*) |
		 * +----+-------+-------+-------------+
		 * | b  | hoge  |  foo  |    true     |
		 * +----+-------+-------+-------------+
		 * | b1 | fuga  |  bar  |    false    |
		 * | b2 | fuga  |  foo  |    false    |
		 * | b3 | hoge  |  bar  |    false    |
		 * | b4 | hoge  |  foo  |    true     |
		 * +----+-------+-------+-------------+
		 * */

		ColorAttr b = new ColorAttr("hoge", "foo");
		ColorAttr b1 = new ColorAttr("fuga", "bar");
		ColorAttr b2 = new ColorAttr("fuga", "foo");
		ColorAttr b3 = new ColorAttr("hoge", "bar");
		ColorAttr b4 = new ColorAttr("hoge", "foo");
		
		assertEquals(true, b.equals(b));
		assertEquals(false, b.equals(b1));
		assertEquals(false, b.equals(b2));
		assertEquals(false, b.equals(b3));
		assertEquals(true, b.equals(b4));

	}
	
	@Test
	public void testHashCode() {
		/*
		 * +----+-------+-------+------------------+
		 * |    | Name  | Value | a.hashCode()     |
		 * +----+-------+-------+------------------+
		 * | a  |  hoge |       | == a.hashCode()  |
		 * +----+-------+-------+------------------+
		 * | a1 |  fuga |       | != a1.hashCode() |
		 * | a2 |  hoge |       | == a2.hashCode() |
		 * +----+-------+-------+------------------+
		 * 
		 * */
		ColorAttr a = new ColorAttr("hoge");
		ColorAttr a1 = new ColorAttr("fuga");
		ColorAttr a2 = new ColorAttr("hoge");

		assertEquals(a.hashCode(), a.hashCode());
		assertNotEquals(a.hashCode(), a1.hashCode());
		assertEquals(a.hashCode(), a2.hashCode());
		
		/*
		 * +----+-------+-------+------------------+
		 * |    | Name  | Value | b.hashCode()     |
		 * +----+-------+-------+------------------+
		 * | b  | hoge  |  foo  | == b.hashCode()  |
		 * +----+-------+-------+------------------+
		 * | b1 |  !eq  |  !eq  | != b1.hashCode() |
		 * | b2 |  !eq  |   eq  | != b2.hashCode() |
		 * | b3 |   eq  |  !eq  | != b3.hashCode() |
		 * | b4 |   eq  |   eq  | == b4.hashCode() |
		 * +----+-------+-------+------------------+
		 * */

		ColorAttr b = new ColorAttr("hoge", "foo");
		ColorAttr b1 = new ColorAttr("fuga", "bar");
		ColorAttr b2 = new ColorAttr("fuga", "foo");
		ColorAttr b3 = new ColorAttr("hoge", "bar");
		ColorAttr b4 = new ColorAttr("hoge", "foo");
		
		assertEquals(b.hashCode(), b.hashCode());
		assertNotEquals(b.hashCode(), b1.hashCode());
		assertNotEquals(b.hashCode(), b2.hashCode());
		assertNotEquals(b.hashCode(), b3.hashCode());
		assertEquals(b.hashCode(), b4.hashCode());

	}

}
