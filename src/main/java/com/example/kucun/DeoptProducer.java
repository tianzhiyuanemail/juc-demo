package com.example.kucun;

public class DeoptProducer {

    private Deopt1 deop;

    public DeoptProducer(Deopt1 deop) {
        this.deop = deop;
    }


    public void producer(Integer i){
        new Thread(() -> deop.producer(i)).start();
    }


}