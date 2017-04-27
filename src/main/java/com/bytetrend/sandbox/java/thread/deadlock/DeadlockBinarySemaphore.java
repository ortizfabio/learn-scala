package com.bytetrend.sandbox.java.thread.deadlock;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * This is a demo of a thread deadlock. The deadlock occurs because the
 * choice of collection used for the producer/consumer class. In this case a blocking queue of size 1
 * as been chosen which results in a deadlock
 *
 * 1) when the consumer grabs the semaphore but there is nothing in the queue and then call blocking method poll.
 * 2) when the producer grabs the semaphore but the queue is already full preventing the consumer to grab the semaphore
 *    and taking the element from te queue.
 */
public class DeadlockBinarySemaphore {
    public static void main(String[] args)
            throws InterruptedException {
        // Object of a class that has both produce()
        // and consume() methods
        final ProducerConsumer producerConsumer = new ProducerConsumer();

        // Create producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "producer-1");

        // Create consumer thread
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer-3");


        // Start both threads
        t1.start();
        t3.start();

        // t1 finishes before t3
        t1.join();
        t3.join();

    }

    // This class has a list, producer (adds items to list
    // and consumber (removes items).
    public static class ProducerConsumer{
            // Create a list shared by producer and {consumer
            // Size of list is 2.
        BlockingQueue<Integer> queue = new ArrayBlockingQueue(1);

    int capacity = 1;
    Semaphore semaphore = new Semaphore(capacity);

    // Function called by producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            try {
                // producer thread waits while queue is full
                semaphore.acquire();
                try {
                    System.out.println(Thread.currentThread().getName() + ": got permit available " + semaphore.availablePermits() + " items " + queue.size());
                    // to insert the jobs in the queue
                    //      if (queue.size() < capacity) {
                    queue.put(value++);
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
                    System.out.println(Thread.currentThread().getName() + ": got permit " + semaphore.availablePermits() + " items " + queue.size());
                    //to retrieve the first job in the queue
                    if (queue.size() > 0) {

                        int value = queue.poll();
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

