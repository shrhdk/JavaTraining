package jpl.chapter1.exercise15;

public class ObjectCollection implements ObjectCollectionInterface {

	private final int size;
	private String[] names;
	private Object[] values;

	public ObjectCollection(final int size) {
		this.size = size;
		this.names = new String[this.size];
		this.values = new Object[this.size];
	}

	@Override
	public Object find(String name) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("name can't be null");
		}

		Object value = null;
		for (int i = 0; i < size; i++) {
			if (names[i] != null && names[i].equals(name)) {
				value = values[i];
				break;
			}
		}
		return value;
	}

	@Override
	public boolean remove(String name) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("name can't be null");
		}

		for (int i = 0; i < size; i++) {
			if (names[i] != null && names[i].equals(name)) {
				names[i] = null;
				values[i] = null;
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean add(String name, Object value)
			throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("name can't be null");
		}

		for (int i = 0; i < size; i++) {
			if (names[i] != null && names[i].equals(name)) {
				values[i] = value;
				return true;
			}
		}

		for (int i = 0; i < size; i++) {
			if (names[i] == null) {
				names[i] = name;
				values[i] = value;
				return true;
			}
		}

		return false;
	}

}
