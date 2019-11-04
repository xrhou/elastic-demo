package com.hxr.syncdeeping;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 加锁和释放锁的时机
 *
 * @author houxiurong
 * @date 2019-08-26
 */
public class SynchronizedToLock13 {

    Lock lock = new ReentrantLock();

    public synchronized void method1() {
        System.out.println("我是 synchronized 形式的锁!");
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("我是 lock 形式的锁!");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynchronizedToLock13 synchronizedToLock13 = new SynchronizedToLock13();
        synchronizedToLock13.method1();
        synchronizedToLock13.method2();
    }

}
