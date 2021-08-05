package com.sunilsahoo.array;

import java.util.ArrayList;
import java.util.List;

public class MultiplicationOfArray {

    public static void main(String[] args) {

        increasingSubstrings("ABCDEFFDEfghCBA");
        int[] arr = {2,5,8,9,6};
        MultiplicationOfArray multiplicationOfArray = new MultiplicationOfArray();
        arr = multiplicationOfArray.multiplication(arr);
        for (int element: arr){
            System.out.print(element+" ");
        }
    }

    private int[] multiplication(int[] arr) {
        //find products of non zero element
        int product =1;
        int countOfZero =0;
        for (int element: arr){
            if (element !=0){
                product*=element;
            }else{
                countOfZero++;
            }
        }
        for (int index=0; index<arr.length; index++){
            if (countOfZero==0){
                arr[index]=product/arr[index];
            }else if(countOfZero==1){
                if (arr[index] !=0){
                    arr[index]=0;
                }else{
                    arr[index]=product;
                }
            }else {
                arr[index]=0;
            }
        }
        return arr;
    }
    static String[] increasingSubstrings(String s) {
        int i=0;
        int j=1;
        int startPosition = i;
        List<String> subStringList = new ArrayList<>();
        if (s==null||s.length()==0){
            return new String[subStringList.size()];
        }
        if (s.length()==1){
            subStringList.add(""+s.charAt(0));
            String[] subStringArr = new String[subStringList.size()];
            return subStringList.toArray(subStringArr);
        }
        while(j<s.length()){
            if((s.charAt(j)!=s.charAt(i)+1) || j==s.length()-1){
                subStringList.add(s.substring(startPosition,j));
                startPosition=j;
                if (j==s.length()-1){
                    break;
                }
            }
            i++;
            j++;
        }
        if((s.charAt(j)==s.charAt(i)+1)){
            String element = subStringList.get(subStringList.size()-1);
            element = element+s.charAt(j);
            subStringList.remove(subStringList.size()-1);
            subStringList.add(element);
        }else {
            subStringList.add(""+s.charAt(j));
        }

        String[] subStringArr = new String[subStringList.size()];
        return subStringList.toArray(subStringArr);
    }


}
