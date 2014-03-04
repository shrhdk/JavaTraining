package ex14_06;

public class IntervalMessage {
	
	private static final int DEFAULT_UNIT_TIME_MS = 1000;
	
	private final int unitTimeMs;
	private int time = 0;
	
	private IntervalMessage() {
		this(DEFAULT_UNIT_TIME_MS);
	}
	
	private IntervalMessage(int unitTimeMs) {
		this.unitTimeMs = unitTimeMs;
	}
	
	private void start() {
		for (;;) {
			try {
				Thread.sleep(unitTimeMs);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (IntervalMessage.this) {
				time++;
				System.out.println(time);
				notifyAll();
			}
		}
	}
	
	private void addMessage(final String message, final int interval ) {
		new Thread() {
			@Override
			public void run() {
				IntervalMessage.this.printMessage(message, interval);
			}
		}.start();
	}

	private synchronized void printMessage(String message, int interval) {
		int last = 0;
		for (;;) {
			while (time - last < interval) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			last = time;
			System.out.println(message);
		}
	}

	public static void main(String[] args) {
		IntervalMessage intervalMessage = new IntervalMessage(100);
		intervalMessage.addMessage("hello", 7);
		intervalMessage.addMessage("world", 15);
		intervalMessage.start();
	}
}
