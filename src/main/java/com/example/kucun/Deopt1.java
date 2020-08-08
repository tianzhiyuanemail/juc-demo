package com.example.kucun;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deopt1 {

    private int size;
    private int maxSize = 100;
    private Lock lock = new ReentrantLock();

    private Condition full = lock.newCondition();

    private Condition zero = lock.newCondition();


    public void producer(int i) {

        lock.lock();
        try {
            int left = i;
            while (left > 0) {
                if (size >= maxSize) {
                    full.await();
                }
                int inc = left + size > maxSize ? maxSize - size : left;
                size = size + inc;
                left = left - inc;
                System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                        Thread.currentThread().getName(), i, left, inc, size);
                zero.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer(int i) {

        lock.lock();
        try {

            int left = i;
            while (left > 0) {
                if (size <= 0) {
                    zero.await();
                }
                int inc = size - left > 0 ? left : size;
                left = left - inc;
                size = size - inc;
                System.out.printf("%s consumer(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                        Thread.currentThread().getName(), i, left, inc, size);
                full.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}