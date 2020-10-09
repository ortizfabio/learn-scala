package com.bytetrend.sandbox.java.algo;

import java.util.Arrays;

/*
https://leetcode.com/problems/sort-colors/
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects
of the same color are adjacent, with the colors in the order red, white, and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
 */
public class Sort3Colors {

    static public void swap(int nums[], int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    static public void sortColors(int[] nums) {

        int redIndex = -1, blueIndex = nums.length, i = 0;
        while (i < blueIndex) {
            if (nums[i] == 0) {
                redIndex++;
                swap(nums, redIndex, i);
                i++;
            } else if (nums[i] == 2) {
                blueIndex--;
                swap(nums, i, blueIndex);
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{2, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{2, 0, 1, 1, 0, 2};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{0, 0, 0, 0, 0, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{2, 2, 2, 2, 2, 2};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{1, 1, 1, 1, 1, 1};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{0, 1, 0, 0, 1, 1};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{2, 1, 2, 1, 1, 2};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{2, 0, 2, 0, 0, 2};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{1, 2, 2, 0, 0, 1};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

}
