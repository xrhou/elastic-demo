package com.hxr.syncdeeping;

/**
 * @author houxiurong
 * @date 2019-08-26
 */
public class SynchronizedClassClass5 implements Runnable {

    static SynchronizedClassClass5 instance1 = new SynchronizedClassClass5();
    static SynchronizedClassClass5 instance2 = new SynchronizedClassClass5();

    @Override
    public void run() {
        method();
    }

    private void method() {
        synchronized (SynchronizedClassClass5.class) {
            System.out.println("类锁的第二种形式,Class,ThreadName:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "程序运行结束.");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);

        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {
        }
        System.out.println("Finish");
    }

}
