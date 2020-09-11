package com.bytetrend.sandbox.java.algo;

public class MultiplyTwoInts {

    public static int bitMultiply(int x, int y) {
        int res = 0;

        while (y > 0) {
            //If the first number becomes odd
            //Add the first number to the result
            if ((y & 1) != 0)
                res += x;
            x = x << 1; //Double the first number
            y = y >> 1; //Half the second number
        }
        return res;
    }

    public static int multiply(int x, int y) {
        if (y == 0) {
            return 0;
        } else if (y > 0) {
            return x + multiply(x, y - 1);
        } else {
            return -multiply(x, -y);
        }
    }

    public static void main(String[] args) {
        System.out.println(String.format("%d", multiply(4, 3)));
        System.out.println(String.format("%d", multiply(5, -2)));
        System.out.println(String.format("%d", multiply(-5, -2)));

        System.out.println(String.format("%d", bitMultiply(4, 3)));
        System.out.println(String.format("%d", bitMultiply(5, -2)));
        System.out.println(String.format("%d", bitMultiply(-5, 2)));

    }
}
