package com.example.atomic;

import com.example.unsafe.UnsafeUtil;
import sun.misc.Unsafe;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class AtomicInt extends Number {

    // 创建unsafe类
    private static Unsafe unsafe = UnsafeUtil.reflectGetUnsafe();

    // 创建偏移量
    private static long offset;

    // 定义偏移量 固定写法吧这是
    static {
        try {
            offset = unsafe.objectFieldOffset(AtomicInt.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    // 数值
    private volatile int value;

    // 无参数构造函数
    public AtomicInt() {
    }

    // 有参数构造函数 设置默认值
    public AtomicInt(int value) {
        this.value = value;
    }


    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }

    public final void lazySet(int newValue) {
        unsafe.putOrderedInt(this, offset, newValue);
    }

    public int getAndSet(int newValue) {
        return unsafe.getAndSetInt(this, offset, newValue);
    }

    /***
     *
     * @param expect 期望值
     * @param update 修改值
     * @return
     */
    public boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, offset, expect, update);
    }

    /***
     * 跟上一个方法一样
     * @param expect
     * @param update
     * @return
     */
    public boolean weadCompareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, offset, expect, update);
    }

    /**
     * 先获取值再自增
     *
     * @return
     */
    public int getAndIncrement() {
        return unsafe.getAndAddInt(this, offset, 1);
    }

    /***
     * 先获取后递减
     * @return
     */
    public int getAndDecrement() {
        return unsafe.getAndAddInt(this, offset, -1);
    }

    /***
     * 先获取后按照步长递加或者递减
     * @param delta
     * @return
     */
    public int getAndAdd(int delta) {
        return unsafe.getAndSetInt(this, offset, delta);
    }

    public int incrementAndGet() {
        return unsafe.getAndAddInt(this, offset, 1) + 1;
    }

    public int decrementAndGet() {
        return unsafe.getAndAddInt(this, offset, -1) - 1;
    }

    public int addAndGet(int delta) {
        return unsafe.getAndAddInt(this, offset, delta) + delta;
    }

    /**
     * todo 不太懂
     *
     * @param unaryOperator
     * @return
     */
    public int getAndUpdate(IntUnaryOperator unaryOperator) {
        int prev, next;
        do {
            prev = get();
            next = unaryOperator.applyAsInt(prev);
        } while (!compareAndSet(prev, next));
        return prev;
    }

    /**
     * todo 不太懂
     */
    public final int updateAndGet(IntUnaryOperator updateFunction) {
        int prev, next;
        do {
            prev = get();
            next = updateFunction.applyAsInt(prev);
        } while (!compareAndSet(prev, next));
        return next;
    }

    /**
     * todo 不太懂
     */
    public final int getAndAccumulate(int x,
                                      IntBinaryOperator accumulatorFunction) {
        int prev, next;
        do {
            prev = get();
            next = accumulatorFunction.applyAsInt(prev, x);
        } while (!compareAndSet(prev, next));
        return prev;
    }

    /**
     * todo 不太懂
     */
    public final int accumulateAndGet(int x,
                                      IntBinaryOperator accumulatorFunction) {
        int prev, next;
        do {
            prev = get();
            next = accumulatorFunction.applyAsInt(prev, x);
        } while (!compareAndSet(prev, next));
        return next;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    // 实现number方法
    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return (long) value;
    }

    @Override
    public float floatValue() {
        return (float) value;
    }

    @Override
    public double doubleValue() {
        return (double) value;
    }
}