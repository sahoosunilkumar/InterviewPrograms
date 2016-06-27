package com.sunilsahoo.programs;
/*Now, lets see how complexity is O(n/2).
We are executing only half the number of characters we have in our string.

 Best case: O(n/2)- (1/2), when we have odd number of characters in string.
Average case: O(n/2) , generally when we have even number of characters in string.
Worst case: O(n/2).*/

public class ReverseStringExample {
	public static void main(String... args) {
		String originalString = "abcde"; // String to be reversed

		System.out.println("Original String: " + originalString);
		System.out.println("Reversed String: " + reverseString(originalString));
	}

	/*
	 * return reversed String.
	 */
	public static String reverseString(String originalString) {
		char ar[] = originalString.toCharArray();
		char temp;
		for (int i = 0, j = ar.length - 1; i < (ar.length / 2); i++, j--) {
			temp = ar[i];
			ar[i] = ar[j];
			ar[j] = temp;
		}
		return new String(ar);
	}
}