package ex14_08;

import org.junit.Test;
import org.junit.AfterClass;

public class FriendlyTest {

	private static final int REPEAT_COUNT = 1000;
	
	private static int withYield = 0;
	private static int withoutYield = 0;

	private Thread thread1;
	private Thread thread2;

	private void setup(boolean doYield) {
		final Friendly jareth = new Friendly("jareth", doYield);
		final Friendly cory = new Friendly("cory", doYield);

		jareth.becomeFriend(cory);
		cory.becomeFriend(jareth);

		thread1 = new Thread(new Runnable() {
			public void run() {
				jareth.hug();
			}
		});

		thread2 = new Thread(new Runnable() {
			public void run() {
				cory.hug();
			}
		});
	}

	@Test
	public void testWithYield() throws InterruptedException {
		int deadLock = 0;
		for (int i = 0; i < REPEAT_COUNT; i++) {
			setup(true);
			thread1.start();
			thread2.start();

			Thread.sleep(50);
			if(thread1.isAlive() && thread2.isAlive()) {
				deadLock++;
			}
		}
		
		withYield += deadLock;
	}

	@Test
	public void testWithoutYield() throws InterruptedException {
		int deadLock = 0;
		for (int i = 0; i < REPEAT_COUNT; i++) {
			setup(false);
			thread1.start();
			thread2.start();

			Thread.sleep(50);
			if(thread1.isAlive() && thread2.isAlive()) {
				deadLock++;
			}
		}
		
		withoutYield += deadLock;
	}

	@AfterClass
	public static void result() {
		System.out.printf("With    Yield -> Dead lock: %d / %d\n", withYield, REPEAT_COUNT);
		System.out.printf("Without Yield -> Dead lock: %d / %d\n", withoutYield, REPEAT_COUNT);
	}
}
