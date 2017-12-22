package com.sunilsahoo.algorithm;

/*
 *The Knuth-Morris-Pratt Algorithm
 * Time Complexity : O(n + m) 
 * 
 * 
 * 
 * Excluding the computation of the failure function, the running time 
 * of the KMP algorithm is clearly proportional to the number of 
 * iterations of the while loop. For the sake of the analysis, 
 * let us define s = j − k. Intuitively, s is the total amount by 
 * which the pattern has been shifted with respect to the text. 
 * Note that throughout the execution of the algorithm, we have s ≤ n. 
 * One of the following three cases occurs at each iteration of the loop.
• If text[ j] = pattern[k], then j and k each increase by 1, thus s is unchanged.
• If text[ j] ̸= pattern[k] and k > 0, then j does not change and s increases 
by at least 1, since in this case s changes from j−k to j− f(k−1); note that
this is an addition of k− f(k−1), which is positive because f(k−1) < k.
• If text[ j] ̸= pattern[k] and k = 0, then j increases by 1 and s increases by 1,
since k does not change.
 * @author sunilkumarsahoo
 *
 */
public class KMPPatternMatching {

	public static void main(String[] args) {
		KMPPatternMatching kmpObj = new KMPPatternMatching();
		int index = kmpObj.search("bcababcdeababax", "ababa");
		System.out.println("search found at :" + index);
	}

	public int[] computeFailKMP(String pattern) {
		if (pattern == null)
			throw new NullPointerException();

		// Compute longest suffix-prefix table
		int[] fail = new int[pattern.length()];
		int j = 1;
		int k = 0;
		while (j < pattern.length()) {
			if (pattern.charAt(j) == pattern.charAt(k)) {
				fail[j] = k + 1;
				j++;
				k++;
			} else if (k > 0) {
				k = fail[k - 1];
			} else
				j++;
		}
		return fail;
	}

	public int search(String text, String pattern) {
		int textLength = text.length();
		int patternLength = pattern.length();
		if (patternLength == 0)
			return 0;
		int[] fail = computeFailKMP(pattern);
		System.out.println(" lsp :" + Utility.toString(fail));
		int textCounter = 0;
		int patternCounter = 0;
		while (textCounter < textLength) {
			System.out.println("textcounter : " + textCounter
					+ " pattern counter : " + patternCounter);
			if (text.charAt(textCounter) == pattern.charAt(patternCounter)) {
				if (patternCounter == patternLength - 1)
					return textCounter - patternCounter;
				textCounter++;
				patternCounter++;
			} else if (patternCounter > 0) {
				patternCounter = fail[patternCounter - 1];
			} else
				textCounter++;
		}
		return -1;
	}

}
