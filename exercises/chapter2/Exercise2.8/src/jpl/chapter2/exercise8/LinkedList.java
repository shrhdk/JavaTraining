package jpl.chapter2.exercise8;

public class LinkedList {

    public Object value = null;
    public LinkedList next = null;

    public LinkedList(){
    }
    
    public LinkedList(Object value) {
	this.value = value;
    }

    public LinkedList(Object value, LinkedList next) {
	this.value = value;
	this.next = next;
    }

}
