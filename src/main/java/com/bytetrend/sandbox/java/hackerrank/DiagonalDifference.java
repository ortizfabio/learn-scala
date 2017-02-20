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
    //O(n^2)
    public static int calc1(int[][] array){
        int topRighDown = 0;
        int bottomRightUp = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(i == j)
                    topRighDown += array[i][j];
                if(j + i == array.length - 1){
                    bottomRightUp += array[i][j];
                }
            }
        }
        return Math.abs(bottomRightUp-topRighDown);
    }
    //O(n)
    public static int calc2(int[][] array){
        int topRighDown = 0;
        int bottomRightUp = 0;
        for(int i =0; i< array.length; i++){
            topRighDown += array[i][i];
            bottomRightUp += array[i][(array.length-1)-i];
        }
        return Math.abs(bottomRightUp-topRighDown);
    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               array[i][j] = in.nextInt();
            }
        }
        System.out.println(calc1(array));
        System.out.println(calc2(array));
    }
}


