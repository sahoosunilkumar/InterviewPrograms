package com.sunilsahoo.designpatterns;

/**
 * Type: Behavioral
 * 
 * Intent :
 * • Define an object that encapsulates how a set of objects interact.
 * Mediator promotes loose coupling by keeping objects from referring to each
 * other explicitly, and it lets you vary their interaction independently. •
 * Design an intermediary to decouple many peers. • Promote the many-to-many
 * relationships between interacting peers to "full object status". 
 * Problem :
 * We want to design reusable components, but dependencies between the potentially
 * reusable pieces demonstrates the "spaghetti code" phenomenon (trying to scoop
 * a single serving results in an "all or nothing clump").
 * 
 * @author sunilkumarsahoo
 *
 */
public class MediatorExample {
	public static void main(String[] args) {
		Mediator mb = new Mediator();
		new Producer(mb).start();
		new Producer(mb).start();
		new Consumer(mb).start();
		new Consumer(mb).start();
		new Consumer(mb).start();
		new Consumer(mb).start();
	}
}
//1. The "intermediary"
class Mediator {
	// 4. The Mediator arbitrates
	private boolean slotFull = false;
	private int number;

	public synchronized void storeMessage(int num) {
		// no room for another message
		while (slotFull == true) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		slotFull = true;
		number = num;
		notifyAll();
	}

	public synchronized int retrieveMessage() {
		// no message to retrieve
		while (slotFull == false)
			try {
				wait();
			} catch (InterruptedException e) {
			}
		slotFull = false;
		notifyAll();
		return number;
	}
}

class Producer extends Thread {
	// 2. Producers are coupled only to the Mediator
	private Mediator med;
	private int id;
	private static int num = 1;

	public Producer(Mediator m) {
		med = m;
		id = num++;
	}

	@Override
	public void run() {
		int num;
		while (true) {
			med.storeMessage(num = (int) (Math.random() * 100));
			System.out.print("p" + id + "-" + num + "  ");
		}
	}
}

class Consumer extends Thread {
	// 3. Consumers are coupled only to the Mediator
	private Mediator med;
	private int id;
	private static int num = 1;

	public Consumer(Mediator m) {
		med = m;
		id = num++;
	}

	@Override
	public void run() {
		while (true) {
			System.out.print("c" + id + "-" + med.retrieveMessage() + "  ");
		}
	}
}