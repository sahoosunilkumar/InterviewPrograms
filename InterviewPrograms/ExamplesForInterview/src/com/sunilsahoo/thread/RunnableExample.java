package com.sunilsahoo.thread;

public class RunnableExample {
	public static void main(String[] args) {
		Thread t = new Thread(new RunnableThread());
		t.start();
	}
}

class RunnableThread implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println(
					"Runnable Thread:" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("Runnable Thread task over:"
					+ Thread.currentThread().getName());

		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
