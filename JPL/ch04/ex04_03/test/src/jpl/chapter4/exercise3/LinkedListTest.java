package jpl.chapter4.exercise3;

import org.junit.*;
import static org.junit.Assert.*;

public class LinkedListTest {

	@Test
	public void testLinkedList() {
		LinkedList<String> list = new LinkedList<String>();
		assertEquals(0, list.size());
	}

	@Test
	public void testLinkedListWithArguments() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		assertEquals("hoge", list.get(0));
		assertEquals("fuga", list.get(1));
		assertEquals("piyo", list.get(2));
		assertEquals(3, list.size());
	}

	@Test
	public void testGet() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		assertEquals("hoge", list.get(0));
		assertEquals("fuga", list.get(1));
		assertEquals("piyo", list.get(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetWithOutOfUpperBoundIndex() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		assertEquals("hoge", list.get(3));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetWithOutOfLowerBoundIndex() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		assertEquals("hoge", list.get(-1));
	}

	@Test
	public void testAdd() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		list.add("watagashi");
		assertEquals("hoge", list.get(0));
		assertEquals("fuga", list.get(1));
		assertEquals("piyo", list.get(2));
		assertEquals("watagashi", list.get(3));
		assertEquals(4, list.size());
	}

	@Test
	public void testAddToEmptyList() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("watagashi");
		assertEquals(1, list.size());
		assertEquals("watagashi", list.get(0));
	}

	@Test
	public void testAddWithIndex() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		list.add("watagashi", 1);
		assertEquals("hoge", list.get(0));
		assertEquals("watagashi", list.get(1));
		assertEquals("fuga", list.get(2));
		assertEquals("piyo", list.get(3));
		assertEquals(4, list.size());
	}

	@Test
	public void testAddWithIndexToEmptyList() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("watagashi", 0);
		assertEquals("watagashi", list.get(0));
		assertEquals(1, list.size());
	}

	@Test
	public void testAddForIndexIsListSize() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		list.add("watagashi", 3);
		assertEquals("hoge", list.get(0));
		assertEquals("fuga", list.get(1));
		assertEquals("piyo", list.get(2));
		assertEquals("watagashi", list.get(3));
		assertEquals(4, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddWithOutOfUpperBoundIndex() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		list.add("watagashi", 4);
	}

	public void testAddForIndexIs0() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		list.add("watagashi", 0);
		assertEquals("watagashi", list.get(0));
		assertEquals("hoge", list.get(1));
		assertEquals("fuga", list.get(2));
		assertEquals("piyo", list.get(3));
		assertEquals(4, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddWithOutOfLowerBoundIndex() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		list.add("watagashi", -1);
	}

	@Test
	public void testDelete() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo", "watagashi");

		list.delete(1);
		assertEquals("hoge", list.get(0));
		assertEquals("piyo", list.get(1));
		assertEquals("watagashi", list.get(2));
		assertEquals(3, list.size());

		list.delete(1);
		assertEquals("hoge", list.get(0));
		assertEquals("watagashi", list.get(1));
		assertEquals(2, list.size());
	}

	@Test
	public void testDeleteWithUpperBoundIndex() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo", "watagashi");
		list.delete(3);
		assertEquals("hoge", list.get(0));
		assertEquals("fuga", list.get(1));
		assertEquals("piyo", list.get(2));
		assertEquals(3, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteWithOutOfUpperBoundIndex() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo", "watagashi");
		list.delete(4);
	}

	@Test
	public void testDeleteWithLowerBoundIndex() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo", "watagashi");
		list.delete(0);
		assertEquals("fuga", list.get(0));
		assertEquals("piyo", list.get(1));
		assertEquals("watagashi", list.get(2));
		assertEquals(3, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteWithOutOfLowerBoundIndex() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo", "watagashi");
		list.delete(-1);
	}

	@Test
	public void testClear() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		list.clear();
		assertEquals(0, list.size());
	}

	@Test
	public void testClearWithEmptyList() {
		LinkedList<String> list = new LinkedList<String>();
		list.clear();
		assertEquals(0, list.size());
	}

	@Test
	public void testSize() {
		LinkedList<String> list = new LinkedList<String>("hoge", "fuga", "piyo");
		assertEquals(3, list.size());
	}

	@Test
	public void testSizeWithEmptyList() {
		LinkedList<String> list = new LinkedList<String>();
		assertEquals(0, list.size());
	}

	@Test
	public void testClone() {
		StringBuffer[] values = new StringBuffer[] { new StringBuffer("hoge"), new StringBuffer("fuga"), new StringBuffer("piyo"), new StringBuffer("watagashi") };
		LinkedList<StringBuffer> list = new LinkedList<StringBuffer>(values);
		LinkedList<StringBuffer> cloneList = list.clone();

		cloneList.delete(1); // delete fuga
		cloneList.delete(2); // delete watagashi
		cloneList.get(1).append("piyo"); // get piyo and make piyopiyo
		
		assertEquals("hoge", list.get(0).toString());
		assertEquals("fuga", list.get(1).toString());
		assertEquals("piyopiyo", list.get(2).toString());
		assertEquals("watagashi", list.get(3).toString());
		assertEquals(4, list.size());

		assertEquals("hoge", cloneList.get(0).toString());
		assertEquals("piyopiyo", cloneList.get(1).toString());
		assertEquals(2, cloneList.size());
	}
}