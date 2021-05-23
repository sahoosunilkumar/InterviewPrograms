package com.sunilsahoo.dynamicprogramming;

public class LongestPalindromSubString {
    public static void main(String[] args) {
        LongestPalindromSubString longestPalindromSubString = new LongestPalindromSubString();
        String result = longestPalindromSubString.longestPalindrome("babad");
        System.out.println(result);
    }

    public String longestPalindrome(String s) {
        boolean table[][] = new boolean[s.length()][s.length()];
        int startIndex = -1;
        int endIndex = -1;
        int prevGap = -1;
        for(int gap= 0; gap<s.length(); gap++){
            for(int i=0, j=gap; j<s.length(); i++,j++){
                if(gap ==0){
                    table[i][j] = true;
                }else if(gap==1 && s.charAt(i)==s.charAt(j)){
                    table[i][j] = true;
                }else{
                    if(s.charAt(i) == s.charAt(j) && table[i+1][j-1]){
                        table[i][j] = true;
                    }
                }
                if(table[i][j]){
                    if(prevGap != gap){
                        startIndex = i;
                        endIndex = j;

                        prevGap = gap;
                    }
                }
            }
        }
        if(startIndex ==-1 || endIndex==-1){
            return null;
        }
        return s.substring(startIndex, endIndex+1);

    }
}
