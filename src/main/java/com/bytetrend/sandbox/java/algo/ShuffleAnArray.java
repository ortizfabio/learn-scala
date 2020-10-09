package com.bytetrend.sandbox.java.algo;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Uses method https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_%22inside-out%22_algorithm
 * To shuffle an array
 */
public class ShuffleAnArray {
    static private Random r = new Random();
    public static void swap(int[] arr,int m, int n){
        int t = arr[m];
        arr[m] = arr[n];
        arr[n] = t;
    }

    public static void shuffle(int[] arr){
        for(int i =  arr.length-1; i > 0; i--){
            int j = r.nextInt(i);
            swap(arr,i,j);
        }
    }

    public static void shuffle2(int[] arr){
        for(int i =  arr.length-1; i > 0; i--){
            int k = abs(r.nextInt());
            int j = k % i;
            swap(arr,i,j);
        }
    }

    public static void main(String[] args){
 //       for(int m = 0; m <= 16; m++)
 //           System.out.println(""+m+" % "+8+" = "+(m%8));
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        shuffle2(arr);
        System.out.println(Arrays.toString(arr));
    }
}
