package com.sunilsahoo.collection;

/**
 * first-in, first-out (FIFO) principle enqueue(e): Adds element e to the back
 * of queue. dequeue(): Removes and returns the first element from the queue (or
 * null if the queue is empty). The queue ADT also includes the following
 * accessor methods (with first being analogous to the stack’s top method):
 * first(): Returns the first element of the queue, without removing it (or null
 * if the queue is empty). size(): Returns the number of elements in the queue.
 * isEmpty(): Returns a boolean indicating whether the queue is empty.
 * 
 * size() = O(1) isEmpty() = O(1) enqueue() = O(1) dequeue() = O(1) first() =
 * O(1)
 * 
 * Performance of a queue realized by an array. The space usage is O(N), where N
 * is the size of the array, determined at the time the queue is created, and
 * independent from the number n < N of elements that are actually in the queue.
 * 
 * 
 * Our Queue ADT Interface java.util.Queue throws exceptions returns special
 * value enqueue(e) add(e) offer(e) dequeue( ) remove( ) poll() first( )
 * element( ) peek() size( ) size( ) isEmpty( ) isEmpty( )
 */
public class CustomQueueExample {
	public static void main(String[] args) {
		CustomQueueExample customQueueExample = new CustomQueueExample();
		Queue queue = customQueueExample.new Queue(10); // queue holds 10
														// elements

		queue.enqueue(31);
		queue.enqueue(49);

		queue.dequeue();
		queue.dequeue();

		queue.enqueue(90);
		queue.enqueue(81);
		queue.enqueue(72);
		queue.enqueue(22); // At this point we got to deal with wrapAround,
							// because rear must be pointing to last position.

		System.out.print("Deleted elements from queue: ");
		System.out.print(queue.dequeue() + " ");
		System.out.print(queue.dequeue() + " ");
		System.out.print(queue.dequeue() + " ");
		System.out.print(queue.dequeue() + " ");

	}

	/**
	 * Exception to indicate that Queue is full.
	 */
	class QueueFullException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4750980209154910824L;

		public QueueFullException() {
			super();
		}

		public QueueFullException(String message) {
			super(message);
		}

	}

	/**
	 * Exception to indicate that Queue is empty.
	 */
	class QueueEmptyException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5299203054566942362L;

		public QueueEmptyException() {
			super();
		}

		public QueueEmptyException(String message) {
			super(message);
		}

	}

	/**
	 * Queue class
	 */
	class Queue {

		private int[] queueAr;
		private int size; // Size of Queue

		private int rear; // elements will be added at rear.
		private int front; // elements will be removed from front
		private int number; // holds number of elements in Queue, initialized
							// with 0 by default

		/**
		 * Constructor
		 */
		public Queue(int size) {
			this.size = size;
			queueAr = new int[this.size];
			rear = 0;
			front = 0;
		}

		/**
		 * Insert element at rear in Queue
		 */
		public void enqueue(int value) {

			if (isFull()) {
				throw new QueueFullException(
						"Cannot insert " + value + ", Queue is full");
			}
			if (rear == size) // means we are at last position (deal with
								// wrapAround)
				rear = 0;
			queueAr[rear++] = value; // Insert element and increment rear
			number++; // increase number of elements in Queue
		}

		/**
		 * Removes elements from front of Queue
		 */
		public int dequeue() {
			if (isEmpty()) {
				throw new QueueEmptyException("Queue is empty");
			}

			int deletedValue = queueAr[front++]; // get value at front and than
													// increment front
			if (front == size) // deal with wrapAround
				front = 0;
			number--; // reduce number of elements in Queue
			return deletedValue;
		}

		/**
		 * @return true if Queue is empty
		 */
		public boolean isEmpty() {
			return (number == 0);
		}

		/**
		 * @return true if Queue is full
		 */
		public boolean isFull() {
			return (number == size);
		}

	}

}
