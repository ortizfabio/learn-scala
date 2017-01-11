package com.bytetrend.sandbox.java.hackerrank;

import java.util.Scanner;

/**
 * Given a square matrix of size N x N , calculate the absolute difference between the sums of its diagonals.
 * <p>
 * Input Format
 * <p>
 * The first line contains a single integer, N. The next N lines denote the matrix's rows, with each line containing N space-separated integers describing the columns.
 * <p>
 * Output Format
 * <p>
 * Print the absolute difference between the two sums of the matrix's diagonals as a single integer.
 * <p>
 * Sample Input
 * <p>
 * 3
 * 11 2 4
 * 4 5 6
 * 10 8 -12
 * Sample Output
 * <p>
 * 15
 * Explanation
 * <p>
 * The primary diagonal is:
 * 11
 * 5
 * -12
 * <p>
 * Sum across the primary diagonal: 11 + 5 - 12 = 4
 * <p>
 * The secondary diagonal is:
 * 4
 * 5
 * 10
 * Sum across the secondary diagonal: 4 + 5 + 10 = 19
 * Difference: |4 - 19| = 15
 */
public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int topRighDown = 0;
        int bottomRightUp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int z = in.nextInt();
                if(i == j)
                    topRighDown += z;
                if(j + i == n - 1){
                    bottomRightUp += z;
                }
            }
        }
        System.out.println(Math.abs(bottomRightUp-topRighDown));
    }
}


