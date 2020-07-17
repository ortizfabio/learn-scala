package com.bytetrend.sandbox.java.hackerrank;

import java.util.Scanner;

/**
 * John Watson performs an operation called a right circular rotation on an array of integers, .
 * After performing one right circular rotation operation, the array is transformed from  to .
 * <p>
 * Watson performs this operation  times. To test Sherlock's ability to identify the current
 * element at a particular position in the rotated array, Watson asks  queries, where each query
 * consists of a single integer, , for which you must print the element at index  in the rotated a
 * rray (i.e., the value of ).
 * <p>
 * Input Format
 * <p>
 * The first line contains  space-separated integers, , , and , respectively.
 * The second line contains  space-separated integers, where each integer  describes array element  (where ).
 * Each of the  subsequent lines contains a single integer denoting .
 * <p>
 * Constraints
 * <p>
 * Output Format
 * <p>
 * For each query, print the value of the element at index  of the rotated array on a new line.
 * <p>
 * Sample Input
 * <p>
 * 3 2 3
 * 1 2 3
 * 0
 * 1
 * 2
 * Sample Output
 * <p>
 * 2
 * 3
 * 1
 * Explanation
 * <p>
 * After the first rotation, the array becomes .
 * After the second (and final) rotation, the array becomes [3, 1, 2].
 * <p>
 * Let's refer to the array's final state as array b. For each query, we just have to print
 * the value of bm on a new line:
 * <p>
 * m = 0, so we print 2 on a new line.
 * m = 1, so we print 3 on a new line.
 * m = 2, so we print 1 on a new line.
 */
public class CircularArrayRotation {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        for (int a0 = 0; a0 < q; a0++) {
            int m = in.nextInt();
            int index = (m - (k % n) + n) % n;
            System.out.println(a[index]);

        }
    }
}
