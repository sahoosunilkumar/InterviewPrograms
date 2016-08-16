package com.sunilsahoo.programs;
/*Now, lets see how complexity is O(n/2).
We are executing only half the number of characters we have in our string.

 Best case: O(n/2)- (1/2), when we have odd number of characters in string.
Average case: O(n/2) , generally when we have even number of characters in string.
Worst case: O(n/2).*/

public class ReverseStringExample {
	public static void main(String... args) {
		String originalString = "abcdef"; // String to be reversed

		System.out.println("Original String: " + originalString);
		System.out.println("Reversed String: " + reverseStringWithPunctuation(originalString));
		originalString = "a,b$cde!fgci";
		System.out.println("Reversed String: "+ reverseStringWithPunctuation(originalString));
	}
	
	public static String reverseStringWithPunctuation(String originalString) {
		char ar[] = originalString.toCharArray();
		char temp;
		int j = ar.length - 1;
		int i = 0;
		boolean isStartAlphanumeric = false;
		boolean isEndAlphanumeric = false;
		while (j>i) {
			isStartAlphanumeric = isAlphanumeric(ar[i]);
			isEndAlphanumeric = isAlphanumeric(ar[j]);
			if(isStartAlphanumeric && isEndAlphanumeric){
				temp = ar[i];
				ar[i] = ar[j];
				ar[j] = temp;
				i++;
				j--;
			}else {
				if(!isStartAlphanumeric){
				i++;
				}
				if(!isEndAlphanumeric){
					j--;
				}
			}
			
		}
		return new String(ar);
	}
	
	private static boolean isAlphanumeric(char c){
		if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a)
            return false;
		else
			return true;
	}
}