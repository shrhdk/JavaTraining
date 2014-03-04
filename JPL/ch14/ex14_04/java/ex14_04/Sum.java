package ex14_04;

public class Sum {
	private static int sum = 0;

	public synchronized static int add(int value) {
		sum += value;
		return sum;
	}
}