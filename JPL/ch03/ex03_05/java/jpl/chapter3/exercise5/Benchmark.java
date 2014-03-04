package jpl.chapter3.exercise5;

public abstract class Benchmark {
	protected abstract void benchmark();

	public final long repeat(int count) {
		long start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			benchmark();
		}
		return System.nanoTime() - start;
	}
}
