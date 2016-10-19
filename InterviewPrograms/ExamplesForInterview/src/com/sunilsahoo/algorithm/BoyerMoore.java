package com.sunilsahoo.algorithm;

/**
 * The algorithm is carried out in two steps:1. Eliminate all elements except
 * one.Iterating through the array of numbers,maintain a current candidate and a
 * counter initialized to 0. With the current element x in iteration,update the
 * counter and(possibly)the candidate:If the counter is 0,set the current
 * candidate to x and the counter to 1. If the counter is not 0,increment or
 * decrement the counter based on whether x is the current candidate.2.
 * Determine if the remaining element is a valid majority element.With the
 * candidate acquired in step 1,iterate through the array of numbers and count
 * its occurrences.Determine if the result is more than half of the
 * sequence'slength.If so,the candidate is the majority.Otherwise,the sequence
 * doesn'tcontain a majority.Note that the counter can be a maximum of n which
 * requires O(\log n)space.In practice however a constant number of bits should
 * suffice as a 128 bit counter can go upto 2^{128}which is large enough for any
 * practical computation.The time complexity remains O(n),even considering the
 * amount of time it takes to increment the counter because it can be
 * incremented in constant amortized time.
 */

public class BoyerMoore {
	public int majorityElement(int[] num) {
		int n = num.length;
		int candidate = num[0], counter = 0;
		for (int i : num) {
			if (counter == 0) {
				candidate = i;
				counter = 1;
			} else if (candidate == i) {
				counter++;
			} else {
				counter--;
			}
		}

		System.out.format("%s\n",
				"counter : " + counter + " candidate : " + candidate);
		counter = 0;
		for (int i : num) {
			if (i == candidate)
				counter++;
		}
		if (counter < (n + 1) / 2)
			return -1;
		return candidate;

	}

	public static void main(String[] args) {
		BoyerMoore s = new BoyerMoore();
		System.out.format("%d\n",
				s.majorityElement(new int[] { 2, 2, 2, 3, 3, 2, 3 }));
		System.out.format("%d\n", s.majorityElement(new int[] { 2, 2, 3 }));
	}
}
