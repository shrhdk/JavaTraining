package jpl.chapter3.exercise5;

import java.util.ArrayList;
import java.util.Collections;

public class SortBenchmark extends Benchmark {

	private static int listSize = 0;
	private static int count = 0;

	@Override
	protected void benchmark() {
		ArrayList<Integer> list = new ArrayList<Integer>(listSize);
		for (int i = 0; i < listSize; i++) {
			list.add((int) (Math.random() * listSize));
		}
		Collections.sort(list);
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException(
					"Require either of two arguments. First argument is length of list to sort, and second argument is repeat count.");
		}

		listSize = Integer.parseInt(args[0]);
		count = Integer.parseInt(args[1]);
		SortBenchmark sortBenchmark = new SortBenchmark();
		long result = sortBenchmark.repeat(count);
		String message = String
				.format("%,3d random numbers (0~%,3d) were sorted %,3d times in %,3d nsec.",
						listSize, listSize, count, result);
		System.out.println(message);
	}

}
