/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 */

package ex14_10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple Thread Pool class.
 * 
 * This class can be used to dispatch an Runnable object to be exectued by a
 * thread.
 * 
 * [Instruction] Implement one constructor and three methods. Don't forget to
 * write a Test program to test this class. Pay attention to @throws tags in the
 * javadoc. If needed, you can put "synchronized" keyword to methods. All
 * classes for implementation must be private inside this class. Don't use
 * java.util.concurrent package.
 */
public class ThreadPool {

	private static final int PERIOD = 100;

	private final ThreadGroup group = new ThreadGroup("ThreadLoop");
	private final Queue<Runnable> queue = new LinkedList<Runnable>();

	private final int numberOfThreads;
	private final int queueSize;

	private State state = State.IDLE;
	private final Thread runner = new RunnerThread();
    private final DispatchableThread[] pool;

	// Constructor

	/**
	 * Constructs ThreadPool.
	 * 
	 * @param queueSize
	 *            the max size of queue
	 * @param numberOfThreads
	 *            the number of threads in this pool.
	 * 
	 * @throws IllegalArgumentException
	 *             if either queueSize or numberOfThreads is less than 1
	 */
	public ThreadPool(int queueSize, int numberOfThreads) {
		if (queueSize < 1) {
			throw new IllegalArgumentException();
		} else if (numberOfThreads < 1) {
			throw new IllegalArgumentException();
		}

		this.numberOfThreads = numberOfThreads;
		this.queueSize = queueSize;
        this.pool = new DispatchableThread[numberOfThreads];
        for(int i = 0; i < pool.length; i++) {
            pool[i] = new DispatchableThread(group);
            pool[i].start();
        }

		runner.start();
	}

	// API

	/**
	 * Starts threads.
	 * 
	 * @throws IllegalStateException
	 *             if threads has been already started.
	 */
	public synchronized void start() {
		assert state != null;
		switch (state) {
		case IDLE:
			state = State.RUNNING;
			break;
		case RUNNING:
			throw new IllegalStateException();
		}
	}

	/**
	 * Stop all threads and wait for their terminations.
	 * 
	 * @throws IllegalStateException
	 *             if threads has not been started.
	 */
	public synchronized void stop() {
		assert state != null;
		switch (state) {
		case IDLE:
			throw new IllegalStateException();
		case RUNNING:
            stopSynchronously();
			state = State.IDLE;
			break;
		}
	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool. run()
	 * method will be invoked in the thread. If the queue is full, then this
	 * method invocation will be blocked until the queue is not full.
	 * 
	 * @param runnable
	 *            Runnable object whose run() method will be invoked.
	 * 
	 * @throws NullPointerException
	 *             if runnable is null.
	 * @throws IllegalStateException
	 *             if this pool has not been started yet.
	 */
	public synchronized void dispatch(Runnable runnable) {
		if (runnable == null)
			throw new NullPointerException();

		assert state != null;
		switch (state) {
		case IDLE:
			throw new IllegalStateException();
		case RUNNING:
			offer(runnable);
			break;
		}
	}

	// Private utility Method

	private synchronized void offer(Runnable runnable) {
		while (queueSize <= queue.size()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		queue.offer(runnable);
		notifyAll();
	}

	private synchronized Runnable poll() {
		while (queue.size() < 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				return null;
			}
		}

		Runnable runnable = queue.poll();
		notifyAll();
		return runnable;
	}

    private synchronized void dispatchToThread(Runnable runnable) {
        for(DispatchableThread thread : pool) {
            if(thread.isAvailable()) {
                thread.dispatch(runnable);
                break;
            }
        }
    }

    private synchronized  void stopSynchronously() {
        group.interrupt();

        while (1 <= queue.size() || 1 <= group.activeCount()) {
            try {
                wait(PERIOD);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

	// Runner Thread

	private class RunnerThread extends Thread {

        {
            setName("RunnerThread");
        }

		@Override
		public void run() {
			synchronized (ThreadPool.this) {
				for (;;) {
					// Wait for state
					while (state != ThreadPool.State.RUNNING) {
						try {
							ThreadPool.this.wait();
						} catch (InterruptedException e) {
							return;
						}
					}

					// Dispatch to thread
					Runnable task = poll();
                    if(task != null)
					    dispatchToThread(task);

                    try {
                        ThreadPool.this.wait(PERIOD);
                    } catch (InterruptedException e) {
                        // No problem
                    }
                }
			}
		}
	}

	// Internal State

	private enum State {
		IDLE, RUNNING
	}
}

class DispatchableThread extends Thread {

    private volatile boolean isInterrupted = false;
	private volatile Runnable runnable;

    public DispatchableThread(ThreadGroup group) {
        super(group, "");
    }

	public synchronized void dispatch(Runnable runnable) {
		this.runnable = runnable;
        notifyAll();
	}

    public boolean isAvailable() {
        return runnable == null;
    }

    @Override
    public void interrupt() {
        isInterrupted = true;
    }

	@Override
	public synchronized void run() {
        do {
            while(runnable == null) {
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    return;
                }
                if(isInterrupted) {
                    return;
                }
            }
            runnable.run();
            runnable = null;
        } while(!isInterrupted);
	}
}
