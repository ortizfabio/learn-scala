package com.bytetrend.sandbox.java.leetcode;

/**
 * https://leetcode.com/problems/decode-ways/
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class DecodeWays {
    public static int numDecodings(char[] chars, int idx, int acc){

        if(idx >= chars.length)
            return acc;
        if(chars[idx] != '0') {
            if ((idx + 1 < chars.length) && (chars[idx] == '1' || (chars[idx] == '2' && chars[idx + 1] <= '6'))) {
                acc = numDecodings(chars, idx + 2, acc + 1) + numDecodings(chars, idx + 1,0);
            } else {
                acc = numDecodings(chars, idx + 1, 0);
            }
            if (acc == 0)
                acc = 1;
        }else{
            //Ignore characer '0' there is no mapping for it.
            acc = numDecodings(chars, idx + 1, acc);
        }
        return acc;
    }


    public static int numDecodings(String s) {
        if(s == null || s == "" || s.charAt(0) == '0')
            return  0;
        return numDecodings(s.toCharArray(),0,0);
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
        m = "10";
        count =numDecodings(m);
        System.out.println(m+" Should be 1 "+count);
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
