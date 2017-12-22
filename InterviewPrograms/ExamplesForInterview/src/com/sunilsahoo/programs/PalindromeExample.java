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
		System.out.println(isBitPalindrome(7));
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

	/**
	 * Because int has 32-bit, so we cut int in two havles, and the second half
	 * is in reverse order, then we see if two halves are equal or not.
	 * 
	 * 1101 | 1011
	 * 
	 * 1st half: 1101 2nd half: 1101
	 * 
	 * UPDATE: But this answer is part correct. When the number of effective
	 * bits is odd, algorithm failed. e.g: 1001 (YES) 101 (NO)
	 * 
	 * UPDATE2: Since two parts of bits share the same middle bit. So if there
	 * are odd number of effective bits we ignore the last one in y.
	 */
	static boolean isBitPalindrome(int x) {
		int y = 0, count = 0;

		// count the # of effectively bits of x
		int t = x;
		while (t != 0) {
			t >>= 1;
			count++;
		}

		for (int i = 0; i < 16; i++) {
			// left move y for the next bit
			y <<= 1;
			// the least significant bit of x is 1, then update y's.
			System.out.printf("x & 1 [%d]: %d  y:%d\n", i, x & 1, y);
			if ((x & 1) > 0) { // remeber & operator should in parentheses
				y |= 1;
			}
			// decrease x, e.g. 11001 -> 1100
			x >>= 1;

			// Since x is keep decreasing and y is keep increasing, we can but
			// the loop early once x <= y
			if (x <= y)
				break;
		}

		if (count % 2 != 0)
			y >>= 1;

		System.out.println(x + "  " + y);
		return x == y;
	}
}
