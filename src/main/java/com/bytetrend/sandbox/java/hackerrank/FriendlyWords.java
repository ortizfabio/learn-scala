package com.bytetrend.sandbox.java.hackerrank;

import scala.actors.threadpool.Arrays;

import java.util.*;

/**
 * Two words are called friendly if the letters of the first can be rearranged to produce
 * the second. Write a program that given a set of words prings out all friendly words. Each
 * set of friendly words should be printed on a separate line. Words without friends should not be
 * printed. The outputs should be alphabetically ordered (across lines and within each line).
 *
 * Beware of bad input data (validate appropiatelly and return an empty array when it fails).
 * Beware of duplicated words on input (if a word appears multiple times on input it should appear
 * multiple times in output).
 */
public class FriendlyWords {


    public static void main(String[] args) {
        String[] input = new String[]{"car", "cheating", "dale", "deal", "lead", "listen", "silent", "teaching"};
        Map<String, List<String>> words = new TreeMap();
        for (int i = 0; i < input.length; i++) {
            char[] temp = input[i].toCharArray();
            Arrays.sort(temp);
            String key = new String(temp);
            List<String> l = words.getOrDefault(key, new ArrayList<String>());
            l.add(input[i]);
            words.putIfAbsent(key, l);
        }
        for( String key : words.keySet()){
            if(words.get(key).size() > 1) {
                Collections.sort(words.get(key));
                System.out.println(words.get(key));
            }
        }

    }

}
