package com.sunilsahoo.programs;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableTest {
	public static void main(String[] args) {
		CallableTest test = new CallableTest();
		System.out
				.println("starting task : " + Thread.currentThread().getName());

		ExecutorService service = Executors.newFixedThreadPool(3);
		// CountDownLatch latch = new CountDownLatch(3);
		// for(int i=0; i<3; i++){
		String result = "";
		Future<String> future = test.haramKhorTest(service);
		System.out.println("furure data");

		try {
			result = future.get(1, TimeUnit.SECONDS);
			service.shutdown();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("finishing task " + result);
		// test.haramKhorTest(service);
		// }
		// try {
		// latch.await();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		System.out.println(
				"finishing task : " + Thread.currentThread().getName());
	}

	Future<String> haramKhorTest(ExecutorService service) {

		Future<String> future = service.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(5000);
				return "Haram Khor : " + Thread.currentThread().getName();
			}
		});
		return future;

	}

	void haramKhorTest1(ExecutorService service, CountDownLatch latch) {

		service.submit(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String str = "Haram Khor : " + Thread.currentThread().getName();
				System.out.println(str);
				latch.countDown();
			}
		});

	}
}
