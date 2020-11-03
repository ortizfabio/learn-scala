package com.bytetrend.sandbox.java.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class LetterCombinationOfAPhoneNumber {
    private static Map<Integer, List<Character>> table = new HashMap() {{
        put(0, Arrays.asList('0'));
        put(1, Arrays.asList('1'));
        put(2, Arrays.asList('a', 'b', 'c'));
        put(3, Arrays.asList('d', 'e', 'f'));
        put(4, Arrays.asList('g', 'h', 'i'));
        put(5, Arrays.asList('j', 'k', 'l'));
        put(6, Arrays.asList('m', 'n', 'o'));
        put(7, Arrays.asList('p', 'q', 'r'));
        put(8, Arrays.asList('t', 'u', 'v'));
        put(9, Arrays.asList('w', 'x', 'y', 'z'));
    }};
    private static String getStringRepresentation(List<Character> list)
    {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }
    public static void processPhone(List<Integer> nbrs) {
        List<List<Character>> r = new ArrayList<>();
        for (Integer i : nbrs) {
            List<Character> lc = table.get(i);
            if (r.size() == 0) {
                for(Character c: lc)
                    r.add(Arrays.asList(c));
            } else {
                List<List<Character>> temp = new ArrayList<>(r);
                r.clear();
                ListIterator<List<Character>> iter = temp.listIterator();
                while(iter.hasNext()){
                    List<Character> list = iter.next();
                    for(Character c : lc){
                        List<Character> nl = new ArrayList<>();
                        nl.addAll(list);
                        nl.add(c);
                        r.add(nl);
                    }
                }
            }
        }
        List<String> fl =  r.stream().map(x -> getStringRepresentation(x)).collect(Collectors.toList());
        fl.sort( Comparator.comparing( String::toString ) );
        System.out.println(fl.stream().collect(Collectors.joining(",")));
    }

    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            List<Integer> l = Arrays.stream(line.split("")).map(c -> Integer.parseInt(c)).collect(Collectors.toList());
            processPhone(l);
        }
    }
}