package com.sunilsahoo.thread;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementing custom BlockingQueue interface . This BlockingQueue
 * implementation follows FIFO (first-in-first-out). New elements are inserted
 * at the tail of the queue, and removal elements is done at the head of the
 * queue.
 *
 * @author AnkitMittal Copyright (c), AnkitMittal . All Contents are copyrighted
 *         and must not be reproduced in any form.
 */
interface BlockingQueueCustom<E> {

	/**
	 * Inserts the specified element into this queue only if space is available
	 * else waits for space to become available.
	 */
	void put(E item) throws InterruptedException;

	/**
	 * Retrieves and removes the head of this queue only if elements are
	 * available else waits for element to become available.
	 */
	E take() throws InterruptedException;

	/**
	 * Returns size of queue.
	 */
	int size();

}

/**
 * Implementing custom LinkedBlockingQueue class. This BlockingQueue
 * implementation follows FIFO (first-in-first-out). New elements are inserted
 * at the tail of the queue, and removal elements is done at the head of the
 * queue.
 *
 * @author AnkitMittal Copyright (c), AnkitMittal . All Contents are copyrighted
 *         and must not be reproduced in any form.
 */
class LinkedBlockingQueueCustom<E> implements BlockingQueueCustom<E> {

	private List<E> queue;
	private int maxSize; // maximum number of elements queue can hold at a time.

	public LinkedBlockingQueueCustom(int maxSize) {
		this.maxSize = maxSize;
		queue = new LinkedList<E>();
	}

	/**
	 * Inserts the specified element into this queue only if space is available
	 * else waits for space to become available. After inserting element it
	 * notifies all waiting threads.
	 */
	public synchronized void put(E item) throws InterruptedException {

		// check space is available or not.
		if (queue.size() == maxSize) {
			this.wait();
		}

		// space is available, insert element and notify all waiting threads.
		queue.add(item);
		this.notifyAll();
	}

	/**
	 * Retrieves and removes the head of this queue only if elements are
	 * available else waits for element to become available. After removing
	 * element it notifies all waiting threads.
	 */
	public synchronized E take() throws InterruptedException {

		// waits element is available or not.
		if (queue.size() == 0) {
			this.wait();
		}

		// element is available, remove element and notify all waiting threads.
		this.notifyAll();
		return queue.remove(0);

	}

	/**
	 * Returns size of LinkedBlockingQueueCustom.
	 */
	public synchronized int size() {
		return queue.size();
	}

}

/**
 * ThreadPool10 is a class which creates a thread pool that reuses a fixed
 * number of threads to execute tasks. At any point, at most nThreads threads
 * will be active processing tasks. If additional tasks are submitted when all
 * threads are active, they will wait in the queue until a thread is available.
 *
 * Once shutdown of ThreadPool10 is initiated, previously submitted tasks are
 * executed, but no new tasks will be accepted.
 *
 * @author AnkitMittal Copyright (c), AnkitMittal .JavaMadeSoEasy.com All
 *         Contents are copyrighted and must not be reproduced in any form.
 */
class ThreadPool10 {

	private BlockingQueueCustom<Runnable> taskQueue;

	/*
	 * Once pool shutDown will be initiated, poolShutDownInitiated will become
	 * true.
	 */
	private boolean poolShutDownInitiated = false;

	/*
	 * Constructor of ThreadPool10 nThreads= is a number of threads that exist
	 * in ThreadPool10. nThreads number of threads are created and started. *
	 */
	public ThreadPool10(int nThreads) {
		taskQueue = new LinkedBlockingQueueCustom<Runnable>(nThreads);

		// Create and start nThreads number of threads.
		for (int i = 1; i <= nThreads; i++) {
			ThreadPool10sThread threadPoolsThread = new ThreadPool10sThread(
					taskQueue, this);
			threadPoolsThread.setName("Thread-" + i);
			System.out.println("Thread-" + i + " created in ThreadPool10.");
			threadPoolsThread.start(); // start thread
		}

	}

	/**
	 * Execute the task, task must be of Runnable type.
	 */
	public synchronized void execute(Runnable task) throws Exception {
		if (this.poolShutDownInitiated)
			throw new Exception(
					"ThreadPool10 has been shutDown, no further tasks can be added");

		/*
		 * Add task in sharedQueue, and notify all waiting threads that task is
		 * available.
		 */
		System.out.println("task has been added.");
		this.taskQueue.put(task);
	}

	public boolean isPoolShutDownInitiated() {
		return poolShutDownInitiated;
	}

	/**
	 * Initiates shutdown of ThreadPool10, previously submitted tasks are
	 * executed, but no new tasks will be accepted.
	 */
	public synchronized void shutdown() {
		this.poolShutDownInitiated = true;
		System.out.println("ThreadPool10 SHUTDOWN initiated.");
	}

}

/**
 * These threads are created and started from constructor of ThreadPool10 class.
 */
class ThreadPool10sThread extends Thread {

	private BlockingQueueCustom<Runnable> taskQueue;
	private ThreadPool10 threadPool;

	public ThreadPool10sThread(BlockingQueueCustom<Runnable> queue,
			ThreadPool10 threadPool) {
		taskQueue = queue;
		this.threadPool = threadPool;

	}

	public void run() {
		try {
			/*
			 * ThreadPool10's threads will keep on running until ThreadPool10 is
			 * not shutDown (shutDown will interrupt thread) and taskQueue
			 * contains some unExecuted tasks.
			 */
			while (true) {
				System.out.println(Thread.currentThread().getName()
						+ " is READY to execute task.");
				/*
				 * ThreadPool10's thread will take() task from sharedQueue only
				 * if tasks are available else waits for tasks to become
				 * available.
				 */
				Runnable runnable = taskQueue.take();
				System.out.println(
						Thread.currentThread().getName() + " has taken task.");
				// Now, execute task with current thread.
				runnable.run();

				System.out.println(Thread.currentThread().getName()
						+ " has EXECUTED task.");

				/*
				 * 1) Check whether pool shutDown has been initiated or not, if
				 * pool shutDown has been initiated and 2) taskQueue does not
				 * contain any unExecuted task (i.e. taskQueue's size is 0 )
				 * than interrupt() the thread.
				 */
				if (this.threadPool.isPoolShutDownInitiated()
						&& this.taskQueue.size() == 0) {
					this.interrupt();
					/*
					 * Interrupting basically sends a message to the thread
					 * indicating it has been interrupted but it doesn't cause a
					 * thread to stop immediately,
					 * 
					 * if sleep is called, thread immediately throws
					 * InterruptedException
					 */
					Thread.sleep(1);
				}
			}
		} catch (Exception e) {
			System.out.println(
					Thread.currentThread().getName() + " has been STOPPED.");
		}
	}
}

/**
 * Task10 class which implements Runnable.
 */
class Task10 implements Runnable {
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println(
					Thread.currentThread().getName() + " is executing task.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
};

/**
 * Test ThreadPool10.
 */
public class ThreadPoolTest {
	public static void main(String[] args) throws Exception {
		ThreadPool10 threadPool = new ThreadPool10(2); // create 2 threads in
														// ThreadPool10
		Runnable task = new Task10();
		threadPool.execute(task);
		threadPool.execute(task);

		threadPool.shutdown();

	}

}
