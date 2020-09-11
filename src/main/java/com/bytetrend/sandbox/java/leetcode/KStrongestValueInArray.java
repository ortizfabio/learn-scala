package com.bytetrend.sandbox.java.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/the-k-strongest-values-in-an-array/
 * Given an array of integers arr and an integer k.
 * <p>
 * A value arr[i] is said to be stronger than a value arr[j] if |arr[i] - m| > |arr[j] - m| where m is the median of the array.
 * If |arr[i] - m| == |arr[j] - m|, then arr[i] is said to be stronger than arr[j] if arr[i] > arr[j].
 * <p>
 * Return a list of the strongest k values in the array. return the answer in any arbitrary order.
 * <p>
 * Median is the middle value in an ordered integer list. More formally, if the length of the list is n, the median is the element in position ((n - 1) / 2) in the sorted list (0-indexed).
 * <p>
 * For arr = [6, -3, 7, 2, 11], n = 5 and the median is obtained by sorting the array arr = [-3, 2, 6, 7, 11] and the median is arr[m] where m = ((5 - 1) / 2) = 2. The median is 6.
 * For arr = [-7, 22, 17, 3], n = 4 and the median is obtained by sorting the array arr = [-7, 3, 17, 22] and the median is arr[m] where m = ((4 - 1) / 2) = 1. The median is 3.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,3,4,5], k = 2
 * Output: [5,1]
 * Explanation: Median is 3, the elements of the array sorted by the strongest are [5,1,4,2,3]. The strongest 2 elements are [5, 1]. [1, 5] is also accepted answer.
 * Please note that although |5 - 3| == |1 - 3| but 5 is stronger than 1 because 5 > 1.
 * <p>
 * Example 2:
 * <p>
 * Input: arr = [1,1,3,5,5], k = 2
 * Output: [5,5]
 * Explanation: Median is 3, the elements of the array sorted by the strongest are [5,5,1,1,3]. The strongest 2 elements are [5, 5].
 * <p>
 * Example 3:
 * <p>
 * Input: arr = [6,7,11,7,6,8], k = 5
 * Output: [11,8,6,6,7]
 * Explanation: Median is 7, the elements of the array sorted by the strongest are [11,8,6,6,7,7].
 * Any permutation of [11,8,6,6,7] is accepted.
 * <p>
 * Example 4:
 * <p>
 * Input: arr = [6,-3,7,2,11], k = 3
 * Output: [-3,11,2]
 * <p>
 * Example 5:
 * <p>
 * Input: arr = [-7,22,17,3], k = 2
 * Output: [22,17]
 */
public class KStrongestValueInArray {
    static public int[] getStrongest(int[] arr, int k) {
        int[] result = new int[k];
        Arrays.sort(arr);
        int h = (arr.length - 1) / 2;
        int m = arr[h];
        int leftValue = 0, rightValue = 0;
        int leftIndesx = 0, rightIndex = arr.length - 1;
        for (int i = 0; i < k; i++) {
            leftValue = Math.abs((arr[leftIndesx] - m));
            rightValue = Math.abs(arr[rightIndex] - m);
            if (leftValue > rightValue)
                result[i] = arr[leftIndesx++];
            else if (rightValue > leftValue)
                result[i] = arr[rightIndex--];
            else if (arr[leftIndesx] > arr[rightIndex])
                result[i] = arr[leftIndesx++];
            else
                result[i] = arr[rightIndex--];
        }
        return result;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int k = 2;
        int[] result = getStrongest(arr, k);
        if (!Arrays.equals(result, new int[]{5, 1})) System.out.println("Failed");
        arr = new int[]{1, 1, 3, 5, 5};
        k = 2;
        result = getStrongest(arr, k);
        if (!Arrays.equals(result, new int[]{5, 5})) System.out.println("Failed");
        arr = new int[]{6,7,11,7,6,8};
        k = 5;
        result = getStrongest(arr, k);
        if (!Arrays.equals(result, new int[]{11,8,6,6,7})) System.out.println("Failed");
        arr = new int[]{6,-3,7,2,11};
        k = 3;
        result = getStrongest(arr, k);
        if (!Arrays.equals(result, new int[]{-3,11,2})) System.out.println("Failed");
        arr = new int[]{-7,22,17,3};
        k = 2;
        result = getStrongest(arr, k);
        if (!Arrays.equals(result, new int[]{22,17})) System.out.println("Failed");

    }
}
