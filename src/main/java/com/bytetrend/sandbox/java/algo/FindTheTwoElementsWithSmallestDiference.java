package com.bytetrend.sandbox.java.algo;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.careercup.com/question?id=5071329456291840
 */
public class FindTheTwoElementsWithSmallestDiference {

    public static int[] build(int size) {
        Random r = new Random();
        Supplier<Integer> f = (() -> r.nextInt(200));
        int[] a = new int[size];
        Iterator<Integer> iter = Stream.generate(f).limit(size).collect(Collectors.toCollection(ArrayList::new)).iterator();
        int i = 0;
        while (iter.hasNext()) {
            a[i++] = iter.next();
        }
        return a;
    }

    public static Optional<int[]> findSmallestDiff(int[] a) {
        int[] r = new int[]{a[0], a[1]};
        int i = 2;
        while (i < a.length) {
            //If smallest than previous
            if ((r[1] - r[0]) > (a[i] - a[i - 1])) {
                r[0] = a[i - 1];
                r[1] = a[i];
            }
            i++;
        }
        return Optional.of(r);
    }

    public static Optional<int[]> find(int[] a) {
        if (a.length < 2)
            return Optional.empty();
        else {
            Arrays.sort(a);
            return findSmallestDiff(a);
        }
    }

    public static void main(String[] args) {
        int[] arr = build(10);
        Arrays.sort(arr);
        System.out.println(String.format("%s %s", Arrays.toString(arr), Arrays.toString(find(arr).orElseGet(() -> new int[]{}))));
    }
}
