package com.bytetrend.sandbox.java.algo;


public class LongestPalindromeInString {
    public static String findBiggestPalindromeSubstring(String input) {
        String tmp;
        String palindrome = "";
        for (int i = 0; i < input.length() - 1; i++) {
            // Even palindrome when two consecutive characters are the same.
            if (input.charAt(i) == input.charAt(i + 1)) {
                for (int j = i, k = i + 1; j >= 0 && k < input.length(); j--, k++) {
                    if (input.charAt(j) == input.charAt(k)) {
                        tmp = input.substring(j, k + 1);
                        if (tmp.length() > palindrome.length())
                            palindrome = tmp;
                    } else
                        break;
                }
            }
            // Odd palindrome when two characters around the current one are the same.
            if (i > 0 && input.charAt(i - 1) == input.charAt(i + 1)) {
                for (int j = i - 1, k = i + 1; j >= 0 && k < input.length(); j--, k++) {
                    if (input.charAt(j) == input.charAt(k)) {
                        tmp = input.substring(j, k + 1);
                        if (tmp.length() > palindrome.length())
                            palindrome = tmp;
                    } else
                        break;
                }
            }
        }
        return palindrome;
    }

    public static String longestPal2(String a) {
        // create new array to hold results
        int[][] arr = new int[a.length() + 1][a.length() + 1];
        // reverse   String a
        String b = new StringBuffer(a).reverse().toString();

        // complete the Longest common subsequence array
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                char achar = a.charAt(i);
                char bchar = b.charAt(j);
                if (achar == bchar) {
                    arr[i + 1][j + 1] = arr[i][j] + 1;
                } else {
                    arr[i + 1][j + 1] = Math.max(arr[i + 1][j], arr[i][j + 1]);
                }
            }
        }
        String str = "";
        int x = a.length();
        int y = b.length();
        //trace back form bottom of LCS array
        while (x > 0 && y > 0) {
            if (arr[x][y] == arr[x - 1][y]) {
                x--;
            } else if (arr[x][y] == arr[x][y - 1]) {
                y--;
            } else {
                if (a.charAt(x - 1) == b.charAt(y - 1)) {
                    str += a.charAt(x - 1);
                    x--;
                    y--;
                }
            }
        }
        // return the largest palindrome
        return str;
    }

    public static void main(String[] args) {
        String input = "aaabbaaaccdeqjncsdddmmmkkkmmmddd";
        String answer = "dddmmmkkkmmmddd";
        LongestPalindromeInString pal = new LongestPalindromeInString();
        String resp = pal.longestPal2(input);
        System.out.println(resp.equals(answer));
        String resp2 = pal.findBiggestPalindromeSubstring(input);
        System.out.println(resp2.equals(answer));
    }
}
