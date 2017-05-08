package com.bytetrend.sandbox.java.concurrent.producerconsumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * http://docs.oracle.com/javase/1.5.0/docs/api/java/util/concurrent/locks/Condition.html
 *
 */
class ProducerConsumerReentrantLockWithCondition<E> {
    final private Lock lock = new ReentrantLock();
    final private Condition notFull  = lock.newCondition();
    final private Condition notEmpty = lock.newCondition();

    final private Object[] items = new Object[10];
    int putptr, takeptr, count;

    public void put(E x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                //Lock and synchronized temporarily allow others to obtain the lock
                // when they are waiting. To stop waiting, a thread have to re-acquire the lock.
                //So at this point the lock is release
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                //Lock and synchronized temporarily allow others to obtain the lock
                // when they are waiting. To stop waiting, a thread have to re-acquire the lock.
                //So at this point the lock is release
                notEmpty.await();
            E x = (E)items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Random random = new Random();
        final ProducerConsumerReentrantLockWithCondition<Integer> buffer = new ProducerConsumerReentrantLockWithCondition();

        // Create producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 100;
                try {
                    while(count > 0) {
                        Integer i = count;
                        buffer.put(i);
                        System.out.println("Producer put " + i);
                        count--;
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(20));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create consumer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int count = 100;
                    while(count > 0 ) {
                        Integer i = buffer.take();
                        System.out.println("Consumer take " + i);
                        count--;
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(10));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // t1 finishes before t2
        t1.join();
        t2.join();
    }

}