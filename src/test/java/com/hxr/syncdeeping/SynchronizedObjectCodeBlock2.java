package com.hxr.syncdeeping;

/**
 * 同步代码块
 *
 * @author houxiurong
 * @date 2019-08-25
 */
public class SynchronizedObjectCodeBlock2 implements Runnable {

    private static SynchronizedObjectCodeBlock2 instance1 = new SynchronizedObjectCodeBlock2();
    private static SynchronizedObjectCodeBlock2 instance2 = new SynchronizedObjectCodeBlock2();

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("对象锁代码块,ThreadName:" + Thread.currentThread().getName());
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
    }
}
