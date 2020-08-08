package com.example.unsafe;

import com.example.atomic.AtomicInt;

public class T implements java.io.Serializable {

    public static void main(String[] args) {
        AtomicInt atomicInt = new AtomicInt();


        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int casSet = atomicInt.incrementAndGet();
                    System.out.println(casSet);
                }
            }).start();
        }


//        int value = atomicInt.getValue();
//
//        System.out.println(value);


    }
}