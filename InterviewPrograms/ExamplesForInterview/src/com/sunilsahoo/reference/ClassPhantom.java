package com.sunilsahoo.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

/*
 * A sample for Detecting and locating memory leaks in Java
 * URL: http://neverfear.org/blog/view/150/Java_References
 * Author: doug@neverfear.org
 */
public class ClassPhantom {

	public static class Referred {
		// Note that if there is a finalize() method PhantomReference's don't
		// get appended to a ReferenceQueue
	}

	public static void collect() throws InterruptedException {
		System.out.println("Suggesting collection");
		System.gc();
		System.out.println("Sleeping");
		Thread.sleep(5000);
	}

	public static void main(String args[]) throws InterruptedException {
		System.out.println("Creating phantom references");

		// The reference itself will be appended to the dead queue for clean up.
		ReferenceQueue dead = new ReferenceQueue();

		// This map is just a sample we might use to locate resources we need to
		// clean up.
		Map<Reference, String> cleanUpMap = new HashMap<Reference, String>();

		// This is now a phantom reference.
		// The object will be collected only if no strong references.
		Referred strong = new Referred();

		PhantomReference<Referred> phantom = new PhantomReference(strong, dead);
		cleanUpMap.put(phantom,
				"You need to clean up some resources, such as me!");

		strong = null;

		// The object may now be collected
		ClassPhantom.collect();

		// Check for
		Reference reference = dead.poll();
		if (reference != null) {
			System.out.println(cleanUpMap.remove(reference));
		}
		System.out.println("Done");
	}

}
