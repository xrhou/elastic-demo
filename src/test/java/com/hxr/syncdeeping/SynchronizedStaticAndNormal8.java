package com.hxr.syncdeeping;

/**
 * 同时访问静态synchronized和非静态的synchronized方法
 *
 * @author houxiurong
 * @date 2019-08-26
 */
public class SynchronizedStaticAndNormal8 implements Runnable {
    static SynchronizedStaticAndNormal8 instance = new SynchronizedStaticAndNormal8();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    public synchronized static void method1() {
        System.out.println("静态加锁方法1,ThreadName:" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "程序运行结束.");
    }

    public synchronized void method2() {
        System.out.println("非静态加锁方法2,ThreadName:" + Thread.currentThread().getName());
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

        System.out.println();
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {
        }
        System.out.println("Finish");
    }
}
