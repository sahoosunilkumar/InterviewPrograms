package com.sunilsahoo.algorithm;
/*
 * MergeSort time Complexity is O(nlgn) which is a fundamental knowledge. 
 * Merge Sort space complexity will always be O(n) including with arrays. 
 * If you draw the space tree out, it will seem as though the 
 * space complexity is O(nlgn). 
 * However, as the code is a Depth First code, 
 * you will always only be expanding along one branch of the tree, 
 * therefore, the total space usage required will always be bounded by O(3n) = O(n).

For example, if you draw the space tree out, it seems like it is O(nlgn)

                             16                                 | 16
                            /  \                              
                           /    \
                          /      \
                         /        \
                        8          8                            | 16
                       / \        / \
                      /   \      /   \
                     4     4    4     4                         | 16
                    / \   / \  / \   / \
                   2   2 2   2.....................             | 16
                  / \  /\ ........................
                 1  1  1 1 1 1 1 1 1 1 1 1 1 1 1 1              | 16
where height of tree is O(logn) => Space complexity is O(nlogn + n) = O(nlogn). 
However, this is not the case in the actual code as it does not execute in parallel. 
For example, in the case where N = 16, this is how the code for mergesort executes. N = 16.

                           16
                          /
                         8
                        /
                       4
                     /
                    2
                   / \
                  1   1
notice how number of space used is 32 = 2n = 2*16 < 3n

Then it merge upwards

                           16
                          /
                         8
                        /
                       4
                     /  \
                    2    2
                        / \                
                       1   1
which is still 32. Then it merge upwards

                           16
                          /
                         8
                        / \
                       4   4
                          /
                         2
                        / \ 
                       1   1
36 < 16 * 3 = 48

then it merge upwards

                           16
                          / \
                         8  8
                           / \
                          4   4
                             / \
                            2   2
                                /\
                               1  1
16 + 16 + 14 = 46 < 3*n = 48

in a larger case, n = 64

                     64
                    /  \
                   32  32
                       / \
                      16  16
                          / \
                         8  8
                           / \
                          4   4
                             / \
                            2   2
                                /\
                               1  1
which is 64*3 <= 3*n = 3*64
 */
public class MergeSort {
//	private int[] array;
    private int[] tempMergArr;
    private int length;
 
    public static void main(String a[]){
         
        int[] inputArr = {45,23,11,10,98,30};
        MergeSort mms = new MergeSort();
        mms.sort(inputArr);
        System.out.println(Utility.toString(inputArr));
    }
     
    public void sort(int array[]) {
//        this.array = inputArr;
        this.length = array.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1, array);
    }
 
    private void doMergeSort(int lowerIndex, int higherIndex, int[] array) {
         System.out.println("do merge : low = "+lowerIndex+" high = "+higherIndex);
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle, array);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex, array);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex, array);
        }
    }
 
//    private void mergeParts(int lowerIndex, int middle, int higherIndex, int[] array) {
//    	
//        for (int i = lowerIndex; i <= higherIndex; i++) {
//            tempMergArr[i] = array[i];
//        }
//        int i = lowerIndex;
//        int j = middle + 1;
//        int k = lowerIndex;
//        while (i <= middle && j <= higherIndex) {
//            if (tempMergArr[i] <= tempMergArr[j]) {
//                array[k] = tempMergArr[i];
//                i++;
//            } else {
//                array[k] = tempMergArr[j];
//                j++;
//            }
//            k++;
//        }
//        while (i <= middle) {
//            array[k] = tempMergArr[i];
//            k++;
//            i++;
//        }
//        System.out.println("low : "+lowerIndex+" high : "+higherIndex+" middle : "+middle+" result : "+Utility.toString(tempMergArr));
//        
//    }
    
    private void mergeParts(int low, int middle, int high, int input[]){
        int temp[] = new int[high-low+1];
        int i = low;
        int j = middle+1;
        int r = 0;
        while(i <= middle && j <= high){
            if(input[i] <= input[j]){
                temp[r++] = input[i++];
            }else{
                temp[r++] = input[j++];
            }
        }
        while(i <= middle){
            temp[r++] = input[i++];
        }
        
        while(j <= high){
            temp[r++] = input[j++];
        }
        i = low;
        for(int k=0; k < temp.length;){
            input[i++] = temp[k++];
        }
    }
}
