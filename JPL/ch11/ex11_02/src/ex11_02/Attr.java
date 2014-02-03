package ex11_02;

public class Attr<T> {

	private final String name;
	private T value = null;

	public Attr(String name, T value) {
		if(name == null || name.equals(""))
			throw new IllegalArgumentException("name can't be null or empty.");
		
		this.name = name;
		this.value = value;
	}

	public Attr(String name) {
		this(name, null);
	}

	public String getName() {
		return name;
	}

	public T getValue() {
		return value;
	}

	public T setValue(T newValue) {
		T oldValue = value;
		value = newValue;
		return oldValue;
	}

	public String toString() {
		return name + "='" + value + "'";
	}
}
