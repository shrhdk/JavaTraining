package jpl.chapter2.exercise6;

import org.junit.*;
import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void testLinkedList() {
	LinkedList linkedList = new LinkedList();
	assertEquals(null, linkedList.value);
	assertEquals(null, linkedList.next);
    }

}
