package com.sunilsahoo.thread;

/**
 * In deadlock, two threads are waiting for each other to release locks. In
 * nested monitor lockout, Thread 1 is holding a lock A, and waits for a signal
 * from Thread 2. Thread 2 needs the lock A to send the signal to Thread 1.
 * 
 * The result of nested monitor lockout and deadlock are pretty much the same:
 * The threads involved end up blocked forever waiting for each other. The two
 * situations are not equal though. As explained in the text on Deadlock a
 * deadlock occurs when two threads obtain locks in different order. Thread 1
 * locks A, waits for B. Thread 2 has locked B, and now waits for A. As
 * explained in the text on Deadlock Prevention deadlocks can be avoided by
 * always locking the locks in the same order (Lock Ordering). However, a nested
 * monitor lockout occurs exactly by two threads taking the locks in the same
 * order. Thread 1 locks A and B, then releases B and waits for a signal from
 * Thread 2. Thread 2 needs both A and B to send Thread 1 the signal. So, one
 * thread is waiting for a signal, and another for a lock to be released.
 * 
 * Nested monitor lockout is a problem similar to deadlock. A nested monitor
 * lockout occurs like this: Thread 1 synchronizes on A Thread 1 synchronizes on
 * B (while synchronized on A) Thread 1 decides to wait for a signal from
 * another thread before continuing Thread 1 calls B.wait() thereby releasing
 * the lock on B, but not A. Thread 2 needs to lock both A and B (in that
 * sequence) to send Thread 1 the signal. Thread 2 cannot lock A, since Thread 1
 * still holds the lock on A. Thread 2 remain blocked indefinately waiting for
 * Thread1 to release the lock on A Thread 1 remain blocked indefinately waiting
 * for the signal from Thread 2, thereby never releasing the lock on A, that
 * must be released to make it possible for Thread 2 to send the signal to
 * Thread 1, etc.
 * 
 * 
 * Notice how the lock() method first synchronizes on "this", then synchronizes
 * on the monitorObject member. If isLocked is false there is no problem. The
 * thread does not call monitorObject.wait(). If isLocked is true however, the
 * thread calling lock() is parked waiting in the monitorObject.wait() call. The
 * problem with this is, that the call to monitorObject.wait() only releases the
 * synchronization monitor on the monitorObject member, and not the
 * synchronization monitor associated with "this". In other words, the thread
 * that was just parked waiting is still holding the synchronization lock on
 * "this". When the thread that locked the Lock in the first place tries to
 * unlock it by calling unlock() it will be blocked trying to enter the
 * synchronized(this) block in the unlock() method. It will remain blocked until
 * the thread waiting in lock() leaves the synchronized(this) block. But the
 * thread waiting in the lock() method will not leave that block until the
 * isLocked is set to false, and a monitorObject.notify() is executed, as it
 * happens in unlock(). Put shortly, the thread waiting in lock() needs an
 * unlock() call to execute successfully for it to exit lock() and the
 * synchronized blocks inside it. But, no thread can actually execute unlock()
 * until the thread waiting in lock() leaves the outer synchronized block. This
 * result is that any thread calling either lock() or unlock() will become
 * blocked indefinately. This is called a nested monitor lockout.
 * 
 * @author sunilkumarsahoo
 *
 */
public class NestedMonitorLockout {
	public static void main(String[] args) {
		Lock lock = new Lock();
		lock.isLocked = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					lock.lock();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				lock.unlock();
			}
		}).start();
	}
}

class Lock {
	protected MonitorObject monitorObject = new MonitorObject();
	protected boolean isLocked = false;

	public void lock() throws InterruptedException {
		synchronized (this) {
			System.out.println("inside lock : " + this.getClass().getName());
			while (isLocked) {
				synchronized (this.monitorObject) {
					System.out.println("waiting at monitorObject");
					this.monitorObject.wait();
				}
			}
			isLocked = true;
		}
	}

	public void unlock() {
		synchronized (this) {
			System.out.println("inside unlock : " + this.getClass().getName());
			this.isLocked = false;
			synchronized (this.monitorObject) {
				System.out.println("notified monitorObject");
				this.monitorObject.notify();
			}
		}
	}
}

class MonitorObject {

}