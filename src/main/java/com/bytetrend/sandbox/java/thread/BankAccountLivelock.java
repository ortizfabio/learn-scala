package com.bytetrend.sandbox.java.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Example of Livelock deadlock
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
                Thread.sleep(10l);
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

