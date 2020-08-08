package com.example.lock.reentrant;

public class MyFairSync extends MySync {
    @Override
    void lock() {
        // 所有的线程必须排队
        acquire(1);
    }

    /**
     * lock acquire 中第一个判断条件
     */
    @Override
    protected final boolean tryAcquire(int acquires) {
        // 获取当前线程
        final Thread current = Thread.currentThread();
        //
        int c = getState();
        if (c == 0) {
            boolean hasQueuedPredecessors = hasQueuedPredecessors();
            // 将state的值由0改为1
            boolean compareAndSetState = compareAndSetState(0, acquires);

            if (!hasQueuedPredecessors && compareAndSetState) {
                setExclusiveOwnerThread(current);
                return true;
            }
            // 判断持有锁的线程是否是当前线程
        } else if (current == getExclusiveOwnerThread()) {
            int nextc = c + acquires;
            if (nextc < 0) {
                throw new Error("Maximum lock count exceeded");
            }
            setState(nextc);
            return true;
        }
        return false;
    }
}