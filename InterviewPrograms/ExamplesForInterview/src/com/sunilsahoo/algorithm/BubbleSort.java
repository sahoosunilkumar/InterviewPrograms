package com.sunilsahoo.algorithm;

public class BubbleSort {
	private void bubblesort(int[] intArray) {
		int n = intArray.length;
		int temp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (intArray[j - 1] > intArray[j]) {
					// swap the elements!
					temp = intArray[j - 1];
					intArray[j - 1] = intArray[j];
					intArray[j] = temp;
				}

			}
			System.out.println("after "+i +" iteration : "+Utility.join(",", intArray));
		}
	}
	public static void main(String[] args){
		int[] intArray = {5,8,2,9,20,1};
		System.out.println("array :"+Utility.join(",", intArray));
		new BubbleSort().bubblesort(intArray);
	}
}
