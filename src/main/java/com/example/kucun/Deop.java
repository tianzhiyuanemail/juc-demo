package com.example.kucun;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deop {


    private Integer size = 0;

    private Integer maxSize = 100;

    private Lock lock = new ReentrantLock();

    private Condition full = lock.newCondition();
    private Condition zore = lock.newCondition();

    public void producer(Integer c) {
        lock.lock();
        try {
            Integer left = c;
            while (left > 0) {
                while (size >= maxSize) {
                    full.await();
                }
                Integer inc = left + size > maxSize ? maxSize - size : left;
                left = left - inc;
                size = size + inc;
                System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                        Thread.currentThread().getName(), c, left, inc, size);
                zore.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void consumer(Integer c) {
        lock.lock();
        try {
            Integer left = c;
            while (left > 0) {
                while (left > size) {
                    zore.wait();
                }
                Integer dec = size - left > 0 ? left : size;

                size = size - dec;
                left = left - dec;
                System.out.printf("%s consumer(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                        Thread.currentThread().getName(), c, left, dec, size);
                full.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}