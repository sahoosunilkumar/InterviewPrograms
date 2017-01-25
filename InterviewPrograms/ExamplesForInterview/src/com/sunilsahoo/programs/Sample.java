package com.sunilsahoo.programs;

public class Sample {
	
	public static void main(String args[]){
		Sample sample = new Sample();
		
		int[] A = {6,42,11,7,1,42,7,28,7};
		int X = 7;
		int Y = 7;
		int output = sample.solution(X, Y, A);
		System.out.println(output);
		System.out.println(sample.solution(3, 426));
		System.out.println("arranged seats :"+sample.solution(2, "1A 2F 1C"));
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
	
	public int solution(int N, String S) {
		int noOfWays = 0;
		int consecutiveCounter = 0;
		int noOfFamilyMember = 3;
		String aisleStartArr = "A D H";
//		char[] seatNumberArr = new char[]{'A','B','C','D','E','F','G','H','I','J','K'};
		String seatNumberStr = "ABCDEFGHJK";
//		String[] reservedSeatsArr = S== null ? new String[]{}: S.split(" ");
		String seatNumber="";
		for(int i=1; i<=N; i++){
			for(int seatNumberCounter = 0; seatNumberCounter<seatNumberStr.length(); seatNumberCounter++){
				
				seatNumber = String.valueOf(seatNumberStr.charAt(seatNumberCounter));
				if(S.contains(i+seatNumber) || (aisleStartArr.contains(seatNumber) && consecutiveCounter !=0) ){

					System.out.println("failed "+(i+seatNumber)+" : "+consecutiveCounter);
					consecutiveCounter = (aisleStartArr.contains(seatNumber) && consecutiveCounter !=0)? 1:0;
				}else{
					consecutiveCounter++;
					System.out.println("adv "+(i)+seatNumber+" : "+consecutiveCounter);
				}
				
				if(consecutiveCounter == noOfFamilyMember){
					noOfWays++;
					consecutiveCounter = 0;
					System.out.println((i)+seatNumber);
				}
			}
		}
		
		return noOfWays;
    }

}

