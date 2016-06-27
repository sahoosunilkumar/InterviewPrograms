package com.sunilsahoo.algorithm;

/**
 * Complexity = O(n/2)
 * 
 * @author sunilkumarsahoo
 *
 */
public class ReverseString {

	/**
	 * Algorithm to reverse word of a string
	 * 
	 * @return reversed words array
	 */
	public char[] reverseWords(char[] arr) {
		// reverse the string
		reverse(arr, 0, arr.length / 2, arr.length);

		// reverse words of a string
		int firstIndex = 0;
		int midIndex = 0;
		int prevlastIndex = 0;

		// outer loop to track spaces
		for (int sentenceIdx = 0; sentenceIdx < arr.length; sentenceIdx++) {
			if (arr[sentenceIdx] != ' ')
				continue;

			firstIndex = prevlastIndex;
			int lastIndex = sentenceIdx;
			midIndex = (sentenceIdx + firstIndex) / 2;
			// reverse each word in a string
			reverse(arr, firstIndex, midIndex, lastIndex);
			prevlastIndex = sentenceIdx + 1;
		}

		// reverse last word
		firstIndex = prevlastIndex;
		midIndex = (arr.length + firstIndex) / 2;
		reverse(arr, firstIndex, midIndex, arr.length);

		return arr;
	}

	/**
	 * Algorithm to reverse a string
	 * 
	 * @param arr
	 * @param firstIndex
	 * @param midIndex
	 * @param lastIndex
	 * 
	 * @return reversed string array
	 */
	public char[] reverse(char[] arr, int firstIndex, int midIndex,
			int lastIndex) {
		System.out.println("firstIndex : " + firstIndex + " midIndex : "
				+ midIndex + " lastIndex : " + lastIndex);
		for (; firstIndex < midIndex; firstIndex++, lastIndex--) {
			// swap first letter with the last letter in the
			char tmp = arr[firstIndex];
			arr[firstIndex] = arr[lastIndex - 1];
			arr[lastIndex - 1] = tmp;
		}
		return arr;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReverseString rw = new ReverseString();

		char[] arr = "welcome to coding algorithms".toCharArray();
		// reverse the string
		rw.reverse(arr, 0, arr.length / 2, arr.length);
		System.out.println(new String(arr));

		// reverse words of a string
		arr = "welcome to coding algorithms".toCharArray();
		System.out.println(new String(rw.reverseWords(arr)));
	}
}
