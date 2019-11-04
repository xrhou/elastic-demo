package com.hxr.executespooldeeping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author houxiurong
 * @date 2019-10-17
 */
public class SemaphoreDemo3 {

    //Semaphore默认使用的是不公平策略,可以使用自定义传参的方式使用公平实现
    private static volatile Semaphore semaphore = new Semaphore(0, true);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(2);

        executors.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "sub_A task over");
                semaphore.release();
            }
        });

        executors.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "sub_A task over");
                semaphore.release();
            }
        });

        semaphore.acquire(2);
        System.out.println("task A is over");

        executors.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "sub_B task over");
                semaphore.release();
            }
        });

        executors.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "sub_B task over");
                semaphore.release();
            }
        });

        semaphore.acquire(2);

        System.out.println("task B is over");

        executors.shutdown();

    }

}
