package ex14_09;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class ThreadGroupManagerTest {

	@Test
	public void test() throws InterruptedException {
		ThreadGroup group1 = new ThreadGroup("group1");
		DummyThread thread1 = new DummyThread(group1, "thread1");
		DummyThread thread2 = new DummyThread(group1, "thread2");
		DummyThread thread3 = new DummyThread(group1, "thread3");
		ThreadGroup group2 = new ThreadGroup(group1, "group2");
		DummyThread thread4 = new DummyThread(group2, "thread4");
		DummyThread thread5 = new DummyThread(group2, "thread5");
		ThreadGroup group3 = new ThreadGroup(group1, "group3");
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		
		ThreadGroupManager tgm = new ThreadGroupManager();
		tgm.printThreadInformation(group1, 1000);
		
		Thread.sleep(500);
		
		thread1.kill();
		Thread.sleep(1000);
		
		thread2.kill();
		Thread.sleep(1000);
		
		thread3.kill();
		Thread.sleep(1000);
		
		thread4.kill();
		Thread.sleep(1000);
		
		thread5.kill();
		Thread.sleep(1000);
		
		tgm.stop();
	}

	@Test
	public void testDummyThread() {
		DummyThread thread = new DummyThread();
		thread.start();
		assertThat(thread.isAlive(), is(true));
		thread.kill();
		assertThat(thread.isAlive(), is(false));
	}

	private static class DummyThread extends Thread {

		public DummyThread() {
		}

		public DummyThread(ThreadGroup group, String name) {
			super(group, name);
		}

		private boolean killed = false;

		public synchronized boolean kill() {
			killed = true;
			this.interrupt();
			while (isAlive()) {
				try {
					wait();
				} catch (InterruptedException e) {
					return !isAlive();
				}
			}

			return !isAlive();
		}

		@Override
		public synchronized void run() {
			while (!killed) {
				try {
					wait();
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

}
