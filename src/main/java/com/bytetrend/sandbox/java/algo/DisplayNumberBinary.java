package com.bytetrend.sandbox.java.algo;

public class DisplayNumberBinary {
    /**
     * in binary if we divide by 2. When you divide by
     * 2, the remainder is the rightmost digit, either 0 or 1. If you divide the result
     * again, you get the second rightmost digit. If you keep going, and write
     * down the remainders, you’ll have your number in binary:
     * 23 / 2  is 11 remainder 1
     * 11 / 2  is 5 remainder 1
     * 5 / 2  is 2 remainder 1
     * 2 / 2  is 1 remainder 0
     * 1 / 2  is 0 remainder 1
     * Reading these remainders from bottom to top, 23 in binary is 10111.
     * @param value
     */
    public static void displayBinary(int value) {
        if (value > 0) {
            displayBinary(value / 2);
            System.out.print(value % 2);
        }
    }
    public static void displayDecimal(int value){
        if(value > 0){
            displayDecimal(value /10);
            System.out.print(value % 10);
        }
    }

    public static void main(String[] args) {
        displayBinary(23);
        System.out.println("");
        displayDecimal(23);
    }
}
