package com.sunilsahoo.concurrency;

/**
 * Asynchronous task handling is important for any application which 
 * performs time consuming activities, as IO operations. Two basic approaches 
 * to asynchronous task handling are available to a Java application: 
 * a thread can wait for a task, which is a blocking approach. 
 * Or the task can perform an action directly when the event completes, 
 * this is called a nonblocking approach.
 * 
 * CompletableFuture extends the functionality of the 
 * Future interface with standard techniques for executing 
 * application code when a task completes, including various ways to combine tasks. 
 * CompletableFuture support both blocking and nonblocking approaches, 
 * including regular callbacks.
 * 
 * This callback can be executed in another thread as the thread in which the 
 * CompletableFuture is executed. 
 * 
 * The following example demonstrates how to create a basic CompletableFuture. 
 * CompletableFuture.supplyAsync runs the task asynchronously on the default thread pool 
 * of Java. It has the option to supply your custom executor to define the ThreadPool .
 */
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
	public static void main(String[] args) {
		CompletableFuture<String> data = createCompletableFuture()
				.thenApply((Integer count) -> {
					int transformedValue = count * 10;
					return transformedValue;
				}).thenApply(transformed -> "Finally creates a string: "
						+ transformed);
		try {
			System.out.println(data.get());
		} catch (InterruptedException | ExecutionException e) {
		}
	}

	private static CompletableFuture<Integer> createCompletableFuture() {
		CompletableFuture<Integer> futureCount = CompletableFuture
				.supplyAsync(() -> {
					try {
						// simulate long running task
						Thread.sleep(5000);
					} catch (InterruptedException e) {
					}
					return 20;
				});
		return futureCount;
	}
}
