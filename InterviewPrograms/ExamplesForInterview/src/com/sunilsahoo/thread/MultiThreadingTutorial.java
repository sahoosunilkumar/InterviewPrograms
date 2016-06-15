package com.sunilsahoo.thread;


public class MultiThreadingTutorial {

    public static void main(String[] args) {
        SingleThread thread = new SingleThread();
        thread.start();

        Thread t = new Thread(new RunnableThread());
        t.start();

        /* Multithreading */
        Thread t1 = new Thread(new RunnableThread());
        Thread t2 = new Thread(new RunnableThread());
        SingleThread t3 = new SingleThread();

        t1.start();
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        t3.start();

        /* Thread Priority */
        MultiThread multiThread1 = new MultiThread();
        multiThread1.setName("Thread1");
        multiThread1.setPriority(Thread.MIN_PRIORITY);

        MultiThread multiThread2 = new MultiThread();
        multiThread2.setName("Thread2");
        multiThread2.setPriority(Thread.MAX_PRIORITY);

        MultiThread multiThread3 = new MultiThread();
        multiThread3.setName("Thread3");

        multiThread1.start();
        multiThread2.start();
        multiThread3.start();

        /* Synchronization */
        Output output = new Output();
        Library library = new Library(output);
        University university = new University(output);
        library.start();
        university.start();
    }
}
