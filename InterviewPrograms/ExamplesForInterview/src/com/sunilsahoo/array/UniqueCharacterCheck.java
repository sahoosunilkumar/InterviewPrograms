package com.sunilsahoo.array;


import java.util.Arrays;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 */
public class UniqueCharacterCheck {
    public static void main(String[] args) {
        char[] input = {'b','c','a','d','x'};
//        Approach -1 Using two loop -> Time Complexity = O(n2), Space Complexity = O(1)
//        Approach -2 Using sorting  -> Time Complexity = O(nLogn), Space Complexity = O(nLogn), Quick Sort space Complexity O(n)
//        Approach-3 Using additional space array/HashMap, input = {'b','c','a','d','a'}; //storage {'b','c','a','d'}
        boolean result = findUsingStorage(input);
        System.out.println(result);
    }

    private static boolean findUsingStorage(char[] input){
        int[] arr = new int[256];
        char element;
        for (int i=0; i<input.length;i++){
            element = input[i];
            if (arr[element] == 0){
                arr[element]=1;
            }else{
                return false;
            }
        }
        return true;
    }

        /* After sorting {'a','b','c','d','x'}
        compare the array with matching character = O(n)
        O(nLogn)+O(n), SPace Complexity = sorting+O(1)  8*Log8 =24 + 8
        1000*10 +1000*/
    private static boolean findUsingSort(char[] input){
        if (input==null || input.length==0){
            return true;
        }
        Arrays.sort(input);
        System.out.println(input);
        char matchingChar = input[0];
        for (int i=1;i<input.length;i++){
            if (input[i] ==matchingChar){
                return false;
            }else {
                matchingChar = input[i];
            }
        }
        return true;
    }

/*      Time Complexity = 4 +3 + 2 + 1 = 1+2+...+n-1 = n*(n-1)/2 5*4/2 =10, 1000*999/2
        Space Complexity = O(1) = Constant
        change factor = Matching character = loop , Array = loop traversal of the array
        startIndexOf of inner loop = indexOf matching character +1
        endIndex of outer loop = lastelement -1

 */
    private static boolean findUsingLoop(char[] input){
        int length = input.length;
        for (int i=0;i<length-1;i++){
            for (int j=i+1;j<length;j++){
                if (input[i]==input[j]){
                    //counter to print
                    return false;
                }
            }//traversal of array
        }//changing the matching character
        return true;
    }

}
