package com.bytetrend.sandbox.java.hackerrank;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;

/**
 * Given a time in -hour AM/PM format, convert it to military (-hour) time.
 * <p>
 * Note: Midnight is  on a -hour clock, and  on a -hour clock. Noon is  on a -hour clock, and  on a -hour clock.
 * <p>
 * Input Format
 * <p>
 * A single string containing a time in -hour clock format (i.e.:  or ), where  and .
 * <p>
 * Output Format
 * <p>
 * Convert and print the given time in -hour format, where .
 * <p>
 * Sample Input
 * <p>
 * 07:05:45PM
 * Sample Output
 * <p>
 * 19:05:45
 */
public class TimeConversion {

    public static void main(String[] args) {
        Scanner sc = new java.util.Scanner(System.in);
        String time = sc.next();
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("hh:mm:ssa");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        TemporalAccessor date = inputFormat.parse(time);
        System.out.println(outputFormat.format(date));
    }
}
