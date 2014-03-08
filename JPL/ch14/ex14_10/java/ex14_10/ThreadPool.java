/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 */

package ex14_10;

import java.util.LinkedList;
import java.util.Queue;

import static ex14_10.LogUtility.logDebug;

/**
 * Simple Thread Pool class.
 * <p/>
 * This class can be used to dispatch an Runnable object to be exectued by a
 * thread.
 * <p/>
 * [Instruction] Implement one constructor and three methods. Don't forget to
 * write a Test program to test this class. Pay attention to @throws tags in the
 * javadoc. If needed, you can put "synchronized" keyword to methods. All
 * classes for implementation must be private inside this class. Don't use
 * java.util.concurrent package.
 */
public class ThreadPool {

    private boolean isInterrupted = false;
    private int numberOfAlive;

    private final int queueSize;
    private final int numberOfThreads;

    private final WorkerThread[] threads;
    private final Queue<Runnable> queue = new LinkedList<Runnable>();

    // State

    private enum State {IDLE, RUNNING, STOPPING}

    private volatile State state = State.IDLE;

    // Constructor

    /**
     * Constructs ThreadPool.
     *
     * @param queueSize       the max size of queue
     * @param numberOfThreads the number of threads in this pool.
     * @throws IllegalArgumentException if either queueSize or numberOfThreads is less than 1
     */
    public ThreadPool(int queueSize, int numberOfThreads) {
        if (queueSize < 1) {
            throw new IllegalArgumentException();
        } else if (numberOfThreads < 1) {
            throw new IllegalArgumentException();
        }

        this.queueSize = queueSize;
        this.numberOfThreads = numberOfThreads;

        threads = new WorkerThread[numberOfThreads];
    }

    // API

    /**
     * Starts threads.
     *
     * @throws IllegalStateException if threads has been already started.
     */
    public synchronized void start() {
        logDebug("Enter");

        if (state != State.IDLE)
            throw new IllegalStateException();

        numberOfAlive = numberOfThreads;

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }

        state = State.RUNNING;

        logDebug("Return");
    }

    /**
     * Stop all threads and wait for their terminations.
     *
     * @throws IllegalStateException if threads has not been started.
     */
    public synchronized void stop() {
        logDebug("Enter");

        if (state != State.RUNNING)
            throw new IllegalStateException();

        state = State.STOPPING;

        // assert numberOfAlive == numberOfThreads : numberOfAlive + "/" + numberOfThreads;

        isInterrupted = true;
        for (int i = 0; i < threads.length; i++) {
            queue.offer(new Runnable() {
                @Override
                public void run() {
                }
            });
        }
        notifyAll();

        while (0 < numberOfAlive) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // assert numberOfAlive == 0 : numberOfAlive + "/" + numberOfThreads;

        state = State.IDLE;

        logDebug("Return");
    }

    /**
     * Executes the specified Runnable object, using a thread in the pool. run()
     * method will be invoked in the thread. If the queue is full, then this
     * method invocation will be blocked until the queue is not full.
     *
     * @param runnable Runnable object whose run() method will be invoked.
     * @throws NullPointerException  if runnable is null.
     * @throws IllegalStateException if this pool has not been started yet.
     */
    public synchronized void dispatch(Runnable runnable) {
        logDebug("Enter");

        if (runnable == null)
            throw new NullPointerException();

        if (state != State.RUNNING)
            throw new IllegalStateException();

        offer(runnable);

        logDebug("Return");
    }

    // Utility Method

    // Private utility Method

    private synchronized void offer(Runnable runnable) {
        logDebug("Enter");

        while (queueSize <= queue.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(runnable);
        notifyAll();

        logDebug("Return");
    }

    private synchronized Runnable poll() {
        logDebug("Enter");

        while (queue.size() < 1) {
            try {
                if (isInterrupted)
                    return null;
                else
                    wait();
            } catch (InterruptedException e) {
                return null;
            }
        }

        Runnable runnable = queue.poll();
        notifyAll();

        logDebug("Return");

        return runnable;
    }

    // Worker Thread Class

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            Runnable runnable;
            while (((runnable = poll()) != null) || !isInterrupted) {
                runnable.run();
                synchronized (ThreadPool.this) {
                    numberOfAlive--;
                    ThreadPool.this.notifyAll();
                }
            }
        }
    }
}
