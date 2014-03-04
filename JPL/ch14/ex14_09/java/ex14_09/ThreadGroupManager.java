package ex14_09;

import java.io.PrintStream;

public class ThreadGroupManager {

	private PrintStream out = System.out;
	private boolean stopped;
	private int depth = 0;

	public synchronized void setOutputStream(PrintStream outputStream) {
		out = outputStream;
	}

	public synchronized void printThreadInformation(final ThreadGroup group, final int period) {
		new Thread() {
			@Override
			public void run() {
				for (;;) {
					while (!stopped) {
						printThreadRecursively(group, 0);
						out.println();
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}.start();
	}

	public synchronized void stop() {
		stopped = true;
	}

	private void printThreadRecursively(ThreadGroup group, int depth) {

		int threadCount = group.activeCount();
		int groupCount = group.activeGroupCount();

		// Print ThreadGroup
		out.println(getTabs(depth) + "+" + group.getName());

		// Print Thread
		Thread[] threads = new Thread[threadCount];
		group.enumerate(threads, false);
		for (Thread thread : threads) {
			if (thread != null)
				out.println(getTabs(depth + 1) + "-" + thread.getName());
		}

		// Recursive call
		ThreadGroup[] threadGroups = new ThreadGroup[groupCount];
		group.enumerate(threadGroups, false);
		for (ThreadGroup threadGroup : threadGroups) {
			if (threadGroup != null)
				printThreadRecursively(threadGroup, depth + 1);
		}
	}

	private String getTabs(int width) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < width; i++)
			buffer.append("\t");
		return buffer.toString();
	}
}
