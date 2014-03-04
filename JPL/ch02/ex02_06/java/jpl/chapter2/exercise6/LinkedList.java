package jpl.chapter2.exercise6;

public class LinkedList {

	public Object value = null;
	public LinkedList next = null;

	public static void main(String[] args) {
		LinkedList linkedList1 = new LinkedList();
		linkedList1.value = new Vehicle();

		LinkedList linkedList2 = new LinkedList();
		linkedList2.value = new Vehicle();

		LinkedList linkedList3 = new LinkedList();
		linkedList3.value = new Vehicle();

		linkedList1.next = linkedList2;
		linkedList2.next = linkedList3;

		System.out.println("*** linkedList1 ***");
		System.out.println("linkedList1       -> " + linkedList1);
		System.out.println("linkedList1.value -> " + linkedList1.value);
		System.out.println("linkedList1.next  -> " + linkedList1.next);
		System.out.println("");

		System.out.println("*** linkedList2 ***");
		System.out.println("linkedList2       -> " + linkedList2);
		System.out.println("linkedList2.value -> " + linkedList2.value);
		System.out.println("linkedList2.next  -> " + linkedList2.next);
		System.out.println("");

		System.out.println("*** linkedList3 ***");
		System.out.println("linkedList3       -> " + linkedList3);
		System.out.println("linkedList3.value -> " + linkedList3.value);
		System.out.println("linkedList3.next  -> " + linkedList3.next);
		System.out.println("");
	}

}
