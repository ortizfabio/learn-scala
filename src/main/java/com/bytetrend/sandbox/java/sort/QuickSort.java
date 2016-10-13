package com.bytetrend.sandbox.java.sort;

import java.util.Arrays;

/**
 * Created by db2admin on 5/23/2016.
 */
public class QuickSort {
    static int[] arrInt = new int[]{5, 9, 0, 2, 4, 8, 7, 7, 3};

    private static void swap(int[] arr, int i, int j) {
        System.out.println("swapping a["+i+"]="+arr[i]+" with a["+j+"]="+arr[j]);
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    public static void quicksort(int[] arr, int lo, int hi) {
        int i = lo;
        int j = hi;
        int pivot = arr[lo + (hi-lo)/2];
        while( i <= j){
            System.out.println("pivot="+pivot+" i="+i+" j="+j);
            while(arr[i] < pivot) i++;
            while(arr[j] > pivot) j--;
            if (i <= j) {
                swap(arr,i,j);
                i++;
                j--;
            }
        }
        if(lo < j)
            quicksort(arr,lo,j);
        if(hi>i)
            quicksort(arr,i,hi);
    }

    static public void main(String[] args){
        System.out.println(Arrays.toString(arrInt));
        quicksort(arrInt,0,arrInt.length-1);
        System.out.println(Arrays.toString(arrInt));
    }
}