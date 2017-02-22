package com.bytetrend.sandbox.java.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Consider a staircase of size n=4:
 * <p>
 *    #
 *   ##
 *  ###
 * ####
 * Observe that its base and height are both equal to n, and
 * the image is drawn using # symbols and spaces.
 * The last line is not preceded by any spaces.
 * <p>
 * Write a program that prints a staircase of size n.
 * <p>
 * Input Format
 * <p>
 * A single integer, , denoting the size of the staircase.
 */
public class Staircase {
    public static void draw(int n) {
        byte[] blank = new byte[n];
        Arrays.fill(blank, (byte) 0x20);
        for (int i = n - 1; i >= 0; i--) {
            Arrays.fill(blank, i, i + 1, (byte) '#');
            System.out.println(new String(blank));
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        draw(n);
    }
}
