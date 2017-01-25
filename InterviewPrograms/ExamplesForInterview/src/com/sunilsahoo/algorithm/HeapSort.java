package com.sunilsahoo.algorithm;

import java.util.Arrays;

/**
 * Time Complexity is O(nlogn) Space Complexity is O(1)
 * 
 * Heap sort is an in-place comparison sort. Heap sort is not a stable sort i.e.
 * it does not ensure the order of same array elements after sorting.
 * Definition: A max-heap is a complete binary tree in which the value at root
 * is greater than or equal to the values of left and right children of the root
 * and all the heap nodes follow this property. Since a max heap is a complete
 * binary tree, it can be represented by an array such that: 1. Element at index
 * 0 is the root. 2. For any node of the heap at index i, including the root
 * node (i=0): Index of Left child = 2*i+1 Index of Right child = 2*i+2 Parent
 * of the node = (i-1)/2
 * 
 * @author sunilkumarsahoo
 *
 */
public class HeapSort {
	public static void maxHeapify(int[] array, int curIndex, int heapSize) {
		int left = 2 * curIndex + 1;
		int right = 2 * curIndex + 2;
		int largest = curIndex;
		if (left < heapSize && array[left] > array[curIndex]) {
			largest = left;
		}
		if (right < heapSize && array[right] > array[largest]) {
			largest = right;
		}
		if (largest != curIndex) {
			swap(array, curIndex, largest);
			maxHeapify(array, largest, heapSize);
		}
	}

	public static void buildMaxHeap(int[] array, int heapSize) {
		int lastElementIndex = array.length - 1;
		int parentIndex = (lastElementIndex - 1) / 2;
		for (int i = parentIndex; i >= 0; i--) {
			maxHeapify(array, i, heapSize);
		}
	}

	public static void heapSort(int[] array) {
		if (array == null || array.length < 2)
			return;
		buildMaxHeap(array, array.length);
		int heapSize = array.length;
		for (int i = array.length - 1; i > 0; i--) {
			swap(array, 0, i);
			heapSize = heapSize - 1;
			maxHeapify(array, 0, heapSize);
		}
	}

	public static void main(String[] args) {
		int[] array = { 12, 35, 87, 26, 9, 28, 7 };
		System.out.println("Original Array:\t\t" + Arrays.toString(array));
		heapSort(array);
		System.out.println("Sorted Array:\t\t" + Arrays.toString(array));
	}

	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}