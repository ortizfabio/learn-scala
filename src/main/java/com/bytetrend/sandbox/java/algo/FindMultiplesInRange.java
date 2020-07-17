package com.bytetrend.sandbox.java.algo;

import static java.lang.System.out;

/**
 * Return the number of integers within the range [A..B] that are divisible by K.
 * <p>
 * Args:
 * <p>
 * A: is an integer within the range [0..2,000,000,000]
 * B: is an integer within the range [0..2,000,000,000] and A <= B
 * K: is an integer within the range [1..2,000,000,000]
 * <p>
 * Time complexity must be O(1).
 */
public class FindMultiplesInRange {

    public static int solution(int start, int end, int k) {
        int part1 = (end / k); //number of integers divisible from 1 to end
        int part2 = (start / k);//number of integers divisible from 1 to start
        int part3 = (start % k); //If start is divisible i.e == 0 add 1 to total
        int count = (part1 - part2) + (part3 == 0 ? 1 : 0);
        return count;
    }

    public static void main(String[] args) {
        out.println(solution(0, 0, 1));// = 1 // 0
        out.println(solution(0, 1, 1));// = 2 // 0, 1
        out.println(solution(0, 5, 2));// = 3 // 0, 2, 4
        out.println(solution(2, 10, 3));// = 3 // 3, 6, 9
        out.println(solution(3, 9, 3));// = 3 // 3, 6, 9
    }
}
