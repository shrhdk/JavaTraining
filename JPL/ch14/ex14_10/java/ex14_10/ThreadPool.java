/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 */

package ex14_10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple Thread Pool class.
 * <p/>
 * This class can be used to dispatch an Runnable object to be executed by a thread.
 * <p/>
 * [Instruction] Implement one constructor and three methods. Don't forget to
 * write a Test program to test this class. Pay attention to @throws tags in the
 * javadoc. If needed, you can put "synchronized" keyword to methods. All
 * classes for implementation must be private inside this class. Don't use
 * java.util.concurrent package.
 */
public class ThreadPool {

    private final Object mutexOfIsInterruptedAndQueue = new Object();
    private boolean isInterrupted = false;
    private final Queue<Runnable> queue = new LinkedList<Runnable>();

    private final int queueSize;
    private final WorkerThread[] threads;

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
        threads = new WorkerThread[numberOfThreads];
    }

    // API

    /**
     * Starts threads.
     *
     * @throws IllegalStateException if threads has been already started.
     */
    public void start() {
        if (state != State.IDLE)
            throw new IllegalStateException();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }

        state = State.RUNNING;
    }

    /**
     * Stop all threads and wait for their terminations.
     *
     * @throws IllegalStateException if threads has not been started.
     */
    public void stop() {
        if (state != State.RUNNING)
            throw new IllegalStateException();

        state = State.STOPPING;

        synchronized (mutexOfIsInterruptedAndQueue) {
            isInterrupted = true;
            mutexOfIsInterruptedAndQueue.notifyAll();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        state = State.IDLE;
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
    public void dispatch(Runnable runnable) {
        if (runnable == null)
            throw new NullPointerException();

        if (state != State.RUNNING)
            throw new IllegalStateException();

        offer(runnable);
    }

    // Private utility

    /**
     * Offer the specified Runnable object to queue.
     * method invocation will be blocked until the queue is full.
     *
     * @param runnable
     */
    private void offer(Runnable runnable) {
        synchronized (mutexOfIsInterruptedAndQueue) {
            while (queueSize <= queue.size()) {
                try {
                    mutexOfIsInterruptedAndQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.offer(runnable);
            mutexOfIsInterruptedAndQueue.notifyAll();
        }
    }

    /**
     * Poll the Runnable object from queue.
     * method invocation will be blocked until the queue is empty and is not interrupted.
     * method return null object when stop method is invoked and queue is empty.
     *
     * @return Runnable object or null
     */
    private Runnable poll() {
        synchronized (mutexOfIsInterruptedAndQueue) {
            while (queue.size() < 1) {
                if (isInterrupted) {
                    return null;
                }
                try {
                    mutexOfIsInterruptedAndQueue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }

            Runnable runnable = queue.poll();
            mutexOfIsInterruptedAndQueue.notifyAll();

            return runnable;
        }
    }

    /**
     * Simple Worker Thread class
     */
    private class WorkerThread extends Thread {
        @Override
        public void run() {
            for (; ; ) {
                Runnable runnable;
                synchronized (mutexOfIsInterruptedAndQueue) {
                    runnable = poll();
                    if (runnable == null) {
                        mutexOfIsInterruptedAndQueue.notifyAll();
                        return;
                    }
                }
                runnable.run();
            }
        }
    }
}
