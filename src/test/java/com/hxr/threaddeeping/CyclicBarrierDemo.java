package com.hxr.threaddeeping;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环屏障:
 * 每个线程执行完毕后再执行最后的线程
 * 人到期才能开会(先到先等待)
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        /**
         * 收集7颗龙珠召唤神龙
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new RunnableThread());

        for (int i = 1; i <= 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集到第" + tempInt + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}

class RunnableThread implements java.lang.Runnable {

    @Override
    public void run() {
        System.out.println("==收集龙珠召唤神龙==");
    }
}
