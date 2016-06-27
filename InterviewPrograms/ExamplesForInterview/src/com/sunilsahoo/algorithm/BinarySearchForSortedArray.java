package com.sunilsahoo.algorithm;

/**
 * We then compare the target value to the median candidate, that is, the
 * element with index mid = ⌊(low + high)/2⌋ . We consider three cases: • If the
 * target equals the median candidate, then we have found the item we are
 * looking for, and the search terminates successfully. • If the target is less
 * than the median candidate, then we recur on the first half of the sequence,
 * that is, on the interval of indices from low to mid − 1. • If the target is
 * greater than the median candidate, then we recur on the second half of the
 * sequence, that is, on the interval of indices from mid + 1 to high. • An
 * unsuccessful search occurs if low > high, as the interval [low,high] is
 * empty.
 * 
 * 
 * To prove this claim, a crucial fact is that with each recursive call the
 * number of candidate elements still to be searched is given by the value
 * high−low+1. Moreover, the number of remaining candidates is reduced by at
 * least one-half with each recursive call. Specifically, from the definition of
 * mid, the number of remaining candidates is either or (mid−1)−low+1=
 * low+high −low≤ high−low+1 22 high−(mid+1)+1 = high− low+high ≤ high−low+1. 22
 * Initially, the number of candidates is n; after the first call in a binary
 * search, it is at most n/2; after the second call, it is at most n/4; and so
 * on. In general, after the jth call in a binary search, the number of
 * candidate elements remaining is at most n/2 j . In the worst case (an
 * unsuccessful search), the recursive calls stop when there are no more
 * candidate elements. Hence, the maximum number of recursive calls performed,
 * is the smallest integer r such that n <1. 2r In other words (recalling that
 * we omit a logarithm’s base when it is 2), r is the smallest integer such that
 * r > log n. Thus, we have r = ⌊log n⌋ + 1, which implies that binary search
 * runs in O(log n) time.
 * 
 * @author sunilkumarsahoo
 *
 */
public class BinarySearchForSortedArray {
	private static int counter = 0;

	private static boolean binarySearch(int[] arr, int number, int startPoint,
			int endPoint) {

		counter++;
		int pivot = (startPoint + endPoint) / 2;
		System.out.println(" startpoint : " + startPoint + " endpoint : "
				+ endPoint + " pivot " + pivot);
		if (startPoint > endPoint) {
			return false;
		}
		if (number == arr[pivot]) {
			System.out.println("counter :" + counter + "  " + number
					+ " found at index : " + pivot);
			return true;
		} else if (number < arr[pivot]) {
			return binarySearch(arr, number, startPoint, pivot - 1);
		} else {
			return binarySearch(arr, number, pivot + 1, endPoint);
		}

	}

	public static void main(String[] str) {
		int[] arr = { 1, 5, 8, 20, 21, 30, 35, 40, 55, 60, 65, 70, 75, 78, 82,
				90, 93, 98, 100 };
		boolean isNumberFound = binarySearch(arr, 102, 0, arr.length - 1);
		System.out.println(isNumberFound);
	}
}
