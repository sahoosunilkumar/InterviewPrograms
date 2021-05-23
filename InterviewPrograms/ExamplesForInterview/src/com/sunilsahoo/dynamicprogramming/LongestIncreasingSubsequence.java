package com.sunilsahoo.dynamicprogramming;

public class LongestIncreasingSubsequence {
    static int count;
    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80,1};//result will be 6
//        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int result = lis.lis(arr);
        System.out.println(result);
    }

    /**
     * Time Complexity nlog(n)
     * @param arr
     * @return
     */
    private int lis(int[] arr) {
        int[] table = new int[arr.length];
        int max = 0;
        for (int i=0;i<arr.length;i++){
            int maxLength = 0;
            for (int j=0; j<i;j++){
                if (arr[j]<arr[i]){
                    maxLength = Math.max(maxLength, table[j]);
                }
            }
            table[i] = maxLength+1;
            max = Math.max(table[i], max);
        }
        return max;
    }
}
