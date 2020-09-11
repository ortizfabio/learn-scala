package com.bytetrend.sandbox.java.leetcode;

public class DecodeWays2 {
    public static int numDecodings(char[] chars, int idx, Integer[] dp){
        if(idx == chars.length) return 1;
        if(chars[idx] == '0') return 0;
        if(idx == chars.length - 1) return 1;

        if(dp[idx] != null ) return dp[idx];
        if(chars[idx] == '1' || (chars[idx] == '2' && chars[idx+1] <= '6' )){
            return dp[idx] = numDecodings(chars, idx+2,dp)+ numDecodings(chars,idx+1,dp);
        }else {
            return dp[idx] = numDecodings(chars,idx+1,dp);
        }
    }


    public static int numDecodings(String s) {
        char[] chars = s.toCharArray();
        Integer[] dp = new Integer[chars.length];
        return numDecodings(chars,0,dp);
    }


    public static void main(String[] args){
        String m = "12";
        int count = numDecodings(m);
        System.out.println(m+" Should be 2 "+count);

        m = "226";
        count = numDecodings(m);
        System.out.println(m+" Should be 3 "+count);
        m = "1234";
        count =numDecodings(m);
        System.out.println(m+" Should be 3 "+count);
        m = "000";
        count =numDecodings(m);
        System.out.println(m+" Should be 0 "+count);
        m = "010";
        count =numDecodings(m);
        System.out.println(m+" Should be 1 "+count);
        m = "";
        count =numDecodings(m);
        System.out.println(m+" Should be 0 "+count);
        m = null;
        count =numDecodings(m);
        System.out.println(m+" Should be 0 "+count);

    }
}
