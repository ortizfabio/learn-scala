package com.bytetrend.sandbox.java.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 3. Longest Substring Without Repeating Characters
 * Medium
 * <p>
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Example 2:
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * Example 3:
 * <p>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestNonRepeatingSubstring {

    public static String find1(String input) {
        String currentLongest = input.length() > 0 ? "" + input.charAt(0) : "";
        String tmp = currentLongest;
        for (int i = 1; i < input.length(); i++) {
            int index = tmp.indexOf("" + input.charAt(i));
            if (index == -1) {
                tmp = tmp + input.charAt(i);
                if (tmp.length() > currentLongest.length())
                    currentLongest = tmp;
            } else
                tmp = tmp.substring(index + 1) + input.charAt(i);
        }
        return currentLongest;
    }

    public static String find(String input) {
        String currentLongest = input.length() > 0 ? "" + input.charAt(0) : "";
        String tmp = currentLongest;
        for (int i = 1; i < input.length(); i++) {
            int index = tmp.indexOf("" + input.charAt(i));
            if (index == -1) {
                tmp = tmp + input.charAt(i);
                if (tmp.length() > currentLongest.length())
                    currentLongest = tmp;
            } else //we drop the characters up to the duplicate and keep the rest if any
                tmp = tmp.substring(index + 1) + input.charAt(i);
        }
        return currentLongest;
    }

    /**
     * Sliding Window
     * <p>
     * This solution requires at most 2n steps.
     * The reason is that if s[j]s[j]s[j] have a duplicate in the range [i,j)[i, j)[i,j) with index j′j'j′,
     * we don't need to increase iii little by little. We can skip all the elements in the range
     * [i,j′][i, j'][i,j′] and let iii to be j′+1j' + 1j′+1 directly.
     *
     * @param s
     * @return
     */
    public static int find2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0, ans = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else
                set.remove(s.charAt(i++)); //This keeps sliding till the index where the duplicate occurs thus 2n
        }
        return ans;
    }

    /**
     * Complexity Analysis
     * <p>
     * Time complexity : O(n)O(n)O(n). Index jjj will iterate nnn times.
     * <p>
     * Space complexity (HashMap) : O(min(m,n))O(min(m, n))O(min(m,n)). Same as the previous approach.
     * <p>
     * Space complexity (Table): O(m)O(m)O(m). mmm is the size of the charset.
     *
     * @param s
     * @return
     */
    public static int find3(String s) {
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0, ans = 0;
        while (i < n && j < n) {
            char c = s.charAt(j);
            Integer e = map.get(c);
            if (e != null) {
                //Remove current entry for repeated character
                map.remove(c);
                //increment i to the next character after duplicate
                //But it can't be less than current i it happens here:
                //aabaab!bb first b at 2 when i is at 4 it should never set i = 3
                //Just keep i = 4
                i = Math.max(i, e + 1);
            }
            map.put(c, j++); //Insert new character and index
            ans = Math.max(ans, j - i);

        }
        return ans;
    }

    public static void main(String[] args) {
        String result = find("abcabcbb");
        System.out.println(String.format("result length should be: %d == 3 %s == abc %d", result.length(), result, find3("abcabcbb")));
        result = find("aabaab!bb");
        System.out.println(String.format("result length should be: %d == 3 %s == ab! %d", result.length(), result, find3("aabaab!bb")));
        result = find("dvdf");
        System.out.println(String.format("result length should be: %d == 3 %s == vdf %d", result.length(), result, find3("dvdf")));
        result = find("au");
        System.out.println(String.format("result length should be: %d == 2 %s == au %d", result.length(), result, find3("au")));
        result = find("bbbbb");
        System.out.println(String.format("result length should be: %d == 1 %s == b %d", result.length(), result, find3("bbbbb")));
        result = find("pwwkew");
        System.out.println(String.format("result length should be: %d == 3 %s == wke %d", result.length(), result, find3("pwwkew")));
        result = find("a");
        System.out.println(String.format("result length should be: %d == 1 %s == a %d", result.length(), result, find3("a")));
    }
}
