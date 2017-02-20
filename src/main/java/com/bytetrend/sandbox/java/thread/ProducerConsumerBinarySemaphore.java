package com.bytetrend.sandbox.java.thread;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;


public class ProducerConsumerBinarySemaphore {
    public static void main(String[] args)
            throws InterruptedException {
        // Object of a class that has both produce()
        // and consume() methods
        final PC pc = new PC();

        // Create producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "producer-1");

        // Create producer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "producer-2");

        // Create consumer thread
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer-3");
        // Create consumer thread
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer-4");

        // Start both threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // t1 finishes before t2
        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }

    // This class has a list, producer (adds items to list
    // and consumber (removes items).
    public static class PC {
        // Create a list shared by producer and consumer
        // Size of list is 2.
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = 1;
        Semaphore semaphore = new Semaphore(capacity);

        // Function called by producer thread
        public void produce() throws InterruptedException {
            int value = 0;
            while (true) {
                try {
                    // producer thread waits while list is full
                    semaphore.acquire();
                    try {
                        System.out.println(Thread.currentThread().getName() + ": got permit available " + semaphore.availablePermits() + " items " + list.size());
                        // to insert the jobs in the list
                        //      if (list.size() < capacity) {
                        list.add(value++);
                        System.out.println(Thread.currentThread().getName() + " produced-" + value);
                        //       }
                        System.out.println(Thread.currentThread().getName() + " : releasing lock...");
                    } finally {
                        semaphore.release();
                    }
                    System.out.println(Thread.currentThread().getName() + ": available Semaphore permits available: "
                            + semaphore.availablePermits());
                    // makes the working of program easier to  understand
                    Thread.sleep((new Random()).nextInt(4) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Function called by consumer thread
        public void consume() throws InterruptedException {
            while (true) {
                try {
                    semaphore.acquire();
                    try {
                        System.out.println(Thread.currentThread().getName() + ": got permit " + semaphore.availablePermits() + " items " + list.size());
                        //to retrive the ifrst job in the list
                        if (list.size() > 0) {
                            int value = list.removeFirst();
                            System.out.println(Thread.currentThread().getName() + " consumed- " + value);
                        }
                    } finally {
                        semaphore.release();
                    }
                    System.out.println(Thread.currentThread().getName() + ": available Semaphore permits available: "
                            + semaphore.availablePermits());
                    // and sleep
                    Thread.sleep((new Random()).nextInt(4) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

