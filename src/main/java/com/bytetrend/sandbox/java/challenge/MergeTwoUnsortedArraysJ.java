package com.bytetrend.sandbox.java.challenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MergeTwoUnsortedArraysJ {

    private static final int[] one = new int[]{4,2,7,10, 33,0};
    private static final int[] two = new int[]{9,2,0,2,4,5,5};

    public static void main(String[] args){
        Map<Integer,Integer> map = new HashMap<>();
        Arrays.stream(one).forEach(x ->{
            if(map.computeIfPresent(x,(k,v)-> v + 1 ) == null)
                map.put(x,1);
        });
        Arrays.stream(two).forEach(x ->{
            if(map.computeIfPresent(x,(k,v)-> v + 1 ) == null)
                map.put(x,1);
        });

    }

}
