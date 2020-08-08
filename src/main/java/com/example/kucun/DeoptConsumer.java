package com.example.kucun;

public class DeoptConsumer {

    private Deopt1 deop;

    public DeoptConsumer(Deopt1 deop) {
        this.deop = deop;
    }


    public void consumer(Integer i){
        new Thread(() -> deop.consumer(i)).start();
    }


}