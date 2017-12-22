package com.sunilsahoo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUsingExecutor {

	private static final int NTHREDS = 2;

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		for (int i = 0; i < 50; i++) {
			Runnable worker = new MyRunnable(100L + i);
			executor.execute(worker);
		}
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		// executor.shutdown();
		// Wait until all threads are finish
		// executor.awaitTermination();
		System.out.println("Finished all threads");
	}
}

class MyRunnable implements Runnable {
	private final long countUntil;

	MyRunnable(long countUntil) {
		this.countUntil = countUntil;
	}

	@Override
	public void run() {
		long sum = 0;
		for (long i = 1; i < countUntil; i++) {
			sum += i;
		}
		System.out.println(
				"Thread Name :" + Thread.currentThread() + " sum :" + sum);
	}
}