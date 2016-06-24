package com.sunilsahoo.thread;

class ThreadDeathExample {
	public static void main(String[] args) {
		MyThreadGroup mtg = new MyThreadGroup("My Group");
		new MyThread(mtg, "My Thread").start();
	}
}

class MyThread extends Thread {
	MyThread(ThreadGroup tg, String name) {
		super(tg, name);
	}

	public void run() {
		System.out.println("About to do something.");
		doSomething();
		System.out.println("Something done.");
	}

	void doSomething() {
		doSomethingHelper();
	}

	void doSomethingHelper() {
		throw new MyThreadDeath(MyThreadDeath.REASON2);
	}
}

class MyThreadDeath extends ThreadDeath {
	final static int REASON1 = 1;
	final static int REASON2 = 2;
	final static int REASON3 = 3;

	int reason;

	MyThreadDeath(int reason) {
		this.reason = reason;
	}
}

class MyThreadGroup extends ThreadGroup {
	MyThreadGroup(String name) {
		super(name);
	}

	public void uncaughtException(Thread t, Throwable e) {
		if (e instanceof MyThreadDeath) {
			reportError(t, e);
			cleanup();
		}

		super.uncaughtException(t, e);
	}

	void reportError(Thread t, Throwable e) {
		System.out.print(t.getName() + " unable to do something. Reason: ");

		switch (((MyThreadDeath) e).reason) {
		case MyThreadDeath.REASON1:
			System.out.println("First reason.");
			break;

		case MyThreadDeath.REASON2:
			System.out.println("Second reason.");
			break;

		case MyThreadDeath.REASON3:
			System.out.println("Third reason.");
		}
	}

	void cleanup() {
		System.out.println("Cleaning up");
	}
}
