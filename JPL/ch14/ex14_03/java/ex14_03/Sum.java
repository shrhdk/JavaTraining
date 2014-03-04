package ex14_03;

public class Sum {
	private int sum = 0;
	public synchronized int add(int value) {
		sum += value;
		return sum;
	}
}
