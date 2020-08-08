package com.example.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @author baidu
 */
public class MyReentrantLock implements MyLock {

    private MySync sync;

    public MyReentrantLock() {
        this.sync = new MyNonfairSync();
    }

    public MyReentrantLock(Boolean boo) {
        if (boo) {
            this.sync = new MyFairSync();
        } else {
            this.sync = new MyNonfairSync();
        }
    }

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}