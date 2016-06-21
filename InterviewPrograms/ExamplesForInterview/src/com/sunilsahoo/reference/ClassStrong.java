package com.sunilsahoo.reference;

public class ClassStrong {
	 
    public static class Referred {
        protected void finalize() {
            System.out.println("Good bye cruel world");
        }
    }
 
    public static void collect() throws InterruptedException {
        System.out.println("Suggesting collection");
        System.gc();
        System.out.println("Sleeping");
        Thread.sleep(5000);
    }
 
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Creating strong references");
 
        // This is now a strong reference.
        // The object will only be collected if all references to it disappear.
        Referred strong = new Referred();
 
        // Attempt to claim a suggested reference.
        ClassStrong.collect();
 
        System.out.println("Removing reference");
        // The object may now be collected.
        strong = null;
        ClassStrong.collect();
 
        System.out.println("Done");
    }
 
}
