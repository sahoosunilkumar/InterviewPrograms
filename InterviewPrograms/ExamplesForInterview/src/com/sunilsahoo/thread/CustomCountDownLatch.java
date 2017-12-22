package com.sunilsahoo.thread;

public class CustomCountDownLatch {
	public static void main(String args[]) {
		CountDown countDown = new CountDown(3);
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Waiting");
				countDown.waitNow();
				System.out.println("Wait completed");
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countDown.update();

			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countDown.update();

			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				countDown.update();

			}
		}).start();
	}
}

class CountDown {
	int maxCountDown;

	CountDown(int maxCountDown) {
		this.maxCountDown = maxCountDown;
	}

	public synchronized void update() {
		--this.maxCountDown;
		System.out.println("countdown after updating : " + this.maxCountDown);
		if (maxCountDown <= 0) {
			notify();
		}
	}

	public synchronized void waitNow() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
