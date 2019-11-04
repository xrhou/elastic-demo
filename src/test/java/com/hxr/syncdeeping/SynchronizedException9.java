package com.hxr.syncdeeping;

/**
 * 方法抛出异常后，会释放锁
 * 展示不抛出异常前和抛出异常后对比:
 * 一旦抛出异常，第二个线程会立即进入同步方法，意味着锁已经释放
 *
 * @author houxiurong
 * @date 2019-08-26
 */
public class SynchronizedException9 implements Runnable {
    static SynchronizedException9 instance = new SynchronizedException9();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    public synchronized void method1() {
        System.out.println("加锁方法1,ThreadName:" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "程序运行结束.");
        throw new RuntimeException("方法1抛出异常");
    }

    public synchronized void method2() {
        System.out.println("加锁方法2,ThreadName:" + Thread.currentThread().getName());
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
