package com.bytetrend.sandbox.java.sort;

import java.util.Arrays;


public class InsertionSort {

    static final int[] arrayInt = new int[]{0, 4, 2, 3, 1, 5};

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    static public void sort(int[] arr) {
        System.out.println("0) " + Arrays.toString(arr));
        for (int j = 1; j < arr.length; j++) {
            int i = j - 1;
            int key = arr[j];
            while (i > 0 && arr[i] > key) {
                arr[i + 1] = arr[i];
                arr[i] = key;
                i--;
                System.out.println(j + ") " + key + " -> " + Arrays.toString(arr));
            }
        }

    }

    static public void main(String args[]) {
        sort(arrayInt);
    }

    static public void sort2(int[] arr) {
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
}
