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
        Stack<Integer> pStack = new Stack<>(), hStack = new Stack<>();
        int insertPos, cHeight, potMax, maxA = 0;

        for (int i = 0; (insertPos = i) <= hist.length; i++) {
            cHeight = (i != hist.length) ?  hist[i] : 0;

            while (!pStack.isEmpty() && hStack.peek() > cHeight)
                if ((potMax = (i - (insertPos = pStack.pop())) * hStack.pop()) >= maxA)
                    maxA = potMax;

            if (hStack.isEmpty() || hStack.peek() != cHeight) {
                pStack.push(insertPos);
                hStack.push(cHeight);
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
        System.out.println(largestRectangleArea2(a));
    }
}
