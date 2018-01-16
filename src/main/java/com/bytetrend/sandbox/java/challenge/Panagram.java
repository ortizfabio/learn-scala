package com.bytetrend.sandbox.java.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Find missing letters of the alphabet
 *
 * Created by db2admin on 1/15/2018.
 */
public class Panagram{
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        char[] arr = new char['z'-'a'+1];
        for(int i = 0; i < arr.length; i++)
            arr[i] =(char)('a' + i);
        while ((line = in.readLine()) != null) {
            // System.out.println(line);
            char[] letters = line.toLowerCase().toCharArray();
            for(int i = 0; i < letters.length; i++){
                if(letters[i] >= 'a' && letters[i] <= 'z'){
                    int c = (letters[i] - 'a');
                    arr[c] = 1;
                }
            }
        }
        String output = "";
        for(int i = 0; i < arr.length; i++)
            if(arr[i] != 1)
                output = output + arr[i];
        if(output == "")
            System.out.println("NULL");
        else
            System.out.println(output);
    }

}
