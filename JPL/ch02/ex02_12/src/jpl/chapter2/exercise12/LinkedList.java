package jpl.chapter2.exercise12;

public class LinkedList {

	private static final String stringForNullValue = "null";
	public Object value = null;
	public LinkedList next = null;

	public static void main(String args[]) {
		System.out.println("Vehicleクラスで可変長メソッドを活用する場面は思いつきません。");
		System.out
				.println("LinkedListクラスでは、可変長メソッドではありませんが、オブジェクトの配列からオブジェクトのリストが作れる可変長コンストラクタがあれば便利です。");
		System.out
				.println("(実行例) new LinkedList(\"A\", \"B\", \"C\", \"D\").toString(): "
						+ new LinkedList("A", "B", "C", "D").toString());
	}

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

	public LinkedList(Object... objects) {
		this(0, objects);
	}

	private LinkedList(int offset, Object... objects) {
		this.value = objects[offset++];
		if (offset < objects.length)
			this.next = new LinkedList(offset, objects);
	}
}
