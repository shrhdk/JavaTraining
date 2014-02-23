package ex14_03;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class SumTest {

	private static final int TIMES = 1000;
	private static final int DIFF_A = 2;
	private static final int DIFF_B = 3;

	@Test
	public void callAddFromMultiThread() {

		final Sum sum = new Sum();

		Thread threadA = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < TIMES; i++)
					sum.add(DIFF_A);
			}
		};
		Thread threadB = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < TIMES; i++)
					sum.add(DIFF_B);
			}
		};

		threadA.start();
		threadB.start();

		while (threadA.isAlive() || threadB.isAlive())
			;

		assertThat(sum.add(0), is((DIFF_A + DIFF_B) * TIMES));
	}

}
