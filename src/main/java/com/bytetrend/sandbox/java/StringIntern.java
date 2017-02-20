package com.bytetrend.sandbox.java;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class StringIntern {

    static public void main(String[] args) {
        String s1 = "Hello";
        String s2 = new String("Hello").intern();
        System.out.println(s1 == s2); // this is shows output false

        System.out.println(s1 == s2); // this gives output true,

        Set set1 = new HashSet();
        Set set2 = new TreeSet();

        System.out.println(set1.getClass());
        System.out.println(set2.getClass());
        System.out.println(set1.getClass() == set2.getClass());
    }
}
