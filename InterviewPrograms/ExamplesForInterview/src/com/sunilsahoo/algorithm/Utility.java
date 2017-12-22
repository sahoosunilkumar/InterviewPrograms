package com.sunilsahoo.algorithm;

public class Utility {
	public static String join(final String delimiter, final int[] arr) {
		if (arr == null) {
			return "";
		}
		StringBuilder productIdsWithQuantities = new StringBuilder();
		int startIndex = 0;
		int endIndex = arr.length;
		for (int counter = startIndex; counter < endIndex; counter++) {
			if (counter > startIndex) {
				productIdsWithQuantities.append(delimiter);
			}
			productIdsWithQuantities.append(arr[counter]);
		}
		return productIdsWithQuantities.toString();
	}

	public static String toString(int[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				sb.append(arr[i]);
			} else {
				sb.append(",").append(arr[i]);
			}
		}
		return sb.toString();
	}

	public static String toString(char[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				sb.append(arr[i]);
			} else {
				sb.append(",").append(arr[i]);
			}
		}
		return sb.toString();
	}

	public static String toString(Integer[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				sb.append(arr[i]);
			} else {
				sb.append(",").append(arr[i]);
			}
		}
		return sb.toString();
	}
}
