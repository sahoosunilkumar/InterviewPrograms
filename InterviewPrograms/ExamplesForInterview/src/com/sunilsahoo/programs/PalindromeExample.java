package com.sunilsahoo.programs;

/*
 * Q.What is palindrome?
A. If reverse of string is same as original one, than our string is palindrome or palindrome is a string which reads the same backward or forward.

 Example in java>
input String : aabaa
output: it’s palindrome.
 */
public class PalindromeExample {
	public static void main(String... args) {
		String inputString = "aabaa"; // String to be reversed
		System.out.println(
				isPalindrome(inputString) ? inputString + " is a palindrome."
						: inputString + "is not a palindrome.");
	}

	/*
	 * returns true if inputString is palindrome.
	 */
	public static boolean isPalindrome(String inputString) {
		char ar[] = inputString.toCharArray();
		for (int i = 0, j = ar.length - 1; i < (ar.length / 2); i++, j--) {
			if (ar[i] != ar[j])
				return false;
		}
		return true;
	}
}
