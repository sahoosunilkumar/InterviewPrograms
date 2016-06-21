package com.sunilsahoo.thread;

class MyRunnable implements Runnable{
    public void run(){
           for(int i=0;i<5;i++){
                  try {
                        Thread.sleep(100);
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
                  System.out.println("i="+i+" ,ThreadName="+Thread.currentThread().getName());
           }          
    }
}
 
/** Copyright (c), AnkitMittal JavaMadeSoEasy.com */
public class ThreadOnSameProcess {
    public static void main(String...args){
           MyRunnable myRunnable=new MyRunnable();
           Thread thread1=new Thread(myRunnable,"Thread-1");
           Thread thread2=new Thread(myRunnable,"Thread-2");
           thread1.start();
           thread2.start();
    }
}
