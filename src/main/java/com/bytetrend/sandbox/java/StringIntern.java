package com.bytetrend.sandbox.java;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class StringIntern {

    static public void main(String[] args) {
        String s1 = "Hello";
        String s2 = new String("Hello");
        System.out.println(s1 == s2); // this is shows output false
        s2 = s2.intern();
        System.out.println(s1 == s2); // this gives output true,

        Set set1 = new HashSet();
        Set set2 = new HashSet();

        System.out.println(set1.getClass());
        System.out.println(set2.getClass());
        System.out.println(set1.getClass().getName() == set2.getClass().getName());
    }
}
