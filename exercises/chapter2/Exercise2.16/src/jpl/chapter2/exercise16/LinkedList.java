package jpl.chapter2.exercise16;

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

    public int getLength() {
	if(this.next == null)
	    return 1;
	else
	    return 1 + this.next.getLength();
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

    public LinkedList(Object... objects) {
	this(0, objects);
    }

    private LinkedList(int offset, Object... objects) {
	this.value = objects[offset++];
	if (offset < objects.length)
	    this.next = new LinkedList(offset, objects);
    }
    
}
