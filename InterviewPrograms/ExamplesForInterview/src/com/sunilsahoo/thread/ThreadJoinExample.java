package com.sunilsahoo.thread;

public class ThreadJoinExample {
	public static void main(String[] args) {
		Thread t1 = new Thread(new LoopRunnable());
		Thread t2 = new Thread(new LoopRunnable());
		Thread t3 = new Thread(new LoopRunnable());
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		t2.start();
		t3.start();
	}

}

class LoopRunnable implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(
					Thread.currentThread().getName() + " counter " + i);
		}
	}
}
