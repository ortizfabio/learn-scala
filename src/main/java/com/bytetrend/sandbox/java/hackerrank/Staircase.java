package com.bytetrend.sandbox.java.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

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
