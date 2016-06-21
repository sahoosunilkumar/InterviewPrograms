package com.sunilsahoo.designpatterns;
/**
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MultitonOrPoolExample {
	
	public static void main(String[] args){
	System.out.println("Program started");

    // Create a list of tasks to be executed
    List<Task> tasks = new ArrayList<>();
    tasks.add(new PotatoPeelingTask(3));
    tasks.add(new PotatoPeelingTask(6));
    tasks.add(new CoffeeMakingTask(2));
    tasks.add(new CoffeeMakingTask(6));
    tasks.add(new PotatoPeelingTask(4));
    tasks.add(new CoffeeMakingTask(2));
    tasks.add(new PotatoPeelingTask(4));
    tasks.add(new CoffeeMakingTask(9));
    tasks.add(new PotatoPeelingTask(3));
    tasks.add(new CoffeeMakingTask(2));
    tasks.add(new PotatoPeelingTask(4));
    tasks.add(new CoffeeMakingTask(2));
    tasks.add(new CoffeeMakingTask(7));
    tasks.add(new PotatoPeelingTask(4));
    tasks.add(new PotatoPeelingTask(5));

    // Creates a thread pool that reuses a fixed number of threads operating off a shared
    // unbounded queue. At any point, at most nThreads threads will be active processing
    // tasks. If additional tasks are submitted when all threads are active, they will wait
    // in the queue until a thread is available.
    ExecutorService executor = Executors.newFixedThreadPool(3);

    // Allocate new worker for each task
    // The worker is executed when a thread becomes
    // available in the thread pool
    for (int i = 0; i < tasks.size(); i++) {
      Runnable worker = new Worker(tasks.get(i));
      executor.execute(worker);
    }
    // All tasks were executed, now shutdown
    executor.shutdown();
    while (!executor.isTerminated()) {
      Thread.yield();
    }
    System.out.println("Program finished");
	}
}

class Worker implements Runnable {

	  private final Task task;

	  public Worker(final Task task) {
	    this.task = task;
	  }

	  @Override
	  public void run() {
	    System.out.println(String.format("%s processing %s", Thread.currentThread().getName(),
	        task.toString()));
	    try {
	      Thread.sleep(task.getTimeMs());
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
	}


abstract class Task {

	  private static final AtomicInteger ID_GENERATOR = new AtomicInteger();

	  private final int id;
	  private final int timeMs;

	  public Task(final int timeMs) {
	    this.id = ID_GENERATOR.incrementAndGet();
	    this.timeMs = timeMs;
	  }

	  public int getId() {
	    return id;
	  }

	  public int getTimeMs() {
	    return timeMs;
	  }

	  @Override
	  public String toString() {
	    return String.format("id=%d timeMs=%d", id, timeMs);
	  }
	}

class CoffeeMakingTask extends Task {

	  private static final int TIME_PER_CUP = 100;

	  public CoffeeMakingTask(int numCups) {
	    super(numCups * TIME_PER_CUP);
	  }

	  @Override
	  public String toString() {
	    return String.format("%s %s", this.getClass().getSimpleName(), super.toString());
	  }
	}

class PotatoPeelingTask extends Task {

	  private static final int TIME_PER_POTATO = 200;

	  public PotatoPeelingTask(int numPotatoes) {
	    super(numPotatoes * TIME_PER_POTATO);
	  }

	  @Override
	  public String toString() {
	    return String.format("%s %s", this.getClass().getSimpleName(), super.toString());
	  }
	}

