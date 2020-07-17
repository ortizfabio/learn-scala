package com.bytetrend.sandbox.java.challenge;

import java.util.Arrays;

/**
 * Find out middle index where sum of both ends are equal.
 * Find the index of the element for which the sum of the elements so far is equal to half the total
 */
public class FindMiddleElement {
    public static int findMiddleElement(int[] arr){
        int totalSum = Arrays.stream(arr).sum();
        int currSum = 0;
        for(int i = 0;i < arr.length; i++) {
            if (2 * currSum == totalSum - arr[i])
                return i;
            else
                currSum += arr[i];
        }
        return -1;
    }

    public static void main(String[] args){
        int[] array = new int[]{4, 2, 4, 1, 5, 3, 2};
        System.out.println("Should be 3 = "+findMiddleElement(array));
        array = new int[]{4, 3, 5, 3, 2, 15};
        System.out.println("Should be 4 = "+findMiddleElement(array));
        array = new int[]{0, 1, 3, 5, 1, 0, 3, 3, 4};
        System.out.println("Should be 5 = "+findMiddleElement(array));
    }
}
