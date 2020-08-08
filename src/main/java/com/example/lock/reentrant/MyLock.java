package com.example.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public interface MyLock {


    /**
     * 锁
     */
    void lock();

    /**
     * 锁 允许中断
     */
    void lockInterruptibly() throws InterruptedException;

    /**
     * 尝试获取锁
     */
    boolean tryLock();

    /**
     * 尝试获取锁
     */
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    /**
     * 解锁锁
     */
    void unlock();

    /**
     * 阻塞条件
     * @return
     */
    Condition newCondition();

}