package com.example.threadlocal;

import java.util.function.Supplier;

public class ThreadLocalUtil {

    ThreadLocal<Integer> threadLocal =   ThreadLocal.withInitial(new Supplier<Integer>() {
        @Override
        public Integer get() {
            return 1;
        }
    });


    public Integer get (){
        return threadLocal.get();
    }


    public void set (){
        threadLocal.set(2);
    }



}