package ex14_02;

import java.util.LinkedList;
import java.util.Queue;

class PrintServer implements Runnable {

	private final Thread workerThread;
	private final Queue<String> requests = new LinkedList<String>();

	public PrintServer() {
		workerThread = new Thread(this);
		workerThread.start();
	}

	public void print(String job) {
		if (job == null) {
			throw new IllegalArgumentException("Print job can't be null.");
		}
		requests.offer(job);
	}

	@Override
	public void run() {
		if (Thread.currentThread() != workerThread)
			throw new UnsupportedOperationException();

		for (;;)
			if (1 <= requests.size())
				realPrint(requests.remove());
	}

	private void realPrint(String job) {
		System.out.print(job);
	}

	public static void main(String[] args) {
		PrintServer printServer = new PrintServer();

		printServer.print("hoge\n");
		printServer.print("fuga\n");
		printServer.print("piyo\n");
	}

}
