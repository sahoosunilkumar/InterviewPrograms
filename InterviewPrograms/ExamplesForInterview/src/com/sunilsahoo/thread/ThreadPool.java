package com.sunilsahoo.thread;



public class ThreadPool {
	
	public static void main(String[] args) {
		Task task = new Task();
		WorkerThread[] threadPool = new WorkerThread[2];  
		for(int i = 0; i<2; i++){
			threadPool[i] = new WorkerThread(task);
		}
		while(task.getCounter()<=20){
			int index = task.getCounter() %2;
			if(threadPool[index].getState() == Thread.State.NEW){
			threadPool[index].start();
			}
		}
	}
	   
	}
	class WorkerThread extends Thread{
		Task task;
		WorkerThread(Task task){
			this.task = task;
		}

	    @Override
	    public void run() {
	            	System.out.println(Thread.currentThread().getName()+" counter "+task.getCounter());
	            	int counter = task.getCounter() +1;
	            	task.setCounter(counter);
	    }
	}
	class Task{
		private volatile int counter = 1;

		public int getCounter() {
			return counter;
		}

		public void setCounter(int counter) {
			this.counter = counter;
		}
	}
