package com.sunilsahoo.algorithm;

public class BinarySearchForSortedArray {
private static int counter = 0;
	private static void binarySearch(int[] arr, int number, int startPoint, int endPoint){
		
		counter++;
		int pivot = (startPoint+endPoint)/2;
		System.out.println(" startpoint : "+startPoint+" endpoint : "+endPoint +" pivot "+pivot);
		if(number == arr[pivot]){
			System.out.println("counter :"+counter+"  "+number+" found at index : "+pivot);
			return;
		}else if(number < arr[pivot]){
			binarySearch(arr, number, startPoint, pivot -1);
		}else if ((number > arr[pivot]) && (number <= arr[endPoint])){
			if(pivot == startPoint){
				pivot = pivot+1;
			}
			binarySearch(arr, number, pivot, endPoint);
		}else{
			System.out.println(" result not found");
		}
		
		
		
	}
	

	public static void main(String[] str){
		int[] arr = {1, 5, 8, 20, 21, 30, 35, 40, 55, 60, 65, 70, 75, 78, 82, 90, 93, 98, 100}; 
		binarySearch(arr, 98, 0, arr.length-1);
	}
}
