package com.sunilsahoo.programs;

import java.util.concurrent.CountDownLatch;

public class CountdownTest {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(5);
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println("running latch");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					latch.countDown();
				}
			}).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("task completed");

	}
}
