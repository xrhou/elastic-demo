package com.hxr.lockdeeping;

import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 和 Lock的区别:
 *
 * @author houxiurong
 * @date 2019-11-05
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        synchronized (Object.class) {

        }

        ReentrantLock lock = new ReentrantLock();
    }
}
