package com.hxr.syncdeeping;

/**
 * 类锁的第一种形式:static形式
 *
 * @author houxiurong
 * @date 2019-08-26
 */
public class SynchronizedObjectMethod3 implements Runnable {

    static SynchronizedObjectMethod3 instance = new SynchronizedObjectMethod3();

    @Override
    public void run() {
        method();
    }

    public static synchronized void method() {
        System.out.println("对象锁的方法修饰形式,ThreadName:" + Thread.currentThread().getName());
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
        System.out.println("finish");
    }
}
