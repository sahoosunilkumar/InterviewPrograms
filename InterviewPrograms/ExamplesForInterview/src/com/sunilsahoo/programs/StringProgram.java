package com.sunilsahoo.programs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import com.sunilsahoo.algorithm.Utility;

public class StringProgram {
	public static void main(String[] args) {
		StringProgram programObj = new StringProgram();
		System.out.println(programObj.checkBalancedParenthesis(")(PH)N(X)"));
		System.out.println("balanced brackets : "+programObj.checkBalancedBrackets("{[]}"));
		System.out.println(programObj
				.longestSubstringWithNonRepeatingCharacters("ABCDABDEFGCABD"));

		Set<String> dictionary = new HashSet<String>(Arrays.asList("arrays",
				"dynamic", "heaps", "IDeserve", "learn", "learning", "linked",
				"list", "platform", "programming", "stacks", "trees"));

		System.out.println("Has Valid Words :" + programObj
				.hasValidWords("IDeservelearningplatform", dictionary));

		char[] arr = "welcome to coding algorithms".toCharArray();
		// reverse the string
		programObj.reverse(arr, 0, arr.length / 2, arr.length);
		System.out.println(new String(arr));

		// reverse words of a string
		arr = "welcome to coding algorithms".toCharArray();
		System.out.println(new String(programObj.reverseWords(arr)));
		String originalString = "a,b$cde!fgci";
		System.out.println("Reversed String: "
				+ programObj.reverseStringWithPunctuation(originalString));
		originalString = "byta";
		System.out.println(originalString + " has unique chars :"
				+ programObj.checkUnique(originalString));
//		System.out.println("After removing duplicates : "
//				+ programObj.removeDuplicate("adbgfraaghdl"));
		char[] charArr = { 'a', 'b', 'd', 'g', 'f', 'r', 'a', 'a', 'g', 'h',
				'd', 'l' };
		System.out.println("After removing duplicates"
				+ Utility.toString(programObj.removeDuplicate(charArr)));
		System.out.println("Check Anagram : "
				+ programObj.checkTwoStringsAnagram("ciodma", "iceman"));
		System.out.println("After compression : "+programObj.compress("SSSunil"));
		System.out.println("Longest Prefix as suffix substring : "+programObj.longestPrefixAsSuffixSUbstring("bananast"));
		programObj.longestPrefixAsSuffixSUbstring1("adgrbananast");
		
		String encode = TinyURL.encode(123);
		System.out.println ( "after Encoding : " + encode ) ;
		System.out.println ( "after Decoding : " + TinyURL.decode (encode) ) ;

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
	/* Check balanced brackets in a string Time Complexity is O(n) */
	private boolean checkBalancedBrackets(String str) {
		Stack<Character> parenthesisStack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			if ('(' == str.charAt(i) || '{' == str.charAt(i)
					|| '[' == str.charAt(i)) {
				parenthesisStack.push(str.charAt(i));
			} else {
				if (isMatching(str.charAt(i), parenthesisStack.isEmpty() ? ' '
						: parenthesisStack.peek())) {
					if ((')' == str.charAt(i) || '}' == str.charAt(i)
							|| ']' == str.charAt(i))) {
						parenthesisStack.pop();
					}
				} else {
					return false;
				}

			}
		}
		return parenthesisStack.isEmpty();
	}
	boolean isMatching(char inputChar, char matchAgainst){
		return (')' == inputChar && '(' == matchAgainst) || ('}' == inputChar && '{' == matchAgainst) || (']' == inputChar && '[' == matchAgainst);
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

	/**
	 * Algorithm to reverse word of a string /** Complexity = O(n/2)
	 * 
	 * @author sunilkumarsahoo
	 * 
	 * @return reversed words array
	 */
	private char[] reverseWords(char[] arr) {
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
	private char[] reverse(char[] arr, int firstIndex, int midIndex,
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

	private boolean wildcardMatch(String str, String pattern) {
		int strIndex = 0;
		int patternIndex = 0;
		int startPattern = -1;
		int startText = -1;
		while (strIndex < str.length()) {
			if ((str.charAt(strIndex) == pattern.charAt(patternIndex))
					|| pattern.charAt(patternIndex) == '?') {
				strIndex++;
				patternIndex++;
			} else if (patternIndex < pattern.length()
					&& pattern.charAt(patternIndex) == '*') {
				while (patternIndex < pattern.length()
						&& pattern.charAt(patternIndex) == '*')
					patternIndex++;
				if (patternIndex == pattern.length())
					return true;
				startPattern = patternIndex;
				startText = strIndex;
			} else if ((patternIndex >= pattern.length()
					|| str.charAt(strIndex) != pattern.charAt(patternIndex))
					&& startPattern > -1) {
				startText++;
				patternIndex = startPattern;
				strIndex = startText;
			} else {
				return false;
			}
		}
		while (patternIndex < pattern.length()) {
			if (pattern.charAt(patternIndex) != '*')
				return false;
			patternIndex++;
		}
		return true;
	}

	/*
	 * Now, lets see how complexity is O(n/2). We are executing only half the
	 * number of characters we have in our string.
	 * 
	 * Best case: O(n/2)- (1/2), when we have odd number of characters in
	 * string. Average case: O(n/2) , generally when we have even number of
	 * characters in string. Worst case: O(n/2).
	 */
	public String reverseStringWithPunctuation(String originalString) {
		char ar[] = originalString.toCharArray();
		char temp;
		int j = ar.length - 1;
		int i = 0;
		boolean isStartAlphanumeric = false;
		boolean isEndAlphanumeric = false;
		while (j > i) {
			isStartAlphanumeric = isAlphanumeric(ar[i]);
			isEndAlphanumeric = isAlphanumeric(ar[j]);
			if (isStartAlphanumeric && isEndAlphanumeric) {
				temp = ar[i];
				ar[i] = ar[j];
				ar[j] = temp;
				i++;
				j--;
			} else {
				if (!isStartAlphanumeric) {
					i++;
				}
				if (!isEndAlphanumeric) {
					j--;
				}
			}

		}
		return new String(ar);
	}

	private boolean isAlphanumeric(char c) {
		if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60)
				|| c > 0x7a)
			return false;
		else
			return true;
	}

	/**
	 * Time complexity O(n) space complexity O(1)
	 * 
	 * @param str
	 * @return
	 */
	private boolean checkUnique(String str) {
		int checker = 0;
		int value = 0;
		for (int i = 0; i < str.length(); i++) {
			value = str.charAt(i) - 'a';
			if ((checker & (1 << value)) > 0) {
				return false;
			}
			checker |= 1 << value;
		}
		return true;
	}

//	private String removeDuplicate(String str) {
//		int checker = 0;
//		int value = 0;
//		int index = 0;
//		boolean isDuplicate = false;
//		StringBuffer sb = new StringBuffer(str);
//		while (index < sb.length()) {
//			value = sb.charAt(index);
//			isDuplicate = (checker & (1 << value)) > 0;
//			if (isDuplicate) {
//				sb.replace(index, sb.length() - 1,
//						sb.substring(index + 1, sb.length() - 1));
//			} else {
//				index++;
//			}
//			checker |= 1 << value;
//		}
//		return sb.toString();
//	}

	/**
	 * Design an algorithm and write code to remove the duplicate characters in
	 * a string without using any additional Time Complexity : O(n) Space
	 * Complexity : O(1)
	 * 
	 * @param charArr
	 * @return
	 */
	private char[] removeDuplicate(char[] charArr) {
		int checker = 0;
		int value = 0;
		int startIndex = 1;
		int replaceIndex = 0;
		boolean isDuplicate = false;
		while (startIndex < charArr.length) {
			value = charArr[replaceIndex];
			isDuplicate = (checker & (1 << value)) > 0;
			if (isDuplicate) {
				charArr[replaceIndex] = charArr[startIndex];
				startIndex++;
				// continue;
			} else {
				checker |= 1 << value;
				startIndex++;
				replaceIndex++;
			}
			// checker |= 1<<value;
		}
		for (int i = replaceIndex + 1; i < charArr.length; i++) {
			charArr[i] = '0';
		}
		return charArr;
	}

	/**
	 * a word, phrase, or name formed by rearranging the letters of another,
	 * such as cinema, formed from iceman.
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	 private boolean checkTwoStringsAnagram(String str1, String str2){
		 //TODO
		 return false;
	 }

	/**
	 * Write a method to replace all spaces in a string with ‘%20’
	 * Count the number of spaces during the first scan of the string 
	 * Parse the string again from the end and for each character:
	 * If a space is encountered, store “%20” Else, 
	 * store the character as it is in the newly shifted location
	 * @param charArr
	 */
	private void replaceSpaceWithChar(char[] charArr, String replacewith){
		
	}
	
	private String compress(String s) {
		char pre_c = 0;
		int compress_v = 0;
		StringBuilder sb = new StringBuilder();
		
		for (char c : s.toCharArray()) {
			if (c != pre_c) {
				if (compress_v != 0) {
					sb.append(compress_v);
				}
				sb.append(c);
				pre_c = c;
				compress_v = 1;
			}
			else {
				compress_v++;
			}
		}
		sb.append(compress_v);

		String return_s = sb.toString();
		
		return return_s.length() > s.length() ? s : return_s;
	}
	/*
	 * For string S, find the longest palindromic substring. Example: S = "bananas" LPS = "anana"
	 * Time Complexity O(n), Space Complexity : O(n)
	 */
	private String longestPrefixAsSuffixSUbstring(String s) {
		char[] charArr = new char[s.length() * 2 + 3];
		charArr[0] = '$';
		charArr[s.length() * 2 + 2] = '@';
		for (int i = 0; i < s.length(); i++) {
			charArr[2 * i + 1] = '#';
			charArr[2 * i + 2] = s.charAt(i);
		}
		
		charArr[s.length() * 2 + 1] = '#';
		System.out.println(Utility.toString(charArr));
		int[] P = new int[charArr.length];
		int center = 0, right = 0;
		for (int i = 1; i < charArr.length - 1; i++) {
			int mirr = 2 * center - i;
			if (i < right)
				P[i] = Math.min(right - i, P[mirr]);
			while (charArr[i + (1 + P[i])] == charArr[i - (1 + P[i])])
				P[i]++;
			if (i + P[i] > right) {
				center = i;
				right = i + P[i];
			}
		}
		int length = 0;
		center = 0;
		for (int i = 1; i < P.length - 1; i++) {
			if (P[i] > length) {
				length = P[i];
				center = i;
			}
		}
		return s.substring((center - 1 - length) / 2,
				(center - 1 + length) / 2);
	}
	
	
	
	
	private void longestPrefixAsSuffixSUbstring1(String s) {
		int i = 0;
		int j = s.length()-1;
		int startPosition=-1,endPosition = -1;
		
		HashMap<Character,Integer> map = new HashMap<>();
		while(i<j){
			if(map.containsKey(s.charAt(i))){
				startPosition=i;
				endPosition = s.charAt(i);
			}else{
				map.put(s.charAt(i), i);
			}
			if(map.containsKey(s.charAt(j))){
				endPosition=j;
				startPosition = map.get(s.charAt(j));
			}else{
				map.put(s.charAt(j), j);
			}
			if((startPosition !=-1) || (endPosition !=-1)){
				break;
			}
			i++;
			j--;
		}
		s = s.substring(startPosition, endPosition+1);
		
		System.out.println(s);
		int[] fail = new int[s.length()];
		i = 1;
		j = s.length()-1;
		while(i<j){
			if(s.charAt(i)==s.charAt(j)){
				fail[i]=fail[i-1]+1;
				i++;
				j--;
			}else{
				fail[i] = 0;
				i++;
			}
		}
		System.out.println(" fail : "+Utility.toString(fail));
		System.out.println();
	}
	
	
	

}


class TinyURL {
	private static final String ALPHABET_MAP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" ;
	private static final int BASE = ALPHABET_MAP.length() ;

	public static String encode ( int IndexNum ) {
		StringBuilder sb = new StringBuilder() ;
		
		while ( IndexNum > 0 ) {
			sb.append ( ALPHABET_MAP.charAt ( IndexNum % BASE ) ) ;
			IndexNum /= BASE ;
		}
		return sb.reverse().toString() ;
	}

	public static int decode ( String str ) {
		int Num = 0 ;

		for ( int i = 0, len = str.length(); i < len; i++ ) {
			Num = Num * BASE + ALPHABET_MAP.indexOf ( str.charAt(i) ) ;
		}
		return Num ;
	}
}
