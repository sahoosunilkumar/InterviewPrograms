package com.sunilsahoo.concurrency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * Java 5.0 provides supports for additional atomic operations.
 * This allows to develop algorithm which are non-blocking algorithm, 
 * e.g. which do not require synchronization, but are based on 
 * low-level atomic hardware primitives such as compare-and-swap (CAS). 
 * A compare-and-swap operation check if the variable has a certain value 
 * and if it has this value it will perform this operation. 
 * Non-blocking algorithms are typically faster than blocking algorithms, 
 * as the synchronization of threads appears on a much finer level (hardware).
 *
 *The interesting part is how the incrementAndGet() method is implemented. 
 *It uses a CAS operation.
public final int incrementAndGet() { for (;;) {
int current = get();
int next = current + 1;
if (compareAndSet(current, next))
} }
return next;
The JDK itself makes more and more use of non-blocking algorithms to increase performance for every developer. Developing correct non-blocking algorithm is not a trivial task.

 */
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDataTypeExample {
	private static final int NTHREDS = 10;

	public static void main(String[] args) {
		final Counter counter = new Counter();
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		for (int i = 0; i < 500; i++) {
			Callable<Integer> worker = new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int number = counter.increment();
					System.out.println(number);
					return number;
				}
			};
			Future<Integer> submit = executor.submit(worker);
			list.add(submit);
		}
		// This will make the executor accept no new threads // and finish all
		// existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
		}
		Set<Integer> set = new HashSet<Integer>();
		for (Future<Integer> future : list) {
			try {
				set.add(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		if (list.size() != set.size()) {
			throw new RuntimeException("Double-entries!!!");
		}
	}
}

class Counter {
	private AtomicInteger value = new AtomicInteger();

	public int getValue() {
		return value.get();
	}

	public int increment() {
		return value.incrementAndGet();
	}
	// Alternative implementation as increment but just make the //
	// implementation explicit

	public int incrementLongVersion() {
		int oldValue = value.get();
		while (!value.compareAndSet(oldValue, oldValue + 1)) {
			oldValue = value.get();
		}
		return oldValue + 1;
	}
}
