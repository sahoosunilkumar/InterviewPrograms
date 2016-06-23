package com.sunilsahoo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class Server {
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				for (int i = 0; i < 10; i++)
					System.out.printf("%s: %d%n", name, i);
			}
		};
		ExecutorService executor;
		executor = Executors.newSingleThreadExecutor(new NamedThread("Foo"));
		executor.execute(r);
		executor.shutdown();
	}
}

class NamedThread implements ThreadFactory {
	private String name;

	NamedThread(String name) {
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, name);
	}
}