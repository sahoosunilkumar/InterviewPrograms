package com.sunilsahoo.concurrency;

/**
 * Some Scenario where Semaphore can be used:
1) To implement better Database connection pool which will block if no more connection is available instead of failing and handover Connection as soon as its available.

2) To put a bound on collection classes. by using semaphore you can implement bounded collection whose bound is specified by counting semaphore.

That's all on Counting semaphore example in Java. Semaphore is real nice concurrent utility which can greatly simply design and implementation of bounded resource pool. Java 5 has added several useful  concurrent utility and deserve a better attention than casual look.

Important points of Counting Semaphore in Java

1. Semaphore class supports various overloaded version of tryAquire() method which acquires permit from semaphore only if its available during time of call.

2. Another worth noting method from Semaphore is acquireUninterruptibly() which is a blocking call and wait until a permit is available.



Read more: http://javarevisited.blogspot.com/2012/05/counting-semaphore-example-in-java-5.html#ixzz4COdbTx5y
 */
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

	Semaphore binary = new Semaphore(1);

	public static void main(String args[]) {
		final SemaphoreExample test = new SemaphoreExample();
		new Thread() {
			@Override
			public void run() {
				test.mutualExclusion();
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				test.mutualExclusion();
			}
		}.start();

	}

	private void mutualExclusion() {
		try {
			binary.acquire();

			// mutual exclusive region
			System.out.println(Thread.currentThread().getName()
					+ " inside mutual exclusive region");
			Thread.sleep(1000);

		} catch (InterruptedException ie) {
			ie.printStackTrace();
		} finally {
			binary.release();
			System.out.println(Thread.currentThread().getName()
					+ " outside of mutual exclusive region");
		}
	}

}
