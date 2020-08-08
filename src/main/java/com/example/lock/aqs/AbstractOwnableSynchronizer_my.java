package com.example.lock.aqs;

public abstract class AbstractOwnableSynchronizer_my {

    // 只能
    protected AbstractOwnableSynchronizer_my(){}

    private transient Thread execlusiverOwnerThread;



}