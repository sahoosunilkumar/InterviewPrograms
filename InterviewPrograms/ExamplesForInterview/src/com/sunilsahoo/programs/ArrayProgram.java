package com.sunilsahoo.programs;

public class ArrayProgram {
	public static void main(String[] args) {
		ArrayProgram arrayProgram = new ArrayProgram();
		int[] A = { 2, 3, 5, 9, 10, 15, 17, 20, 22 };
		int matchNumber = 5;
		boolean found = arrayProgram.findNumber(A, matchNumber);
		System.out.println("Is Match Found : " + found);
		
		int arrival[] = {900, 940, 950, 1100, 1500, 1800} ;
		int deparature[] = {910, 1200, 1120, 1130, 1900, 2000};
		arrayProgram.numberOfPlatformRequired(arrival, deparature);
	}

	/**
	 * find whether sum of two numbers which is equal to the number present in
	 * array
	 * 
	 * @param A
	 * @param matchNumber
	 * @return
	 */
	private boolean findNumber(int[] A, int matchNumber) {
		int i = 0;
		int j = A.length - 1;
		int tempValue;
		while (true) {
			if (i > j) {
				return false;
			}
			tempValue = A[i] + A[j];
			if (tempValue == matchNumber) {
				return true;
			} else if (tempValue > matchNumber) {
				j--;
			} else {
				i++;
			}
		}
	}

	/*
	 * Input: arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00} 
	 * dep[] = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
	 * Output: 3. 
	 * There are at-most three trains at a time (time between 11:00 to 11:20)
	 */
	private void numberOfPlatformRequired(int[] arrival, int[] deparature){
		int i = 0;
		int j =0;
		int maxPlatform = 0;
		int platform = 0;
		while(i<(arrival.length-1)){
			if(arrival[i]<deparature[j]){
				i++;
				platform++;
			}else{
				j++;
				platform--;
			}
			maxPlatform = Math.max(maxPlatform, platform);
		}
		System.out.println("Maximum no of platform required : "+maxPlatform);
	}
	
}
