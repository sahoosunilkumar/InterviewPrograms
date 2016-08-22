package com.sunilsahoo.programs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class StringProgram {
	public static void main(String[] args) {
		StringProgram programObj = new StringProgram();
		System.out.println(programObj.checkBalancedParenthesis(")(PH)N(X)"));
		System.out.println(programObj
				.longestSubstringWithNonRepeatingCharacters("ABCDABDEFGCABD"));

		Set<String> dictionary = new HashSet<String>(Arrays.asList("arrays",
				"dynamic", "heaps", "IDeserve", "learn", "learning", "linked",
				"list", "platform", "programming", "stacks", "trees"));

		System.out.println("Has Valid Words :" + programObj
				.hasValidWords("IDeservelearningplatform", dictionary));
	}

	/* Check balanced parentheses in a string Time Complexity is O(n) */
	private boolean checkBalancedParenthesis(String str) {
		int balancedParenthesisCount = 0;
		for (int i = 0; i < str.length(); i++) {
			if (balancedParenthesisCount < 0) {
				return false;
			}
			if ('(' == str.charAt(i)) {
				balancedParenthesisCount++;
			} else if (')' == str.charAt(i)) {
				balancedParenthesisCount--;
			}
		}
		return (balancedParenthesisCount == 0) ? true : false;

	}

	/*
	 * Longest Substring with non-Repeating Characters 1. Create an array
	 * charIndexes which has last index of string characters seen in the string,
	 * initialized to -1 2. Traverse the array and check if current character
	 * was seen earlier in the current sub-array, if not seen then increment
	 * index of current non repeating substring seen till now(currLength). 3. If
	 * the current character is a repeated character and length of longest
	 * substring (maxLength) seen till now is less than current length, then
	 * update maxLength. Time Complexity is O(n) Space Complexity is O(1) Input
	 * : ABCDABDEFGCABD Output: ABDEFGC
	 */
	private String longestSubstringWithNonRepeatingCharacters(String str) {
		if (str == null) {
			return null;
		}

		int[] charIndexes = new int[256];
		for (int i = 0; i < charIndexes.length; i++) {
			charIndexes[i] = -1;
		}
		int maxLength = 0;
		int currentLength = 0;
		int startIndex = 0;
		int prevIdx = -1;
		for (int i = 1; i < str.length(); i++) {
			prevIdx = charIndexes[str.charAt(i)];
			if ((prevIdx == -1) || ((i - currentLength) > prevIdx)) {
				currentLength++;
			} else {
				if (currentLength > maxLength) {
					maxLength = currentLength;
					startIndex = i - maxLength;
				}
				currentLength = i - prevIdx;
			}

			charIndexes[str.charAt(i)] = i;
		}
		return str.substring(startIndex, startIndex + maxLength);
	}

	/*
	 * Time Complexity is O(n^2) in worst case Space Complexity is O(n)
	 */
	boolean hasValidWords(String words, Set<String> dictionary) {
		if (words == null || words.length() == 0) {
			return true;
		}
		int n = words.length();
		boolean[] validWords = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (dictionary.contains(words.substring(0, i + 1))) {
				validWords[i] = true;
			}
			if (validWords[i] == true) {
				for (int j = i + 1; j < n; j++) {
					if (dictionary.contains(words.substring(i + 1, j + 1))) {
						validWords[j] = true;
					}
					if (j == n - 1 && validWords[j] == true) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
