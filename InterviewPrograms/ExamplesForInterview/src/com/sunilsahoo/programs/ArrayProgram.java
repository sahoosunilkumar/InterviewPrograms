package com.sunilsahoo.programs;

import java.util.LinkedHashMap;

import com.sunilsahoo.algorithm.QuickSort;
import com.sunilsahoo.algorithm.Utility;

public class ArrayProgram {
	public static void main(String[] args) {
		ArrayProgram arrayProgram = new ArrayProgram();
		int[] A = { 2, 3, 5, 9, 10, 15, 17, 20, 22 };
		int matchNumber = 5;
		boolean found = arrayProgram.findNumber(A, matchNumber);
		System.out.println("Is Match Found : " + found);
		found = arrayProgram.findNumber1(A, matchNumber);
		System.out.println("Is Match Found Using approach 1 : " + found);
		leftRotate(A, 2);
		A = new int[] { 16, 17, 4, 3, 5, 2 };
		findLeaders(A);
		System.out.println(findMaxSum(A));

		int arrival[] = { 900, 940, 950, 1100, 1500, 1800 };
		int deparature[] = { 910, 1200, 1120, 1130, 1900, 2000 };
		arrayProgram.numberOfPlatformRequired(arrival, deparature);

		int[] stockPriceArr = { 100, 80, 120, 130, 70, 60, 95, 100 };
		System.out.println(findMaxProfitStock(stockPriceArr));
		int[] stockPriceArr1 = { 100, 80, 120, 130, 70, 60, 100, 125 };
		System.out.println(findMaxProfit(stockPriceArr1));
	}

	/**
	 * find whether sum of two numbers which is equal to the number present in
	 * array
	 * 
	 * @param A
	 * @param matchNumber
	 * @return
	 */
	private boolean findNumber(int[] A, int matchNumber) {
		int i = 0;
		int j = A.length - 1;
		int tempValue;
		while (true) {
			if (i > j) {
				return false;
			}
			tempValue = A[i] + A[j];
			if (tempValue == matchNumber) {
				return true;
			} else if (tempValue > matchNumber) {
				j--;
			} else {
				i++;
			}
		}
	}

	/**
	 * find whether sum of two numbers which is equal to the number present in
	 * array
	 * 
	 * @param A
	 * @param matchNumber
	 * @return
	 */
	private boolean findNumber1(int[] A, int matchNumber) {
		boolean[] binmap = new boolean[100000];
		for (int i = 0; i < A.length; ++i) {
			int temp = matchNumber - A[i];
			if (temp >= 0 && binmap[temp]) {
				return true;
			}
			binmap[A[i]] = true;
		}
		return false;
	}

	/*
	 * Input: arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00} dep[] = {9:10,
	 * 12:00, 11:20, 11:30, 19:00, 20:00} Output: 3. There are at-most three
	 * trains at a time (time between 11:00 to 11:20)
	 */
	private void numberOfPlatformRequired(int[] arrival, int[] deparature) {
		int i = 0;
		int j = 0;
		int maxPlatform = 0;
		int platform = 0;
		while (i < (arrival.length - 1)) {
			if (arrival[i] < deparature[j]) {
				i++;
				platform++;
			} else {
				j++;
				platform--;
			}
			maxPlatform = Math.max(maxPlatform, platform);
		}
		System.out.println("Maximum no of platform required : " + maxPlatform);
	}

	/*
	 * Find whether an array is subset of another array 1) Sort both arrays:
	 * arr1[] and arr2[] O(mLogm + nLogn) 2) Use Merge type of process to see if
	 * all elements of sorted arr2[] are present in sorted arr1[]. Time
	 * Complexity: O(mLogm + nLogn)
	 */
	boolean isSubset(int arr1[], int arr2[], int m, int n) {
		int i = 0, j = 0;
		if (m < n)
			return false;

		QuickSort.sort(arr1);
		QuickSort.sort(arr2);
		while (i < n && j < m) {
			if (arr1[j] < arr2[i])
				j++;
			else if (arr1[j] == arr2[i]) {
				j++;
				i++;
			}

			else if (arr1[j] > arr2[i])
				return false;
		}
		if (i < n)
			return false;
		else
			return true;
	}

	/*
	 * Write a program to print all the LEADERS in the array. An element is
	 * leader if it is greater than all the elements to its right side. And the
	 * rightmost element is always a leader. For example int the array {16, 17,
	 * 4, 3, 5, 2}, leaders are 17, 5 and 2. Let the input array be arr[] and
	 * size of the array be size. Time Complexity: O(n)
	 */
	private static void findLeaders(int[] arr) {
		int maxNumber = 0;
		System.out.print("findLeaders : ");
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] > maxNumber) {
				maxNumber = arr[i];
				System.out.print(arr[i] + ", ");
			}
		}
		System.out.println();
	}

	/*
	 * Maximum sum such that no two elements are adjacent Question: Given an
	 * array of positive numbers, find the maximum sum of a subsequence with the
	 * constraint that no 2 numbers in the sequence should be adjacent in the
	 * array. So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10 7
	 * should return 15 (sum of 3, 5 and 7).
	 * 
	 * Loop for all elements in arr[] and maintain two sums incl and excl where
	 * incl = Max sum including the previous element and excl = Max sum
	 * excluding the previous element. Max sum excluding the current element
	 * will be max(incl, excl) and max sum including the current element will be
	 * excl + current element (Note that only excl is considered because
	 * elements cannot be adjacent). At the end of the loop return max of incl
	 * and excl. Example: arr[] = inc = 5 exc = 0 For i = incl = excl= For i =
	 * incl = excl= {5, 5, 10, 40, 50, 35} 1 (current element is 5) (excl +
	 * arr[i]) = 5 max(5,0)=5 2 (current element is 10) (excl + arr[i]) = 15
	 * max(5,5)=5 For i = incl = (excl + arr[i]) = 45 excl = max(5, 15) = 15 3
	 * (current element is 40) For i = 4 (current element is 50) incl = (excl +
	 * arr[i]) = 65 excl = max(45, 15) = 45 For i = 5 (current element is 35)
	 * incl = (excl + arr[i]) = 80 excl = max(5, 15) = 65 And 35 is the last
	 * element. So, answer is max(incl, excl) = 80
	 * 
	 */
	private static int findMaxSum(int arr[]) {
		int incl = arr[0];
		int excl = 0;
		int excl_new;
		int i;
		for (i = 1; i < arr.length; i++) {
			excl_new = (incl > excl) ? incl : excl;
			incl = excl + arr[i];
			excl = excl_new;
		}
		return ((incl > excl) ? incl : excl);
	}

	/*
	 * Write a function rotate(ar[], d, n) that rotates arr[] of size n by d
	 * elements Time Complexity: O(n)
	 */
	private static void leftRotate(int arr[], int d) {
		int i, j;
		if (d == 0 || d == arr.length)
			return;
		i = d;
		j = arr.length - d;
		while (i != j) {
			if (i < j) {
				swap(arr, d - i, d + j - i, i);
				j -= i;
				System.out
						.println("111 Left rotate : " + Utility.toString(arr));
			} else {
				swap(arr, d - i, d, j);
				i -= j;
				System.out
						.println("222 Left rotate : " + Utility.toString(arr));
			}
		}
		swap(arr, d - i, d, i);
		System.out.println("After Left rotate : " + Utility.toString(arr));
	}

	private static void swap(int arr[], int fi, int si, int d) {
		int i, temp;
		for (i = 0; i < d; i++) {
			temp = arr[fi + i];
			arr[fi + i] = arr[si + i];
			arr[si + i] = temp;
		}
	}

	/*
	 * Given an array representing prices of the stock on different days, find
	 * the maximum profit that can be earned by performing maximum of one
	 * transaction. A transaction consists of activity of buying and selling the
	 * stock on different or same days. For example in this array - {100, 80,
	 * 120, 130, 70, 60, 100, 125} the price of the stock on day-1 is 100, on
	 * day-2 is 80 and so on. The maximum profit that could be earned in this
	 * window is 65 (buy at 60 and sell at 125). For price array - {100, 80, 70,
	 * 65, 60, 55, 50}, maximum profit that could be earned is 0.
	 * 
	 * 
	 * If you observe above chart carefully, you should be able to notice that:
	 * for any given day, maximum profit obtainable is maximum of 1.Maximum
	 * profit obtained till previous day, 2.Stock price on that day - minimum
	 * stock price so far. Therefore, to find out the maximum profit obtainable
	 * for a given window, all we need to is to keep track of minimum stock
	 * price seen so far (starting from day-1) and maximum profit obtained so
	 * far. Maximum profit obtained so far can be computed using above
	 * observation. Maximum profit obtained so far is initialized to 0 and
	 * minimum stock price seen so far is initialized to MAX_VALUE.
	 * 
	 * Order of the Algorithm Time Complexity is O(n) Space Complexity is O(1)
	 * 
	 */
	private static LinkedHashMap<String, Integer> findMaxProfitStock(
			int[] stockPriceArr) {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		int maxProfit = 0;
		int minStockPrice = stockPriceArr[0];
		for (int i = 1; i < stockPriceArr.length; i++) {
			if ((stockPriceArr[i] < minStockPrice)) {
				minStockPrice = stockPriceArr[i];
			}
			if ((stockPriceArr[i] - minStockPrice) > maxProfit) {
				maxProfit = stockPriceArr[i] - minStockPrice;
				map.put("Day", i);
				map.put("Profit", maxProfit);
			}

		}
		return map;
	}

	/**
	 * Given an array of stock prices, find the maximum profit that can be
	 * earned by performing multiple non-overlapping transactions (buy and
	 * sell). Example: {100, 80, 120, 130, 70, 60, 100, 125} Profit: 115
	 * 
	 * Time Complexity is O(n) Space Complexity is O(1)
	 * 
	 * @return
	 */
	private static int findMaxProfit(int[] stockPriceArr) {
		int maxProfit = 0;
		for (int i = 1; i < stockPriceArr.length; i++) {
			if ((stockPriceArr[i] - stockPriceArr[i - 1]) > 0) {
				maxProfit += (stockPriceArr[i] - stockPriceArr[i - 1]);
			}
		}
		return maxProfit;
	}
}
