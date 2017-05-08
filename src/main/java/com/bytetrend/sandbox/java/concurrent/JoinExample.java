package com.bytetrend.sandbox.java.concurrent;

import java.util.Random;

/**
 * Usage of Join
 * http://www.journaldev.com/1024/java-thread-join-example
 *
 * In this example t1 is started and the main tread waits for 2 millis or if t1 dies before
 * that. Then the main thread starts t2 and waits till it dies to start t3.
 */
public class JoinExample {

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");

        t1.start();

        //start second thread after waiting for 2 seconds or if t1 it's dead
        try {
            t1.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getName()+" state is "+t1.getState());
        t2.start();

        //start third thread only when t1 thread is dead
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getName()+" state is "+t1.getState());
        System.out.println(t2.getName()+" state is "+t2.getState());

        t3.start();
        System.out.println(t1.getName()+" state is "+t1.getState());
        System.out.println(t2.getName()+" state is "+t2.getState());
        System.out.println(t3.getName()+" state is "+t3.getState());


        //let all threads finish execution before finishing main thread
        try {
            //No need to join t2 because it should be dead.
            //t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(t1.getName()+" state is "+t1.getState());
        System.out.println(t2.getName()+" state is "+t2.getState());
        System.out.println(t3.getName()+" state is "+t3.getState());
        System.out.println("All threads are dead, exiting main thread");
    }

}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread started:::"+Thread.currentThread().getName());
        try {
            Thread.sleep(((new Random()).nextInt(6)+1) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread ended:::"+Thread.currentThread().getName());
    }

}