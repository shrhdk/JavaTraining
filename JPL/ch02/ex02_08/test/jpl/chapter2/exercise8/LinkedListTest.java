package jpl.chapter2.exercise8;

import org.junit.*;
import static org.junit.Assert.*;

public class LinkedListTest {

	@Test
	public void testLinkedList() {
		LinkedList linkedList = new LinkedList();
		assertEquals(null, linkedList.value);
		assertEquals(null, linkedList.next);
	}

	@Test
	public void testLinkedListWithValue() {
		Object object = new Object();
		LinkedList linkedList = new LinkedList(object);
		assertEquals(object, linkedList.value);
		assertEquals(null, linkedList.next);
	}

	@Test
	public void testLinkedListWithValueAndNext() {
		Object object = new Object();
		LinkedList linkedList2 = new LinkedList();
		LinkedList linkedList1 = new LinkedList(object, linkedList2);
		assertEquals(object, linkedList1.value);
		assertEquals(linkedList2, linkedList1.next);
	}

}
