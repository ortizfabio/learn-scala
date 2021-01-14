package com.bytetrend.sandbox.java.challenge;


/*

given 2 lists of websites each user visited.
implement a method, test cases
is visitation pattern for these 2 users the same?

1. all websites have to match exactly
2. the order of visits is not important
3. the number of visits is important

sort - O(n log n)
map - O(n)

*/


/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class ArrayOfStringsIsTheSame {

    public static boolean isTheSame(String[] list1, String[] list2){
        Map<String, Integer> table = new HashMap<>();
        for(String s : list1){
            if(table.containsKey(s))
                table.put(s,table.get(s)+1);
            else
                table.put(s,1);

        }

        for(String s: list2){
            if(!table.containsKey(s))
                return false;
            else
                table.put(s,table.get(s) -1);
        }

        for(Map.Entry<String,Integer> e : table.entrySet()){
            if(e.getValue() != 0 )
                return false;
        }
        return true;
    }

    public static void test1(){
        String[] strings1 = new String[]{"A", "B", "C"};
        String[] strings2 = new String[]{"A","Z", "C"};
        System.out.println(String.format("This test should print false %s",isTheSame(strings1,strings2)));
    }

    public static void test2(){
        String[] strings1 = new String[]{"A", "B", "C"};
        String[] strings2 = new String[]{"A"};
        System.out.println(String.format("This test should print false %s",isTheSame(strings1,strings2)));
    }

    public static void test3(){
        String[] strings1 = new String[]{"A", "B", "C"};
        String[] strings2 = new String[]{"C","A", "B"};
        System.out.println(String.format("This test should print true %s",isTheSame(strings1,strings2)));
    }

    public static void test4(){
        String[] strings1 = new String[]{"A", "B", "C", "A","B"};
        String[] strings2 = new String[]{"C","A", "B", "A","B"};
        System.out.println(String.format("This test should print true %s",isTheSame(strings1,strings2)));
    }

    public static void test5(){
        String[] strings1 = new String[]{"A", "B", "C", "A","B"};
        String[] strings2 = new String[]{};
        System.out.println(String.format("This test should print false %s",isTheSame(strings1,strings2)));
    }


    public static void main(String[] args) {

        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
