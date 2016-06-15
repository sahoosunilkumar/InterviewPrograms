package com.sunilsahoo.thread;

public class Output{

    public synchronized void print(String s){
        System.out.println(s);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void scan(String s){
        synchronized (s){
            try {
                s.wait(1000);
                System.out.println("Scan is completed for "+s);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
