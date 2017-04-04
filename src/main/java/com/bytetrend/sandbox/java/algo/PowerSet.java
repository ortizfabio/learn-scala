package com.bytetrend.sandbox.java.algo;

import java.util.Arrays;
import java.util.Stack;

/**
 * http://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
 * complexity 2^n * n
 */
public class PowerSet {

    static void printSubsets(char set[]) {
        int n = set.length;
        int solutions = (1<<n);
        // Run a loop for printing all 2^n
        // subsets one by obe
        for (int i = 0; i < solutions; i++) {
            System.out.print("{ ");
            // Print current subset
            for (int j = 0; j < n; j++) {
                int mask = (1 << j);
                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if ((i & mask) > 0)
                    System.out.print(set[j] + " ");
            }
            System.out.print("}");
        }
    }

    public static void main(String[] args){
       String input = (new java.util.Scanner(System.in)).nextLine();
       Stack<Character> result = new Stack<>();
       printSubsets(input.toCharArray());
//       System.out.println(Arrays.toString(result.toArray()));
    }
}
