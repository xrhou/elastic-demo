package com.hxr.syncdeeping;

/**
 * 不使用锁的后果
 *
 * @author houxiurong
 * @date 2019-08-25
 */
public class DisappearRequest1 implements Runnable {

    private static DisappearRequest1 instance = new DisappearRequest1();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);

        thread1.start();
        System.out.println();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }


    private static int i = 0;

    @Override
    public void run() {
        synchronized (DisappearRequest1.class) {
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }
}
