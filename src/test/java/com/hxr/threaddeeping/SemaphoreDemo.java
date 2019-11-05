package com.hxr.threaddeeping;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量主要两个目标：加减-要同步
 * 一个多用于共享资源的互斥使用
 * 一个用于并发线程数的控制
 * <p>
 * 场景:争车位
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        //模拟3个车位
        Semaphore semaphore = new Semaphore(3);

        //6辆车
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    //占用
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");

                    //暂停一会
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "\t停3秒钟后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }


    }
}
