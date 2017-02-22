package com.bytetrend.sandbox.java.hackerrank;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-array?h_r=next-challenge&h_v=zen
 * <p>
 * Watson gives Sherlock an array  of length n. Then he asks him to determine if
 * there exists an element in the array such that the sum of the elements on
 * its left is equal to the sum of the elements on its right. If there are no
 * elements to the left/right, then the sum is considered to be zero.
 * Formally, find an i, such that, A0 + A1 + ... +Ai-1 = Ai+1 + Ai+2+...+An-1.
 * <p>
 * Input Format
 * <p>
 * The first line contains T, the number of test cases. For each test case, the
 * first line contains n, the number of elements in the array A. The second
 * line for each test case contains  space-separated integers, denoting the array A.
 * Constraints
 *
 * Output Format
 * <p>
 * For each test case print YES if there exists an element in the array, such that
 * the sum of the elements on its left is equal to the sum of the elements on
 * its right; otherwise print NO.
 * <p>
 * Sample Input 0
 * <p>
 * 2
 * 3
 * 1 2 3
 * 4
 * 1 2 3 3
 * Sample Output 0
 * <p>
 * NO
 * YES
 * Explanation 0
 * <p>
 * For the first test case, no such index exists.
 * For the second test case, , therefore index  satisfies the given conditions.
 */
public class SherlockAndArray {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int x = 0; x < t; x++) {
            int n = in.nextInt();
            int[] a = new int[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                sum += a[i];
            }
            int currentSum = 0;
            boolean found = false;
            for (int j = 0; j < a.length; j++) {
                if (2 * currentSum + a[j] == sum) {
                    found = true;
                    break;
                }
                currentSum += a[j];
            }
            if (found)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
