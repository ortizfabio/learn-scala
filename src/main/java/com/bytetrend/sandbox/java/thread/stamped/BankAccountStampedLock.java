package com.bytetrend.sandbox.java.thread.stamped;

import java.util.concurrent.locks.*;

/**
 * http://www.javaspecialists.eu/archive/Issue215.html
 * <p>
 * Our sixth version uses StampedLock. I have written two getBalance()
 * methods. The first uses pessimistic read locks, the other optimistic.
 * In our case, since there are no invariants on the fields that would
 * somehow restrict the values, we never need to have a pessimistic lock.
 *
 * Thus the optimistic read is only to ensure memory visibility, much
 * like the BankAccountSynchronizedVolatile approach.
 *
 */
public class BankAccountStampedLock {

    private final StampedLock sl = new StampedLock();
    private long balance;

    public BankAccountStampedLock(long balance) {
        this.balance = balance;
    }

    public void deposit(long amount) {
        long stamp = sl.writeLock();
        try {
            balance += amount;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    public void withdraw(long amount) {
        long stamp = sl.writeLock();
        try {
            balance -= amount;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    public long getBalance() {
        long stamp = sl.readLock();
        try {
            return balance;
        } finally {
            sl.unlockRead(stamp);
        }
    }

    public long getBalanceOptimisticRead() {
        long stamp = sl.tryOptimisticRead();
        long balance = this.balance;
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                balance = this.balance;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return balance;
    }

}
