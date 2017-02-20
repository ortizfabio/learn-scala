package com.bytetrend.sandbox.java.thread;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ExchangerExample {

    Exchanger<String> exchanger = new Exchanger();

    private class Producer implements Runnable {
        private String queue;
        private int value;
        @Override
        public void run() {
            while(true) {
                try {
                    //create tasks & fill the queue
                    //exchange the full queue for a empty queue with Consumer
                    queue = exchanger.exchange("Producer " + value++);
                    System.out.println(Thread.currentThread().getName() + " now has " + queue);
                    Thread.sleep((new Random()).nextInt(4) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Consumer implements Runnable {
        private String queue;
        private int value;
        @Override
        public void run() {
            while(true) {
                try {
                    //do procesing & empty the queue
                    //exchange the empty queue for a full queue with Producer
                    queue = exchanger.exchange("Consumer " + value++);
                    System.out.println(Thread.currentThread().getName() + " now has " + queue);
                    Thread.sleep((new Random()).nextInt(4) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void start() {
        new Thread(new Producer(), "Producer").start();
        new Thread(new Consumer(), "Consumer").start();
    }

    public static void main(String[] args) {
        new ExchangerExample().start();
    }
}
