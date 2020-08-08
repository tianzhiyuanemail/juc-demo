package com.example.lock.reentrant;

public class T {

    public static void main(String[] args) {
        MyLock lock = new MyReentrantLock(true);

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取锁");
            Thread.sleep(3000);
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取锁");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁");
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁");
        }


//        new Thread(() -> {
//            lock.lock();
//            try {
//                System.out.println(Thread.currentThread().getName() + "获取锁");
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//                System.out.println(Thread.currentThread().getName() + "释放锁");
//            }
//        }).start();

//        new Thread(() -> {
//            lock.lock();
//            try {
//                System.out.println(Thread.currentThread().getName()+"获取锁");
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//                System.out.println(Thread.currentThread().getName()+"释放锁");
//            }
//        }).start();


    }

}