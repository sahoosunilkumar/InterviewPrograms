package com.sunilsahoo.dynamicprogramming;

public class FibonacciSeries {

    private int calculateFibonacciUsingRecurssion(int n) {
        if (n < 2) {
            return n;
        }
        return calculateFibonacciUsingRecurssion(n - 1) + calculateFibonacciUsingRecurssion(n - 2);
    }

    private int calculateFibonacciUsingTopDownMemorization(int[] memory, int n) {
        if (n < 2) {
            return n;
        }
        return calculateFibonacciUsingTopDownMemorization(memory, n - 1) + calculateFibonacciUsingTopDownMemorization(memory, n - 2);
    }

    /** calculate fibonacci top down approach with memorization  **/
    private int calculateFibonacciUsingTopDownMemorization(int n) {
        int[] memory = new int[n+1];
        if (n < 2) {
            return n;
        }
        if(memory[n] != 0){
            return memory[n];
        }
        memory[n] = calculateFibonacciUsingTopDownMemorization(memory, n - 1) + calculateFibonacciUsingTopDownMemorization(memory, n - 2);
        return memory[n];

    }
    
    private int calculateFibanacciBottomUpTabulation(int n) {
        int[] table = new int[n+1];
        table[0] = 0;
        table[1] = 1;
        for(int i = 2; i<=n; i++){
            table[i] = table[i-1]+table[i-2];
        }
        return table[n];
    }

    public static void main(String[] args) {
        FibonacciSeries fibonacciSeries = new FibonacciSeries();
        System.out.println( " 5th Fibonacci is --->"+fibonacciSeries.calculateFibonacciUsingRecurssion(5));
        System.out.println( " 6th Fibonacci is --->"+fibonacciSeries.calculateFibonacciUsingRecurssion(6));
        System.out.println( " 7th Fibonacci is --->"+fibonacciSeries.calculateFibonacciUsingRecurssion(7));

        System.out.println("=============== TOP DOWN WITH MEMORIZATION =============");
        System.out.println( " 5th Fibonacci is --->"+fibonacciSeries.calculateFibonacciUsingTopDownMemorization(5));
        System.out.println( " 6th Fibonacci is --->"+fibonacciSeries.calculateFibonacciUsingTopDownMemorization(6));
        System.out.println( " 7th Fibonacci is --->"+fibonacciSeries.calculateFibonacciUsingTopDownMemorization(7));

        System.out.println("=============== BOTTOM UP TABULATION =============");
        System.out.println( " 5th Fibonacci is --->"+fibonacciSeries.calculateFibanacciBottomUpTabulation(5));
        System.out.println( " 6th Fibonacci is --->"+fibonacciSeries.calculateFibanacciBottomUpTabulation(6));
        System.out.println( " 7th Fibonacci is --->"+fibonacciSeries.calculateFibanacciBottomUpTabulation(7));
    }
}