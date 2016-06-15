package com.sunilsahoo.programs;

/*
 * Execute code inside while loop as number>0 (i.e. 123)
remainder=number%10;
number=number/10;
reverse=reverse*10+remainder;
remainder=3, number=12, reverse=3

 Execute code inside while loop again, as number>0 (i.e. 12)
remainder=number%10;
number=number/10;
reverse=reverse*10+remainder;
remainder=2, number=1, reverse=32

 Execute code inside while loop again, as number>0 (i.e. 1)
remainder=number%10;
number=number/10;
reverse=reverse*10+remainder;
remainder=1, number=0, reverse=321

 Now, while loop will not be executed again as number=0.
 */
public class ReverseNumberExample {
    public static void main(String...args){
           
           int number=12345; //number to be reversed
           
           System.out.println("Original number: "+number);
           System.out.println("Reversed number: "+reverseNumber(number));
           
    }
    
    public static int reverseNumber(int number){
           int reverse=0;
           int remainder;
           
           while(number>0){
                  remainder=number%10;
                  number=number/10;
                  reverse=reverse*10+remainder;
           }          
           return reverse;
    }
 
}
