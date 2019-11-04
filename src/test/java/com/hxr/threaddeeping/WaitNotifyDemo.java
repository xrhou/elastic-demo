package com.hxr.threaddeeping;

import java.util.concurrent.TimeUnit;

/**
 * wait和notify理解
 *
 * @author houxiurong
 * @date 2019-09-05
 */
public class WaitNotifyDemo {
    final static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程 A 等待拿锁");
                synchronized (lock) {
                    try {
                        System.out.println("线程 A 拿到了锁");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("线程 A 开始等待并释放锁");
                        lock.wait();
                        System.out.println("线程 A 等待被通知可以继续执行 到结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程 B 等待锁");
                synchronized (lock) {
                    try {
                        System.out.println("线程 B 拿到了锁");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("线程 B 执行其他逻辑");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                    System.out.println("线程B随机通知Lock对象的某个线程运行");
                }
            }
        }, "线程B").start();
    }
}
