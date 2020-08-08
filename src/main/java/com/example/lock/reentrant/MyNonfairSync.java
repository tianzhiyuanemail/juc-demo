package com.example.lock.reentrant;

/**
 * 非公平锁
 *
 * @author baidu
 */
public class MyNonfairSync extends MySync {
    @Override
    final void lock() {
        // 尝试将0 更改为1 如果成功则表示 不用排队
        boolean b = compareAndSetState(0, 1);
        if (b) {
            setExclusiveOwnerThread(Thread.currentThread());
        } else {
            acquire(1);
        }
    }

    /***
     * lock acquire 中第一个判断条件
     * @param acquires
     * @return
     */
    @Override
    protected final boolean tryAcquire(int acquires) {
        // 获取当前线程
        final Thread current = Thread.currentThread();
        //
        int c = getState();
        if (c == 0) {
            boolean compareAndSetState = compareAndSetState(0, acquires);
            if (compareAndSetState) {
                setExclusiveOwnerThread(current);
                return true;
            }
        } else if (current == getExclusiveOwnerThread()) {
            int nextc = c + acquires;
            // overflow
            if (nextc < 0) {
                throw new Error("Maximum lock count exceeded");
            }
            setState(nextc);
            return true;
        }
        return false;
    }
}