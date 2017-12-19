package com.bytetrend.sandbox.java.concurrent.executor;

import java.util.Random;
import java.util.concurrent.Callable;

public class LongRunningTask implements Callable<String> {

    public String call() {
        // do stuff and return some String
        try {
            Thread.sleep(Math.abs(new Random().nextLong() % 5000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return Thread.currentThread().getName();
    }
}