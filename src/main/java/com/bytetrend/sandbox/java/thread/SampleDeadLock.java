package com.bytetrend.sandbox.java.thread;

public class SampleDeadLock {
    static final Object resource1 = "resource1";
    static final Object resource2 = "resource2";

    static class SampleThread extends Thread {
        private final Object firstResource;
        private final Object secondResource;

        public SampleThread(String name, Object resource1, Object resource2) {
            super(name);
            firstResource = resource1;
            secondResource = resource2;
        }

        public void run() {
            // Lock first resource
            synchronized (firstResource) {
                System.out.println(getName() + " locked resource " + firstResource);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
                // lock second resource
                synchronized (secondResource) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        }
    }

    static public void main(String[] args) {
        final SampleThread one = new SampleThread("one", resource1, resource2);
        final SampleThread two = new SampleThread("two", resource2, resource1);
        one.start();
        two.start();
    }
}

