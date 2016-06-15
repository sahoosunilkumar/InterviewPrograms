package com.sunilsahoo.programs;

public class PrimeNumberExample {
	 
    public static void main(String[] args) {
           
           int n=11;
           System.out.println(isPrimeNumber(n)? n+" is prime number." : n+" is not prime number.");
           n=12;
           System.out.println(isPrimeNumber(n)? n+" is prime number." : n+" is not prime number.");
           n=13;
           System.out.println(isPrimeNumber(n)? n+" is prime number." : n+" is not prime number.");
           n=14;
           System.out.println(isPrimeNumber(n)? n+" is prime number." : n+" is not prime number.");
    }
    
    /**
     * returns true if number is prime.
     */
    public static boolean isPrimeNumber(int n){
                  
           for(int i=2;i<=Math.sqrt(n);i++){
                  if(n%i==0){
                        return false;
                  }
           }
           return true;  //means number wasn't divisible by any of the number, it's a prime number.
    }
 
}
/*OUTPUT
 
11 is prime number.
12 is not prime number.
13 is prime number.
14 is not prime number.
 
 
*/


