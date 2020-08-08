package com.example.kucun;

public class T {


    public static void main(String[] args) {

        // 80 70
        Deopt1 deop = new Deopt1();

        DeoptProducer deoptProducer = new DeoptProducer(deop);

        DeoptConsumer deoptConsumer = new DeoptConsumer(deop);

        deoptProducer.producer(120);
        deoptProducer.producer(60);

        deoptConsumer.consumer(50);

        deoptConsumer.consumer(5);
        deoptConsumer.consumer(5);
        deoptConsumer.consumer(5);



    }


}