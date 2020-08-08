package com.example.threadexecuter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadExector {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);


        ExecutorService executorService1 = Executors.newCachedThreadPool();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        ExecutorService executorService3 = Executors.newWorkStealingPool();





    }


}