package com.sunilsahoo.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The executor framework presented in the last chapter works with Runnable
 * objects. Unfortunately a Runnable cannot return a result to the caller. In
 * case you expect your threads to return a computed result you can use
 * java.util.concurrent.Callable . The Callable object allows to return values
 * after completion. The Callable object uses generics to define the type of
 * object which is returned. If you submit a object to an the framework returns
 * an object of type . exposes methods allowing a client to monitor the progress
 * of a task being executed by a different thread. Therefore a Future object can
 * be used to check the status of a Callable and to retrieve the result from the
 * Callable . On the Executor you can use the method submit to submit a Callable
 * and to get a future. To retrieve the result of the future use the get()
 * method.
 * 
 * 
 * Drawbacks with Futures and Callbacks The interface is limited as a model of
 * asynchronously executed tasks. While Future allows a client to query a task
 * for its result, it does not provide the option to register a callback method,
 * which would allow to notified once a task is done. In Java 5 you could use
 * ExecutorCompletionService for this purpose but as of Java 8 you can use the
 * CompletableFuture interface which allows to provide a callback interface
 * which is called once a task is completed.
 * 
 * 
 * @author sunilkumarsahoo
 *
 */
public class CallableExample {
	private static final int NTHREDS = 10;

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		for (int i = 0; i < 20000; i++) {
			Callable<Long> worker = new MyCallable();
			Future<Long> submit = executor.submit(worker);
			list.add(submit);
		}
		long sum = 0;
		System.out.println(list.size()); // now retrieve the result
		for (Future<Long> future : list) {
			try {
				sum += future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sum);
		executor.shutdown();
	}
}

class MyCallable implements Callable<Long> {
	@Override
	public Long call() throws Exception {
		long sum = 0;
		for (long i = 0; i <= 100; i++) {
			sum += i;
		}
		System.out.println("Thread Name :" + Thread.currentThread());
		return sum;
	}
}
