
package com.sunilsahoo.designpatterns;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Producer Consumer Design pattern is a classic concurrency or threading
 * pattern which reduces coupling between Producer and Consumer by separating
 * Identification of work with Execution of Work.
 * <p>
 * In producer consumer design pattern a shared queue is used to control the
 * flow and this separation allows you to code producer and consumer separately.
 * It also addresses the issue of different timing require to produce item or
 * consuming item. by using producer consumer pattern both Producer and Consumer
 * Thread can work with different speed.
 * 
 */
public class ProducerConsumerTest {
	public static void main(String[] args) {
		ProducerConsumerTest pcTest = new ProducerConsumerTest();

		ItemQueue queue = pcTest.new ItemQueue();

		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 2; i++) {

			Producer producer = pcTest.new Producer("Producer_" + i, queue);
			executorService.submit(() -> {
				while (true) {
					producer.produce();
				}
			});
		}

		for (int i = 0; i < 3; i++) {
			final Consumer consumer = pcTest.new Consumer("Consumer_" + i, queue);
			executorService.submit(() -> {
				while (true) {
					consumer.consume();
				}
			});
		}

		executorService.shutdown();
		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
			executorService.shutdownNow();
		} catch (InterruptedException e) {
			System.out.println("Error waiting for ExecutorService shutdown");
		}
	}

	class Producer {

		private final ItemQueue queue;

		private final String name;

		private int itemId;

		public Producer(String name, ItemQueue queue) {
			this.name = name;
			this.queue = queue;
		}

		/**
		 * Put item in the queue
		 */
		public void produce() throws InterruptedException {

			Item item = new Item(name, itemId++);
			queue.put(item);
			Random random = new Random();
			Thread.sleep(random.nextInt(2000));
		}
	}

	class ItemQueue {

		private BlockingQueue<Item> queue;

		public ItemQueue() {

			queue = new LinkedBlockingQueue<>(5);
		}

		public void put(Item item) throws InterruptedException {

			queue.put(item);
		}

		public Item take() throws InterruptedException {

			return queue.take();
		}
	}

	class Item {

		private String producer;

		private int id;

		public Item(String producer, int id) {
			this.id = id;
			this.producer = producer;
		}

		public int getId() {

			return id;
		}

		public String getProducer() {

			return producer;
		}

	}

	class Consumer {

		private final ItemQueue queue;

		private final String name;

		public Consumer(String name, ItemQueue queue) {
			this.name = name;
			this.queue = queue;
		}

		/**
		 * Consume item from the queue
		 */
		public void consume() throws InterruptedException {

			Item item = queue.take();
			System.out.println(String.format("Consumer [%s] consume item [%s] produced by [%s]", name, item.getId(),
					item.getProducer()));

		}
	}
}
