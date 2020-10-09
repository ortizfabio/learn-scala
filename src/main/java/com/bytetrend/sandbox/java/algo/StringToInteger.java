package com.bytetrend.sandbox.java.algo;

import static java.lang.StrictMath.pow;

public class StringToInteger {
    public static double strToNum(char[] arr) {
        double value = 0;
        int d = 1;
        boolean p = false;
        int s = 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-')
                s = -1;
            else if (arr[i] == '.')
                p = true;
            else {
                if (p)
                    d *= 10;
                value = value * (int) pow(10, 1) + (arr[i] - 48);
                System.out.println(value);
            }
        }
        return value / d * s;

    }

    public static void main(String[] a) {
        System.out.println(strToNum("-48982.90".toCharArray()));
    }
}
