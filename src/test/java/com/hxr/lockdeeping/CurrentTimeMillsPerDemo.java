package com.hxr.lockdeeping;

import java.util.concurrent.CountDownLatch;

/**
 * @author houxiurong
 * @date 2019-09-17
 */
public class CurrentTimeMillsPerDemo {

    private static final int COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        long beginTime = System.nanoTime();
        for (int i = 0; i < COUNT; i++) {
            System.currentTimeMillis();
        }

        long elapsedTime = System.nanoTime() - beginTime;
        System.out.println("100 System.currentTimeMillis() serial calls:" + elapsedTime + "ns");


        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(COUNT);

        for (int i = 0; i < COUNT; i++) {
            new Thread(() -> {
                try {
                    startLatch.await();
                    System.currentTimeMillis();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown();
                }
            }).start();
        }

        beginTime = System.nanoTime();
        startLatch.countDown();
        endLatch.await();
        elapsedTime = System.nanoTime() - beginTime;
        System.out.println("100 System.currentTimeMillis() parallel calls:" + elapsedTime + "ns");
    }
}
