package com.sunilsahoo.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.sunilsahoo.algorithm.Utility;

public class Sample {
	
	public static void main(String args[]){
		Sample sample = new Sample();
		int[] A = {40,40,100,80,20};
		int[] B = {3,3,2,2,3};
		int M =3;
		int X =5;
		int Y = 200;
		System.out.println("no of stops : "+sample.solution(A, B, M, X, Y));
		System.out.println("no of stops : "+sample.solution(new int[]{60, 80, 40}, new int[]{2, 3, 5}, 5, 2, 200));
		int[] data = {5,4,-3,2,0,1,-1,0,2,-3,4,-5};
		sample.maxNoOfMatches(data);
		int[] arr = {1,5,3,3,2,0,7};
//		sample.solution(arr);
		System.out.println("no of swaps required :"+sample.noOfSwapsRequired(arr));
//		System.out.println("no of swaps required :"+sample.swapRequired(arr));
		int[] A1 = {6,42,11,7,1,42};
		int X1 = 7;
		int Y1 = 42;
		int output = sample.solution(X1, Y1, A1);
		System.out.println("longest leading prefix :"+ output);
		System.out.println(sample.solution(3, 426));
		System.out.println("arranged seats :"+sample.solution(2, "1A 2F 1C"));
		
	}
	
	
	
	public int solution(int[] A, int[] B, int M, int X, int Y) {
        int i=0;
        int stopcount =0;
        int sum =0;
        int noOfPeople = 0;
        while(i<A.length){
        	int prevStop = -1;
        while(i<A.length && (sum+A[i])<Y && noOfPeople<X ){
        	sum = sum+A[i];
        	i++;
        	noOfPeople++;
        }
        while(noOfPeople>0){
        	if(prevStop != B[noOfPeople-1]){
        	stopcount++;
        	prevStop = B[noOfPeople-1];
        	}
        	sum = sum-A[noOfPeople-1];
        	noOfPeople--;
        	
        }
        ++stopcount;
        }
        
        return stopcount;
    }
	
	
	public void maxNoOfMatches(int[] A){
		
		
		boolean prevIncreasing = A[0]>0;
		int size = 0;
		int maxSize = 0;
		for(int i =1; i<A.length; i++){
			if(prevIncreasing){
				if(A[i]<=0){
					size++;
					prevIncreasing = !prevIncreasing;
				}else{
					maxSize = maxSize>size? maxSize : size;
					size = 0;
				}
			}else{
				if(A[i]>=0){
					size++;
					prevIncreasing = !prevIncreasing;
				}else{
					maxSize = maxSize>size? maxSize : size;
					size = 0;
				}
			}
		}
		System.out.println("size : "+maxSize);
	}
	
	
	int solution(int X, int Y, int[] A) {
		if(A == null){
			return -1;
		}
        int N = A.length;
        int result = -1;
        int nX = 0;
        int nY = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] == X)
                nX += 1;
            else if (A[i] == Y)
                nY += 1;
            if (nX == nY || ((nX%2 ==0) && X==Y))
                result = i;
        }
        return result;
    }
	
	
	int solution1(int A, int B)
    {
        int count = 0;
        for (int i=A; i<=B; i++)
            for (int j=1; j*j<=i; j++)
                if (j*j==i)
                	count++;
        return count;
    }
	
	int solution(int A, int B)
    {
		int perfectSquareUpperBounds = (int) Math.floor(Math.sqrt(B));
		int perfectSquareLowerBounds = (int) Math.ceil(Math.sqrt(A));
		return perfectSquareUpperBounds -
				perfectSquareLowerBounds + 1;
    }
	
//	public void solution(int[] arr){
//		int noOfswapRequired = 0;
//		int startIndexOfProb = -1;
//		int disturbedIndex = -1;
//		for(int i=0; i<arr.length-1; i++){
//			if(arr[i] > arr[i+1]){
//				startIndexOfProb = i;
//				disturbedIndex = i+1;
//				noOfswapRequired++;
//			}
//		}
//		System.out.println("no of swap required : "+noOfswapRequired);
//	}
	
	public boolean noOfSwapsRequired(int[] arr){
		int[] sortedArr = new int[arr.length];
		for(int i=0; i<arr.length; i++){
			sortedArr[i] = arr[i];
		}
		Arrays.sort(sortedArr);
		System.out.println(Utility.toString(arr)+"sorted arr :"+Utility.toString(sortedArr));
		int misMatch = 0;
		for(int i=0; i<arr.length; i++){
			if(arr[i] != sortedArr[i]){
				misMatch++;
			}
		}
		System.out.println("mismatch count :"+misMatch);
		return misMatch/2.0 <=1.0;
	}
	public int solution(int N, String S) {
		int noOfWays = 0;
		int consecutiveCounter = 0;
		int noOfFamilyMember = 3;
		String aisleStartArr = "A D H";
		String seatNumberStr = "ABCDEFGHJK";
		String seatNumber="";
		for(int i=1; i<=N; i++){
			for(int seatNumberCounter = 0; seatNumberCounter<seatNumberStr.length(); seatNumberCounter++){
				
				seatNumber = String.valueOf(seatNumberStr.charAt(seatNumberCounter));
				if(S.contains(i+seatNumber) || (aisleStartArr.contains(seatNumber) && consecutiveCounter !=0) ){

//					System.out.println("failed "+(i+seatNumber)+" : "+consecutiveCounter);
					consecutiveCounter = (aisleStartArr.contains(seatNumber) && consecutiveCounter !=0)? 1:0;
				}else{
					consecutiveCounter++;
//					System.out.println("adv "+(i)+seatNumber+" : "+consecutiveCounter);
				}
				
				if(consecutiveCounter == noOfFamilyMember){
					noOfWays++;
					consecutiveCounter = 0;
//					System.out.println((i)+seatNumber);
				}
			}
		}
		
		return noOfWays;
    }
	
	
	

}


final class MyEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
}

