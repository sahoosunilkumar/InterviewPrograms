package com.sunilsahoo.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


public class ThreadPoolUsingExecutor2 {

	private static final int NTHREDS = 3;

	public static void main(String[] args) {
		
		// Creating shared object
				BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>();

				Producer1 producer = new Producer1(sharedQueue);
				Consumer1 consumer = new Consumer1(sharedQueue);

				Thread producerThread = new Thread(producer, "Producer1Thread");
				Thread consumerThread = new Thread(consumer, "Consumer1Thread");
				producerThread.start();
				consumerThread.start();
				
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		while(!sharedQueue.isEmpty()){
			executor.execute(consumer);
		}
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		// executor.awaitTermination();
		System.out.println("Finished all threads");
	}
}

/**
 * Consumer1 Class.
 */
class Consumer1 implements Runnable {

	private BlockingQueue<Integer> sharedQueue;

	public Consumer1(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// take/consume from sharedQueue.
				System.out.println("CONSUMED : " + sharedQueue.take());
			} catch (InterruptedException ex) {

			}
		}
	}

}

class Producer1 implements Runnable {

	private final BlockingQueue<Integer> sharedQueue;

	public Producer1(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			try {
				System.out.println("Produced : " + i);
				// put/produce into sharedQueue.
				sharedQueue.put(i);
			} catch (InterruptedException ex) {

			}
		}
	}

}