package jpl.chapter2.exercise11;

public class LinkedList {

    private static final String stringForNullValue = "null";
    public Object value = null;
    public LinkedList next = null;

    @Override
    public String toString() {
	String string;

	if (this.value != null) {
	    string = this.value.toString();
	} else {
	    string = stringForNullValue;
	}

	if (this.next != null) {
	    string += " -> " + this.next.toString();
	}
	return string;
    }

    public LinkedList() {
    }

    public LinkedList(Object value) {
	this.value = value;
    }

    public LinkedList(Object value, LinkedList next) {
	this.value = value;
	this.next = next;
    }

}
