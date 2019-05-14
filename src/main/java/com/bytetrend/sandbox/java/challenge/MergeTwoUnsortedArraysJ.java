package com.bytetrend.sandbox.java.challenge;

import java.util.*;

import static java.lang.System.out;

public class MergeTwoUnsortedArraysJ {

    private static final int[] one = new int[]{4, 2, 7, 10, 33, 0};
    private static final int[] two = new int[]{9, 2, 0, 2, 4, 5, 5};

    public static void main(String[] args) {
        Map<Integer, Integer> map = new TreeMap<>();
        Arrays.stream(one).forEach(x -> {
            if (map.computeIfPresent(x, (k, v) -> v + 1) == null)
                map.put(x, 1);
        });
        Arrays.stream(two).forEach(x -> {
            if (map.computeIfPresent(x, (k, v) -> v + 1) == null)
                map.put(x, 1);
        });
        final int j = 0;
        List<Integer> result = new ArrayList(one.length + two.length);
        map.entrySet().stream().forEach(kv -> {
            for (int i = 0; i < kv.getValue(); i++)
                result.add(kv.getKey());
        });
        result.forEach(x -> out.print(x+", "));
        //O(n) + O(n)
    }

}
