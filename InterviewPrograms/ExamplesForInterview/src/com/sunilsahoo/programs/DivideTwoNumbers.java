package com.sunilsahoo.programs;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit
 * signed integer range: [−231, 231 − 1]. For this problem, assume that your function returns 231 − 1 when the division result overflows.
 */
public class DivideTwoNumbers {
    public static void main(String[] args) {
        DivideTwoNumbers divideTwoNumbers = new DivideTwoNumbers();
        int result = divideTwoNumbers.divide(-2147483648, -1);
        System.out.println(result);
        result = -2147483648/-1;
        System.out.println(result);
    }

    public int divide(int dividend, int divisor) {
        boolean isPlus = true;

        if(dividend<0|| divisor<0){

            if(dividend<0 && divisor<0){
                isPlus = true;
            }else{
                isPlus = false;
            }
            if(dividend<0){
                dividend = 0-dividend;
            }
            if(divisor<0){
                divisor = 0-divisor;
            }
        }
        if(divisor ==0){
            return 0;
        }

        int count = 0;
        while(true){
            if(dividend <divisor){
                if(isPlus){
                    return count;
                }else{
                    return 0-count;
                }
            }
            dividend = dividend-divisor;
            count++;
        }
    }
}
