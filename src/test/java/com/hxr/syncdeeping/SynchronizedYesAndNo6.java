package com.hxr.syncdeeping;

/**
 * 同时访问同步和非同步方法
 *
 * @author houxiurong
 * @date 2019-08-26
 */
public class SynchronizedYesAndNo6 implements Runnable {
    static SynchronizedYesAndNo6 instance = new SynchronizedYesAndNo6();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    public synchronized void method1() {
        System.out.println("加锁方法,ThreadName:" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "程序运行结束.");
    }

    public void method2() {
        System.out.println("不加锁方法,ThreadName:" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "程序运行结束.");
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);

        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {
        }
        System.out.println("Finish");
    }
}
