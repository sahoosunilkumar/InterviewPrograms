package com.sunilsahoo.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	final static int N = 3;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);
		for (int i = 0; i < N; ++i) // create and start threads
			new Thread(new Worker(startSignal, doneSignal)).start();
		System.out.println("about to let threads proceed");
		startSignal.countDown(); // let all threads proceed
		System.out.println("doing work");
		System.out.println("waiting for threads to finish");
		doneSignal.await(); // wait for all threads to finish
		System.out.println("main thread terminating");
	}
}

class Worker implements Runnable {
	private final static int N = 5;

	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	@Override
	public void run() {
		try {
			String name = Thread.currentThread().getName();
			startSignal.await();
			for (int i = 0; i < N; i++) {
				System.out.printf("thread %s is working%n", name);
				try {
					Thread.sleep((int) (Math.random() * 300));
				} catch (InterruptedException ie) {
				}
			}
			System.out.printf("thread %s finishing%n", name);
			doneSignal.countDown();
		} catch (InterruptedException ie) {
			System.out.println("interrupted");
		}
	}
}