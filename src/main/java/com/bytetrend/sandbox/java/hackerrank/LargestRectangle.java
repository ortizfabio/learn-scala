package com.bytetrend.sandbox.java.hackerrank;

import java.util.Scanner;
import java.util.Stack;

/**
 * http://www.programcreek.com/2014/05/leetcode-largest-rectangle-in-histogram-java/
 */
public class LargestRectangle {

    static public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int i = 0;

        while (i < height.length) {
            //push index to stack when the current height is larger than the previous one
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                //calculate max value when the current height is less than the previous one
                int p = stack.pop();
                int h = height[p];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(h * w, max);
            }
        }

        while (!stack.isEmpty()) {
            int p = stack.pop();
            int h = height[p];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1;
            max = Math.max(h * w, max);
        }

        return max;
    }

    public static int largestRectangleArea2(int[] hist) {
        Stack<Integer> posStk = new Stack<>();
        Stack<Integer> hghtStk = new Stack<>();
        int insertPos = 0;
        int cHeight = 0;
        int potMax =0;
        int maxA = 0;

        for (int i = 0; i <= hist.length; i++) {
            insertPos = i;
            cHeight = (i != hist.length) ? hist[i] : 0;

            while (!posStk.isEmpty() && hghtStk.peek() > cHeight) {
                insertPos = posStk.pop();
                potMax = (i - insertPos) * hghtStk.pop();
                maxA = Math.max(maxA,potMax);
            }
            if (hghtStk.isEmpty() || hghtStk.peek() != cHeight) {
                posStk.push(insertPos);
                hghtStk.push(cHeight);
            }
        }
        return maxA;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        System.out.println(largestRectangleArea(a));
    }
}
