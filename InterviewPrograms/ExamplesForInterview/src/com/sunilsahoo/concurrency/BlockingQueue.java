package com.sunilsahoo.concurrency;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 
 * 
 * A blocking queue is a queue that blocks when you try to dequeue from it and
 * the queue is empty, or if you try to enqueue items to it and the queue is
 * already full. A thread trying to dequeue from an empty queue is blocked until
 * some other thread inserts an item into the queue. A thread trying to enqueue
 * an item in a full queue is blocked until some other thread makes space in the
 * queue, either by dequeuing one or more items or clearing the queue
 * completely.
 * 
 * @author sunilkumarsahoo
 */
public class BlockingQueue {
	private List<Object> queue = new LinkedList<>();
	private int size = 10;

	public synchronized void enqueue(Object item) throws InterruptedException {
		if (queue.size() == size) {
			wait();
		} else {
			notifyAll();
		}
		queue.add(item);
	}

	public synchronized Object dequeue() throws InterruptedException {
		if (queue.size() == 0) {
			wait();
		} else {
			notifyAll();
		}
		return queue.remove(0);
	}

	public synchronized Object get(int position) throws InterruptedException {
		return queue.get(position);
	}

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue queue = new BlockingQueue();
		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
		}

		for (int i = 0; i < queue.size; i++) {
			System.out.print(queue.get(i) + " ");
		}

	}
}
