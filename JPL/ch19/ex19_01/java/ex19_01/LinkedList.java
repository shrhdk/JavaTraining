package ex19_01;

/**
 * Item of linked list structure
 */
public class LinkedList {

	private Object value = null;
	private LinkedList next = null;

    // Constructor

    /**
     * Construct linked list with specific objects;
     * @param value Objects stored to new linked list
     */
	public LinkedList(Object value) {
		this.value = value;
	}

    /**
     *
     * @param value
     * @param next
     */
	public LinkedList(Object value, LinkedList next) {
		this.value = value;
		this.next = next;
	}

    // Setter and Getter

    /**
     * Get the value in this linked list item
     * @param value The value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Get value in this linked list item
     * @return The value of this linked list item
     */
    public Object getValue() {
        return value;
    }

    /**
     * Set the next linked list item
     * @param next The next linked list item to set
     */
    public void setNext(LinkedList next) {
        this.next = next;
    }

    /**
     * Get the next linked list item
     * @return The next linked list item
     */
    public LinkedList getNext() {
        return next;
    }

    // Utility

    /**
     * Get length of this list
     * @return Length of list
     */
    public int getLength() {
        if (this.next == null)
            return 1;
        else
            return 1 + this.next.getLength();
    }
}
