package com.bytetrend.sandbox.java.hackerrank;

import java.util.Scanner;

public class PlusMinus {

    /**
     * https://www.hackerrank.com/challenges/plus-minus
     * Given an array of integers, calculate which fraction of its elements are positive, which fraction of its elements are negative, and which fraction of its elements are zeroes, respectively. Print the decimal value of each fraction on a new line.
     * <p>
     * Note: This challenge introduces precision problems. The test cases are scaled to six decimal places, though answers with absolute error of up to  are acceptable.
     * <p>
     * Input Format
     * <p>
     * The first line contains an integer, , denoting the size of the array.
     * The second line contains  space-separated integers describing an array of numbers .
     * <p>
     * Output Format
     * <p>
     * You must print the following  lines:
     * <p>
     * A decimal representing of the fraction of positive numbers in the array.
     * A decimal representing of the fraction of negative numbers in the array.
     * A decimal representing of the fraction of zeroes in the array.
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double zeros = 0.0;
        double positives = 0.0;
        double negatives = 0.0;
        for (int i = 0; i < n; i++) {
            int z = in.nextInt();
            if (z == 0)
                zeros += 1;
            else if (z > 0)
                positives += 1;
            else
                negatives += 1;
        }
        System.out.println(String.format("%1.6f\n%1.6f\n%1.6f\n", positives / n, negatives / n, zeros / n));
    }
}
