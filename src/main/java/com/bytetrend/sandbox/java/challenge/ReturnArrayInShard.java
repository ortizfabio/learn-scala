package com.bytetrend.sandbox.java.challenge;

import java.util.*;

public class ReturnArrayInShard {

    public static int[] optimizeProcesses(int[] l) {
        Map<Integer, Integer> m = new HashMap();
        for (int i = 0; i < l.length; i++) {
            if (m.get(l[i]) == null) {
                m.put(l[i], 1);
            } else {
                m.put(l[i], m.get(l[i]) + 1);
            }
        }
        int j = 0;
        Set<Map.Entry<Integer, Integer>> kv = m.entrySet();
        while (j < l.length) {
            for (Iterator<Map.Entry<Integer, Integer>> iter = kv.iterator(); iter.hasNext(); ) {
                Map.Entry<Integer, Integer> e = iter.next();
                l[j++] = e.getValue();
                m.put(e.getKey(), e.getValue() - 1);
                if (e.getValue() == 1) {
                    iter.remove();
                }
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] l = new int[]{1, 2, 0, 3, 1, 2, 5, 4};
        //[0, 1, 2, 3, 4, 5, 1, 2]
        System.out.println(Arrays.toString(optimizeProcesses(l)));
    }

}
