package ex12_01;

public class LinkedList<T> implements Cloneable {

	private Item<T> first = null;
	private Item<T> last = null;

	public LinkedList() {
	}

	public LinkedList(T... values) {
		for (T value : values) {
			add(value);
		}
	}
	
	public Item<T> find(T value) throws ObjectNotFoundException {
		for(Item<T> item = first; item != null; item = item.next) {
			if(item.value == value) {
				return item;
			}
		}
		throw new ObjectNotFoundException();
	}

	public T get(int index) {
		return getItem(index).value;
	}

	public Item<T> getItem(int index) {
		if (index < 0 || size() - 1 < index) { // Out of bounds
			throw new IndexOutOfBoundsException();
		} else if (0 <= index && index <= size() - 1) {
			Item<T> current = first;
			for (int i = 0; i < index; i++) {
				if (current.next == null) {
					throw new IndexOutOfBoundsException();
				} else {
					current = current.next;
				}
			}
			return current;
		} else {
			throw new AssertionError();
		}
	}

	public void add(T value) {
		if (size() == 0) {
			Item<T> item = new Item<T>(null, null, value);
			first = item;
			last = item;
		} else {
			Item<T> item = new Item<T>(last, null, value);
			last.next = item;
			last = item;
		}
	}

	public void add(T value, int index) {
		if (index < 0 || size() < index) { // Out of bounds
			throw new IndexOutOfBoundsException();
		} else if (index == size()) { // Upper bound index
			add(value);
		} else if (index == 0) { // Lower bound index
			Item<T> item = new Item<T>(null, null, value);
			Item<T> earlier = getItem(0);
			item.previous = null;
			first = item;
			item.next = earlier;
			earlier.previous = item;
		} else if (0 < index && index < size()) {
			Item<T> item = new Item<T>(null, null, value);
			Item<T> earlier = getItem(index);
			item.previous = earlier.previous;
			item.previous.next = item;
			item.next = earlier;
			earlier.previous = item;
		} else {
			throw new AssertionError();
		}
	}

	public void delete(int index) {
		if (index < 0 || size() - 1 < index) { // Out of bounds
			throw new IndexOutOfBoundsException();
		} else if (index == 0) { // Lower bound index
			first = first.next;
			first.previous = null;
		} else if (index == size() - 1) { // Upper bound index
			last = last.previous;
			last.next = null;
		} else if (0 < index && index < size() - 1) {
			Item<T> previous = getItem(index).previous;
			Item<T> next = getItem(index).next;
			previous.next = next;
			next.previous = previous;
		} else {
			throw new AssertionError();
		}
	}

	public void clear() {
		first = null;
		last = null;
	}

	public int size() {
		int count = 0;
		Item<T> current = first;
		while (current != null) {
			current = current.next;
			count++;
		}
		return count;
	}

	public static class Item<T> {
		public Item<T> previous;
		public Item<T> next;
		public T value;

		public Item(Item<T> previous, Item<T> next, T value) {
			this.previous = previous;
			this.next = next;
			this.value = value;
		}
	}

	@Override
	public LinkedList<T> clone() {
		LinkedList<T> cloneList = new LinkedList<T>();
		for (int i = 0; i < size(); i++) {
			cloneList.add(get(i));
		}
		return cloneList;
	}
}
