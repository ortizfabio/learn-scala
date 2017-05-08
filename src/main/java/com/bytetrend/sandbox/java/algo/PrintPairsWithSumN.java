package com.bytetrend.sandbox.java.algo;


import java.util.HashSet;
import java.util.Set;

/**
 * Count pairs with given sum
 * Given an array of integers, and a number ‘sum’, find the number
 * of pairs of integers in the array whose sum is equal to ‘sum’.
 *http://www.geeksforgeeks.org/count-pairs-with-given-sum/
 */
public class PrintPairsWithSumN {

    public static void calc(int[] array, int sum) {
        int count = 0;
        Set<Integer> set = new HashSet();
        for (Integer x : array)
            set.add(x);
        for (Integer x : array) {
            int i = sum - x;
            if (set.contains(i)) {
                System.out.println(String.format("%d %d", x, sum - x));
                count++;
            }
        }
        System.out.println("Found pairs: "+count);
    }

    public static void main(String[] args) {
        int[] array = {3, 7, 2, 5, 6, 4};
        calc(array, 10);

        int arr[] = {1, 5, 7, -1, 5} ;
        calc(arr,6);
    }

}
