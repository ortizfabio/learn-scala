package com.bytetrend.sandbox.java.thread;


import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This is an example of usage of a ReentrantReadWriteLock
 * The class below to insert a value in the list we use a writeLock and
 * read we use a read lock
 * <p>
 * The values are generated sequentially but they are inserted out of order because
 * the worker object doing it has a random sleep time.
 *
 * @param <E>
 */
public class ReadWriteLockExample<E> {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    private final List<E> list;


    public ReadWriteLockExample(int count) {
        //Arrays.asList(new String[count - 1])
        list = new ArrayList<>();
    }

    private final static Random rnd = new Random();

    public void set(E o) {
        writeLock.lock();
        try {
            list.add(o);
            System.out.println("Adding element "+o+" by thread" + Thread.currentThread().getName());
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int i) {
        readLock.lock();
        try {
//            System.out.println("Printing elements by thread" + Thread.currentThread().getName());
            E val = list.get(i);
            return val;
        } finally {
            readLock.unlock();
        }
    }


    public Callable<E> getProducer(E i) {
        return new Producer(i);
    }

    public Callable<E> getConsumer(int i) {
        return new Consumer(i);
    }

    class Producer implements Callable {
        private final E value;

        public Producer(E i) {
            value = i;
        }

        @Override
        public E call() throws Exception {

            TimeUnit.MILLISECONDS.sleep(rnd.nextInt(100));
            //The value will be a integer witch is different than the index in the
            //array list of the readWriteLock
            if(Integer.parseInt(value.toString())% 10 == 0)
                throw new IllegalArgumentException("Random exception in Future "+value);
            set(value);
            return value;
        }
    }

    class Consumer implements Callable {
        private final int value;

        public Consumer(int i) {
            value = i;
        }

        @Override
        public E call() throws Exception {

            TimeUnit.MILLISECONDS.sleep(rnd.nextInt(100));
            //The value will be a integer witch is different than the index in the
            //array list of the readWriteLock
            return get(value);

        }
    }

    public static void main(String[] args) {
        int count = 30;
        ReadWriteLockExample<String> example = new ReadWriteLockExample<>(count);
        produce(example, count);
        consume(example, count);


    }

    private static void produce(ReadWriteLockExample example, int count) {

        ExecutorService executor = Executors.newFixedThreadPool(100);

        final String[] calculations = new String[count];

        //Requests are encapsulated by a Future here and monitored till done.
        List<Future<String>> pendingFutures = new ArrayList<>(count);
        try {
            for (int i = 0; i < count; i++)
                pendingFutures.add(i, executor.submit(example.getProducer("" + i)));
            int doneCount = 0;
            while (doneCount < pendingFutures.size()) {
                for (int j = 0; j < count; j++) {
                    if (calculations[j] == null) {
                        Future<String> future = pendingFutures.get(j);
                        if (future.isDone()) {
                            doneCount++;
                            try {
                                String value = future.get();
                                calculations[j] = value;
                            }catch (CancellationException  | ExecutionException | InterruptedException |IllegalArgumentException   e){
                                e.printStackTrace();
                                calculations[j] = "0";
                            }
                        }
                    }
                }

            }
        } catch (Exception t) {
            t.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static void consume(ReadWriteLockExample example, int count) {

        ExecutorService executor = Executors.newFixedThreadPool(count / 10);

        final String[] calculations = new String[count];

        //Requests are encapsulated by a Future here and monitored till done.
        List<Future<String>> pendingFutures = new ArrayList<>(count);
        try {
            for (int i = 0; i < count; i++)
                pendingFutures.add(i, executor.submit(example.getConsumer(i)));
            int doneCount = 0;
            while (doneCount < pendingFutures.size()) {
                for (int j = 0; j < count; j++) {
                    if (calculations[j] == null) {
                        Future<String> future = pendingFutures.get(j);
                        if (future.isDone()) {
                            doneCount++;
                            String value = future.get();
                            calculations[j] = value;
                            System.out.println("Element : " + j + " " + value);
                        }
                    }
                }

            }
        } catch (Exception t) {
            t.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

