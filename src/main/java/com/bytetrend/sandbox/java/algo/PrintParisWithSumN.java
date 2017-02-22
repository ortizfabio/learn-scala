package com.bytetrend.sandbox.java.algo;


import java.util.HashSet;
import java.util.Set;

public class PrintParisWithSumN {

    public static void calc(int[] array, int value) {

        Set<Integer> set = new HashSet();
        for (Integer x : array)
            set.add(x);
        for (Integer x : array) {
            int i = value - x;
            if (set.contains(i))
                System.out.println(String.format("%d %d", x, value - x));
        }
    }

    public static void main(String[] args) {
        int[] array = {3, 7, 2, 5, 6, 4};
        calc(array, 10);
    }

}
