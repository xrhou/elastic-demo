package com.hxr.lockdeeping;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 面试题目:手写自旋锁
 * 自旋锁的好处是:循环比较获取直到成功为止，没有类似wait的阻塞
 * 通过CAS操作完成自旋,A线程进入调用myLock方法持有锁5秒钟,B随后进入后发现当前有线程持有锁,不是null,
 * 所以只能通过自旋等待,直到A释放后B抢占到锁
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class SpinLockDemo {

    //原子引用线程
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 加锁
     */
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in thread...");
        //默认内存值是null,比较并交换后值是Thread
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    /**
     * 解锁
     */
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnLock()");
    }


    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();

            //暂停一会
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinLockDemo.myUnLock();
        }, "AA").start();


        try {
            Thread.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        }, "BB").start();

    }
}
