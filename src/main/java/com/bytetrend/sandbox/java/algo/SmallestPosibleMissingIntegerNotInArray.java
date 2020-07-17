package com.bytetrend.sandbox.java.algo;

import java.util.Arrays;

/**
 * Write a function:
 *
 *     class Solution { public int solution(int[] A); }
 *
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
 *
 * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
 *
 * Given A = [1, 2, 3], the function should return 4.
 *
 * Given A = [−1, −3], the function should return 1.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 *         N is an integer within the range [1..100,000];
 *         each element of array A is an integer within the range [−1,000,000..1,000,000].
 */
public class SmallestPosibleMissingIntegerNotInArray {
    public int solution(int[] A) {
        int min = 1;
        Arrays.sort(A);
        for(int i =0; i < A.length; i++){
            if(A[i] > 0 && A[i] == min){
                min++;
            }
        }
        // write your code in Java SE 8
        return min;
    }
    public static void main(String[] args){
        SmallestPosibleMissingIntegerNotInArray test = new SmallestPosibleMissingIntegerNotInArray();
        int min = test.solution(new int[]{1,3,6,4,1,2});
        System.out.println(min+" Should equal 5 ");
    }
}
