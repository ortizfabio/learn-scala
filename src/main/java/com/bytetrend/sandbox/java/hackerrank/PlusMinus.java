package com.bytetrend.sandbox.java.hackerrank;

import java.util.Scanner;

public class PlusMinus {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double zeros = 0.0;
        double positives = 0.0;
        double negatives = 0.0;
        for ( int i = 0; i < n; i++) {
            int z = in.nextInt();
            if (z == 0)
                zeros += 1;
            else if (z > 0)
                positives += 1;
            else
                negatives += 1;
        }
        System.out.println(String.format("%1.6f\n%1.6f\n%1.6f\n",positives/n,negatives/n,zeros/n));
    }
}
