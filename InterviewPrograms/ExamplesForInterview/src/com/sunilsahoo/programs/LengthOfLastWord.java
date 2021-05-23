package com.sunilsahoo.programs;

public class LengthOfLastWord {
    public static void main(String[] args) {
        LengthOfLastWord lengthOfLastWord = new LengthOfLastWord();
        int length = lengthOfLastWord.lengthOfLastWord("Hello W");
        System.out.println(length);
    }
    public int lengthOfLastWord(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        int length = s.length()-1;
        int count = 0;
        boolean isValid = false;
        while(true){
            if(s.charAt(length) != ' '){
                isValid = true;
                count++;
            }else{
                if(isValid){
                    break;
                }
            }
            length--;
            if(length<0){
                break;
            }
        }
        return count;

    }
}
