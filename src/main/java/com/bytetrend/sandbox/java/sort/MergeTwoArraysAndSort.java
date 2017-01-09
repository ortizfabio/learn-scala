package com.bytetrend.sandbox.java.sort;

import java.util.Arrays;
import java.util.stream.IntStream;


public class MergeTwoArraysAndSort {
    static void swap(int[] array,int i,int j){
        int temp = array[i]; array[i] = array[j];array[j]= temp;
    }

    static int[] sort(int[] array){
        for(int i = 1; i < array.length; i++){
            int j = i;
            while(j>0 && array[j] < array[j-1]){
                swap(array,j,j-1);
                j--;
            }
        }
        return array;
    }
    public static int[] streamMergeAndSort(int[] arrayOne, int[] arrayTwo){
        return sort(IntStream.range(0, arrayOne.length+arrayTwo.length).map( i -> (i < arrayOne.length)?arrayOne[i]:arrayTwo[i-arrayOne.length]).toArray());
    }

    public static int[] mergeAndSort(int[] arrayOne, int[] arrayTwo){
        int[] result = new int[arrayOne.length+arrayTwo.length];
        for(int i = 0; i < result.length; i++){
           result[i]= (i < arrayOne.length)?arrayOne[i]:arrayTwo[i-arrayOne.length];
        }
        sort(result);
        return result;
    }

    public static void main(String[] args){
        int[] arrayOne = new int[]{3,4,9,0,1,2,4};
        int[] arrayTwo = new int[]{5,4,1,0,9,8};

        System.out.println(Arrays.toString(mergeAndSort(arrayOne,arrayTwo)));
        System.out.println(Arrays.toString(streamMergeAndSort(arrayOne,arrayTwo)));
    }
}
