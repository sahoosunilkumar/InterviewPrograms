package com.sunilsahoo.algorithm;

public class QuickSort {

	public static void main(String[] args) {
		int[] test = { 38, 81, 22, 48, 13, 69, 30 };
		QuickSort.sort(test);
	}

/*
 * Quicksort or partition-exchange sort, is a fast sorting algorithm, which is
 * using divide and conquer algorithm. Quicksort first divides a large list into
 * two smaller sub-lists: the low elements and the high elements. Quicksort can
 * then recursively sort the sub-lists.
 * 
 * Steps to implement Quick sort:
 * 
 * 1) Choose an element, called pivot, from the list. Generally pivot can be the
 * middle index element. 2) Reorder the list so that all elements with values
 * less than the pivot come before the pivot, while all elements with values
 * greater than the pivot come after it (equal values can go either way). After
 * this partitioning, the pivot is in its final position. This is called the
 * partition operation. 3) Recursively apply the above steps to the sub-list of
 * elements with smaller values and separately the sub-list of elements with
 * greater values.
 * 
 * 
 * there are three basic steps:
1. Partition the array or subarray into left (smaller keys) and right (larger keys) groups.
2. Call ourselves to sort the left group.
3. Call ourselves again to sort the right group.

After a partition, all the items in the left subarray are smaller than all 
those on the right. If we then sort the left subarray and sort the right subarray,
the entire array will be sorted. How do we sort these subarrays? 
By calling ourself recursively.
If the array has two or more cells, the algorithm calls the partitionIt() method, 
described in the preceding section, to partition it. 
This method returns the index number of the partition: 
the left element in the right (larger keys) subarray. 
The partition marks the boundary between the subarrays. 

What pivot value should the partitionIt() method use? Here are some relevant ideas:
• The pivot value should be the key value of an actual data item; 
this item is called the pivot.
• You can pick a data item to be the pivot more or less at random. 
For simplicity, let’s say we always pick the item on the right end of the subarray being parti- tioned.
• After the partition, if the pivot is inserted at the boundary between the left and right subarrays, it will be in its final sorted position
 * 
 * The complexity of quick sort in the average case is Θ(n log(n)) and in the
 * worst case is Θ(n2)
 */

	public static void sort(int[] numbers) {
		// check for empty or null array
		if (numbers == null || numbers.length == 0) {
			return;
		}

		System.out.println("before sorting");
		System.out.println(Utility.toString(numbers));
		quicksort(0, numbers.length - 1, numbers);
		System.out.println("after sorting");
		System.out.println(Utility.toString(numbers));
	}

	public static void quicksort(int left, int right, int[] numbers) {
		System.out.println("left : "+left +" right : "+right);
		if (left >= right)
			return;
		else {
			// if size <= 1,
			// already sorted // size is 2 or larger
			long pivot = numbers[right]; // rightmost item // partition range
			int partition = partitionIt(left, right, pivot, numbers);
			quicksort(left, partition - 1, numbers); // sort left side
			quicksort(partition + 1, right, numbers); // sort right side
		}
	} // end recQuickSort()

	private static void exchange(int i, int j, int[] numbers) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
		System.out.println(" After swap : "+Utility.toString(numbers));
	}

	private static int partitionIt(int left, int right, long pivot, int[] numbers) {
		int leftPtr = left - 1; // right of first elem
		int rightPtr = right + 1; // left of pivot
		System.out.println(" leftptr : "+leftPtr+" rightptr : "+rightPtr+" pivot : "+pivot+" number : "+Utility.toString(numbers));
		while (true) {
			while (leftPtr < right && numbers[++leftPtr] < pivot) // find bigger
																	// item
				; // (nop)
			while (rightPtr > left && numbers[--rightPtr] >= pivot) // find
																	// smaller
																	// item
				; // (nop)
			if (leftPtr >= rightPtr)
				break;
			else{
				System.out.println("leftPtr : "+leftPtr+" rightPtr : "+rightPtr+" pivot : "+pivot);
				exchange(leftPtr, rightPtr, numbers); // if pointers cross, partition done // not crossed, so swap elements
			}
		} // end while(true)
		System.out.println(leftPtr+" other exchange "+right);
		exchange(leftPtr, right, numbers);
		return leftPtr;
		
		// return partition
	} // end partitionIt()
		// //--------------------------------------------------------------

}
