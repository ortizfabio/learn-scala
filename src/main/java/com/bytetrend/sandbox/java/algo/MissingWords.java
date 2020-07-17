package com.bytetrend.sandbox.java.algo;
import java.util.Arrays;

import static java.lang.System.out;
public class MissingWords {

    /**
     * Given a string s, and a substring t, where s and t are both space separated string sequences of words, and
     * all words of t are definitely contained in s, return the ordered sequence of words in s that do not appear
     * in the t subsequence as a list of those strings.
     * @param args
     */
    public static void main(String[] args){
        String allWords = "I went to school last week and ate a super super carne asada burrito afterwards for dinner";
        String partialWords = "went last week and ate a super burrito for dinner";
        String[] output =new String[]{"I", "to", "school", "super", "carne", "asada", "afterwards"};

        String[] input = allWords.split(" ");
        String[] test = partialWords.split(" ");
        int index = 0;
        int outIndex = 0;
        for(String w : input){
            if(test[index].equals(w)){
                index++;
            }else {
                output[outIndex++]= w;
            }
        }
        out.println(Arrays.toString(output));
    }
}
