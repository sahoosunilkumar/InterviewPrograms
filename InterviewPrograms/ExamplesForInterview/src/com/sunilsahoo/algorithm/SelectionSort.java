package com.sunilsahoo.algorithm;

/*
 * The selection sort improves on the bubble sort by reducing 
 * the number of swaps necessary from O(N2) to O(N). 
 * Unfortunately, the number of comparisons remains O(N2). 
 * However, the selection sort can still offer a significant improvement 
 * for large records that must be physically moved around in memory,
 * causing the swap time to be much more important than the comparison time.
 * (Typically, this isn’t the case in Java, where references are moved around, 
 * not entire objects.)
 * 
 * What’s involved in the selection sort is making a pass through all the players 
 * and picking (or selecting, hence the name of the sort) the shortest one.
 * This shortest player is then swapped with the player on the left end of the line, 
 * at position 0. Now the leftmost player is sorted and won’t need to be moved again. 
 * Notice that in this algorithm the sorted players accumulate on the 
 * left (lower indices), whereas in the bubble sort they accumulated on the right.
 * The next time you pass down the row of players, you start at position 1, 
 * and, finding the minimum, swap with position 1. 
 * This process continues until all the players are sorted.
 */
public class SelectionSort {
	public static void main(String[] args) {
		SelectionSort selSort = new SelectionSort();
		int[] arr = { 7, 3, 8, 9, 2, 6 };
		selSort.sort(arr);
		System.out.println("After sorting arr : " + Utility.toString(arr));
	}

	public void sort(int[] arr) {
		int out, in, min, nElems = arr.length;
		for (out = 0; out < nElems - 1; out++) {
			min = out;
			for (in = out + 1; in < nElems; in++) {
				if (arr[in] < arr[min])
					min = in;
			}

			swap(out, min, arr); // swap them
		} // end for(out)
	}

	private void swap(int i, int j, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
