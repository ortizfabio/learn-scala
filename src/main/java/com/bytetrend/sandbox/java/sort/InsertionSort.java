package com.bytetrend.sandbox.java.sort;

import java.util.Arrays;


public class InsertionSort {

    static final int[] arrayInt = new int[]{7, 7, 4, 0, 2, 1};

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static public void sort(int[] arr) {
        System.out.println("0) "+Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j-1] > arr[j]) {
                System.out.print(i+") ");
                swap(arr, j-1, j);
                j--;
            }
        }
        Arrays.toString(arr);
    }

    static public void main(String args[]) {
        sort(arrayInt);
    }
}
