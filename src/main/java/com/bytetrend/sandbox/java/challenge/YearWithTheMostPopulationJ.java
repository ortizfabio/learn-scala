package com.bytetrend.sandbox.java.challenge;

import java.util.*;

/**
 * Find the year with the higest population given a list of birth and death days
 * https://vimeo.com/158532188 pw=FB_IPS
 */
public class YearWithTheMostPopulationJ {

    static public void main(String[] args) {
        List<BirthDeathYears> byYears = new ArrayList<>(
                Arrays.asList(new BirthDeathYears(1929, 1967), new BirthDeathYears(1939, 1994),
                        new BirthDeathYears(1945, 1985), new BirthDeathYears(1900, 1929),
                        new BirthDeathYears(1929, 1967), new BirthDeathYears(1969, 2002), new BirthDeathYears(1989, 2019)
                ));
        Map<Integer, Integer> yearAndCount = new HashMap<>();
        byYears.stream().forEach(bdy -> {
            if (yearAndCount.computeIfPresent(bdy.birth, (k, v) -> v + 1) == null)
                yearAndCount.putIfAbsent(bdy.birth, 1);
            if (yearAndCount.computeIfPresent(bdy.death, (k, v) -> v - 1) == null)
                yearAndCount.putIfAbsent(bdy.death, -1);
        });
        Map.Entry<Integer, Integer>[] array = yearAndCount.entrySet().toArray(new Map.Entry[yearAndCount.size()]);
        Arrays.sort(array, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> entry, Map.Entry<Integer, Integer> t1) {
                if (entry.getKey() > t1.getKey())
                    return 1;
                else if (entry.getKey() < t1.getKey())
                    return -1;
                else
                    return 0;
            }
        });
        for (Map.Entry<Integer, Integer> e : array) System.out.println(e);
        Integer mostPopYear = 0;
        Integer mostPopCount = 0;
        int currentCount = 0;
        for (Map.Entry<Integer, Integer> e : array) {
            currentCount = currentCount + e.getValue();
            if(currentCount > mostPopCount){
                mostPopCount = currentCount;
                mostPopYear = e.getKey();
            }
        }
        System.out.println("year: "+mostPopYear+" births: "+mostPopCount+" final count: "+currentCount);
    }


     static class BirthDeathYears {
        final private int birth;
        final private int death;

        public BirthDeathYears(int b, int d) {
            birth = b;
            death = d;

        }
    }

}
