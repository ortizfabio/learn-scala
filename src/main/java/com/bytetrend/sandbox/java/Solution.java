package com.bytetrend.sandbox.java;
class Solution {
    public static int solution(int[] A) {
        // write your code in Java SE 8
        int count = 1;
        int i = 0;
        int next = 0;
        while(A[next] != -1){
            next = A[next];
            count++;
        }
        return count;
    }

    public static void main(String[] args){
        int[] A = new int[]{1,4,-1,3,2};
        int result = solution(A);
        System.out.println(result);
    }
}
