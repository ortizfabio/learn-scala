package com.bytetrend.sandbox.java.memory;

/**
 * Example 1: Autoboxing
 * https://dzone.com/articles/memory-leak-andjava-code
 * <p>
 * Can you spot the memory leak?
 * <p>
 * Here I made a mistake. Instead of taking the primitive long for
 * the sum, I took the Long (wrapper class), which is the cause
 * of the memory leak. Due to auto-boxing, sum=sum+l; creates
 * a new object in every iteration, so 1000 unnecessary objects
 * will be created. Please avoid mixing and matching between primitive
 * and wrapper classes. Try to use primitive as much as you can.
 */

public class Adder {
    public long addIncremental(long l) {
        //with settings -Xms2m -Xmx16m and in debug mode
        //Long here is creating unnecessary Long objects when using Long sum = 0L
        //The memory usage is 11 Mb in debugging mode
        //When using long sum = 0L the total memory usage is 1 Mb
        Long sum = 0L;
        sum = sum + l;
        return sum;
    }

    public static void main(String[] args) {
        Adder adder = new Adder();
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            adder.addIncremental(i);
            if (i % (Integer.MAX_VALUE / 10) == 0)
                System.out.println(String.format("free= %14d Mb total= %14d Mb max= %14d Mb", Runtime.getRuntime().freeMemory()/1_000_000, Runtime.getRuntime().totalMemory()/1_000_000, Runtime.getRuntime().maxMemory()/1_000_000));
        }
        System.out.println("Exiting");
    }
}
