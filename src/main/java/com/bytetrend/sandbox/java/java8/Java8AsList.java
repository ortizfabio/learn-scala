package com.bytetrend.sandbox.java.java8;

import java.util.Arrays;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

/**
 * Created by db2admin on 17/04/13.
 * <p>
 * public class FindStringInBoard {
 * <p>
 * <p>
 * static public void main(String[] args) {
 * Stream<String> fruitStream = Stream.of("Mango", "Orange").parallel();
 * <p>
 * fruitStream.filter(fruit -> {
 * System.out.println("Fruit:" + fruit);
 * return false;
 * }).forEach(fruit -> {});
 * }
 * }
 */
public class Java8AsList {
    public static void main(String[] args) {
        List<String> elements = Arrays.asList("1","2","3");
        String result = elements.stream().reduce("",String::concat);
        System.out.println(result);
        result = elements.stream().collect(joining());
        System.out.println(result);

       System.out.println( elements.stream().anyMatch(s -> Integer.parseInt(s) == 2));
    }
}