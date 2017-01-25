package com.sunilsahoo.reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/*
 * A sample for Detecting and locating memory leaks in Java
 * URL: http://neverfear.org/blog/view/150/Java_References
 * Author: doug@neverfear.org
 */
public class ClassSoft {

	public static class Referred {
		@Override
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
		System.out.println("Creating soft references");

		// This is now a soft reference.
		// The object will be collected only if no strong references exist and
		// the JVM really needs the memory.
		Referred strong = new Referred();
		SoftReference<Referred> soft = new SoftReference<Referred>(strong);

		// Attempt to claim a suggested reference.
		ClassSoft.collect();

		System.out.println("Removing reference");
		// The object may but highly likely wont be collected.
		strong = null;
		ClassSoft.collect();

		System.out.println("Consuming heap");
		try {
			// Create lots of objects on the heap
			List<ClassSoft> heap = new ArrayList<ClassSoft>(100000);
			while (true) {
				heap.add(new ClassSoft());
			}
		} catch (OutOfMemoryError e) {
			// The soft object should have been collected before this
			System.out.println("Out of memory error raised");
		}

		System.out.println("Done");
	}

}
