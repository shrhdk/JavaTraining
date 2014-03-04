package ex14_04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SumTest {
	private static final int REPEAT_COUNT = 1000;
	private static final int DIFF_A = 2;
	private static final int DIFF_B = 3;

	@Test
	public void callAddFromMultiThread() {

		Thread threadA = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < REPEAT_COUNT; i++)
					Sum.add(DIFF_A);
			}
		};
		Thread threadB = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < REPEAT_COUNT; i++)
					Sum.add(DIFF_B);
			}
		};

		threadA.start();
		threadB.start();

		while (threadA.isAlive() || threadB.isAlive())
			;

		assertThat(Sum.add(0), is((DIFF_A + DIFF_B) * REPEAT_COUNT));
	}
}
