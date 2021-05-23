package com.sunilsahoo.dynamicprogramming;

public class StairsClimb {
    public static void main(String[] args) {
        StairsClimb stairsClimb = new StairsClimb();
        int result = stairsClimb.count(4  );
        System.out.println(result);
    }
    private int count(int n){
        if (n<=2){
            return n;
        }
        int first = 1;
        int second = 2;
        for (int i=3;i<=n;i++){
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
