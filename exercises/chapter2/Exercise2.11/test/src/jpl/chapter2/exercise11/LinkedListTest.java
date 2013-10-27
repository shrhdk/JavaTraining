package jpl.chapter2.exercise11;

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
    public void testToString() {
	LinkedList linkedList1 = new LinkedList("list1");
	LinkedList linkedList2 = new LinkedList("list2");
	LinkedList linkedList3 = new LinkedList("list3");
	LinkedList linkedList4 = new LinkedList("list4");
	
	linkedList1.next = linkedList2;
	linkedList2.next = linkedList3;
	linkedList3.next = linkedList4;
	
	assertEquals("list1 -> list2 -> list3 -> list4", linkedList1.toString());
    }

    @Test
    public void testToStringForSingleLinkedList() {
	LinkedList linkedList = new LinkedList("hoge");

	assertEquals("hoge", linkedList.toString());
    }

    @Test
    public void testToStringForValueIsNull() {
	LinkedList linkedList = new LinkedList();

	assertEquals("null", linkedList.toString());
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
