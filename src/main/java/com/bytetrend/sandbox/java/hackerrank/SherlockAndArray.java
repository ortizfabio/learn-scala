package com.bytetrend.sandbox.java.hackerrank;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-array?h_r=next-challenge&h_v=zen
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
