package com.sunilsahoo.thread;

import java.util.LinkedList;

public class ThreadCommunication {

	public static void main(String[] args) {
		Data data = new Data();
		Thread t1 = new Thread(new CommWorkerThread(data, true));
		Thread t2 = new Thread(new CommWorkerThread(data, false));
		t1.start();
		t2.start();
	}

}

class Data {
	boolean isOdd = false;
	public LinkedList<Integer> list1 = new LinkedList<>();
	public LinkedList<Integer> list2 = new LinkedList<>();

	Data() {
		for (int i = 1; i <= 20; i++) {
			if (i % 2 == 0) {
				list1.add(i);
			} else {
				list2.add(i);
			}
		}

	}

	synchronized public boolean readEven() {
		if (list1.isEmpty()) {
			return false;
		}
		System.out.println("inside readEven");
		if (isOdd == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isOdd = false;
		System.out.println("after wait1");
		System.out.println(
				Thread.currentThread().getName() + " counter " + list1.poll());
		notifyAll();
		System.out.println("after notify1");
		return true;
	}

	synchronized public boolean readOdd() {

		System.out.println("inside readOdd");
		if (list2.isEmpty()) {
			return false;
		}
		if (isOdd == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isOdd = true;
		System.out.println("after wait1");
		System.out.println(
				Thread.currentThread().getName() + " counter " + list2.poll());
		notifyAll();
		System.out.println("after notify2");
		return true;
	}
}

class CommWorkerThread implements Runnable {

	Data data;
	boolean isEven;
	boolean hasMoreEven = true;
	boolean hasMoreOdd = true;

	CommWorkerThread(Data data, boolean isEven) {
		this.data = data;
		this.isEven = isEven;
	}

	@Override
	public void run() {
		while (true) {
			if (isEven) {
				if (hasMoreEven)
					hasMoreEven = data.readEven();
			} else {
				if (hasMoreOdd)
					hasMoreOdd = data.readOdd();
			}
		}
	}
}
