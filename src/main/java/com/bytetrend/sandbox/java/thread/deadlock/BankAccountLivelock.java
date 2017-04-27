package com.bytetrend.sandbox.java.thread.deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Example of Livelock deadlock
 * https://richardbarabe.wordpress.com/2014/02/21/java-deadlock-livelock-and-lock-starvation-examples/
 * <p>
 *
 * A LiveLock looks like a deadlock in the sense that two (or more) processes are blocking each others.
 * But with the livelock, each process is waiting “actively”, trying to resolve the problem on its
 * own (like reverting back its work and retry). A live lock occurs when the combination of
 * these processes’s efforts to resolve the problem makes it impossible for them to ever terminate.
 * Let’s take the example of the bank account again. Suppose another erroneous implementation of
 * two simultaneous transfer operation. Here again, two threads tries to transfer money from one
 * account to another one at the same time. But this time, instead of waiting for a lock to be released
 * when a required account is locked, a thread will juste revert its work if any, and retry the whole
 * operation in loop until successful.
 *
 */
public class BankAccountLivelock {
    double balance;
    int id;
    Lock lock = new ReentrantLock();

    BankAccountLivelock(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    boolean withdraw(double amount) {
        if (this.lock.tryLock()) {
            // Wait to simulate io like database access ...
            try {
                TimeUnit.MILLISECONDS.sleep(10l);
            } catch (InterruptedException e) {
            }
            balance -= amount;
            return true;
        }
        return false;
    }

    boolean deposit(double amount) {
        if (this.lock.tryLock()) {
            // Wait to simulate io like database access ...
            try {
                Thread.sleep(10l);
            } catch (InterruptedException e) {
            }
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean tryTransfer(BankAccountLivelock destinationAccount, double amount) {
        if (this.withdraw(amount)) {
            if (destinationAccount.deposit(amount)) {
                return true;
            } else {
                // destination account busy, refund source account.
                this.deposit(amount);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        final BankAccountLivelock fooAccount = new BankAccountLivelock(1, 500d);
        final BankAccountLivelock barAccount = new BankAccountLivelock(2, 500d);

        new Thread(new Transaction(fooAccount, barAccount, 10d), "transaction-1").start();
        new Thread(new Transaction(barAccount, fooAccount, 10d), "transaction-2").start();

    }

    static class Transaction implements Runnable {
        private BankAccountLivelock sourceAccount, destinationAccount;
        private double amount;

        Transaction(BankAccountLivelock sourceAccount, BankAccountLivelock destinationAccount, double amount) {
            this.sourceAccount = sourceAccount;
            this.destinationAccount = destinationAccount;
            this.amount = amount;
        }

        public void run() {
            while (!sourceAccount.tryTransfer(destinationAccount, amount))
                continue;
            System.out.printf("%s completed ", Thread.currentThread().getName());
        }

    }
}

