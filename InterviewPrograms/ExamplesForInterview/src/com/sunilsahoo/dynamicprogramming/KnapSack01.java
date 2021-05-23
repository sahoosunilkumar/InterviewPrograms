package com.sunilsahoo.dynamicprogramming;

public class KnapSack01 {

    public static void main(String[] args){
        int val[] = {1,3,4,5};
        int wt[] = {1,4,5,7};
        int capacity = 7;
        int length = val.length;
        KnapSack01 knapSack01 = new KnapSack01();
        int result = knapSack01.knapSackRecurssive(val, wt, capacity, length);
        System.out.println(result);

        int table[][] = new int[length+1][capacity+1];
        knapSack01.fill(table, length+1, capacity+1, -1);
        result = knapSack01.knapSackRecursiveMemorization(val, wt, capacity, length, table);
        System.out.println(result);

        table = new int[length+1][capacity+1];
        result = knapSack01.knapSackTopDown(val, wt, capacity, length, table);
        System.out.println(result);

    }

    void fill(int table[][], int row, int column, int value){
        for (int i=0; i<row; i++){
            for (int j=0; j<column; j++){
                table[i][j]=value;
            }
        }
    }
    static void print(int table[][], int row, int column){
        for (int i=0; i<=row; i++){
            for (int j=0; j<=column; j++){
                System.out.print(table[i][j]+" ");
            }
            System.out.println();
        }
    }

    int knapSackRecurssive(int[] val, int[] wt, int capacity, int length) {
        //Base condition
        if (length == 0 || capacity == 0) {
            return 0;
        }
        if (wt[length - 1] <= capacity) {
            return Math.max(
                    (val[length - 1] + knapSackRecurssive(val, wt, capacity - wt[length - 1], length - 1)),
                    knapSackRecurssive(val, wt, capacity, length - 1)
            );
        } else {
            return knapSackRecurssive(val, wt, capacity, length - 1);
        }
    }

    int knapSackRecursiveMemorization(int[] val, int[] wt, int capacity, int length, int table[][]) {
        //Base condition
        if (length == 0 || capacity == 0) {
            return 0;
        }
        if (table[length][capacity] != -1){
            return table[length][capacity];
        }
        if (wt[length - 1] <= capacity) {
            return table[length][capacity] =Math.max(
                    (val[length - 1] + knapSackRecursiveMemorization(val, wt, capacity - wt[length - 1], length - 1, table)),
                    knapSackRecursiveMemorization(val, wt, capacity, length - 1, table)
            );
        } else {
            return table[length][capacity] =knapSackRecursiveMemorization(val, wt, capacity, length - 1, table);
        }
    }

    int knapSackTopDown(int[] val, int[] wt, int capacity, int length, int table[][]) {
        //Base condition
        for(int i=0; i<=length;i++){
            for (int j=0; j<=capacity; j++){
                if (i==0||j==0){
                    table[i][j]=0;
                }
            }
        }
        for (int i=1; i<=length; i++){
            for (int j=1; j<=capacity; j++){
                if (wt[i - 1] <= j) {
                     table[i][j] =Math.max(
                            (val[i - 1] + table[i-1][j-wt[i - 1]]),
                            table[i-1][j]
                    );
                } else {
                     table[i][j] =table[i - 1][j];
                }
            }
        }
        print(table, length, capacity);
        return table[length][capacity];
    }
}
