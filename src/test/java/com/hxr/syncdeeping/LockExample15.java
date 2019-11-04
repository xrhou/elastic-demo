package com.hxr.syncdeeping;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 展示Lock的方法
 *
 * @author houxiurong
 * @date 2019-08-26
 */
public class LockExample15 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        lock.lock();
        lock.unlock();
        try {
            lock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
