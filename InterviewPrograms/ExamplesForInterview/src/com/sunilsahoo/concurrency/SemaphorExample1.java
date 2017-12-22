package com.sunilsahoo.concurrency;

import java.util.concurrent.Semaphore;

public class SemaphorExample1 {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(4);
		SendingThread st = new SendingThread(semaphore);
		RecevingThread rt = new RecevingThread(semaphore);
		st.start();
		rt.start();
	}
}

class SendingThread extends Thread {
	Semaphore semaphore = null;

	public SendingThread(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("inside sending thread");
			// do something, then signal
			try {
				this.semaphore.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class RecevingThread extends Thread {
	Semaphore semaphore = null;

	public RecevingThread(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		while (true) {
			this.semaphore.release();
			System.out.println("inside receiving thread");
			// receive signal, then do something...
		}
	}
}