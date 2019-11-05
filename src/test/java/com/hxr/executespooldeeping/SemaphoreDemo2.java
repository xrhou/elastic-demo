package com.hxr.executespooldeeping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量,和CountDownLatch和CyclicBarrier不同是计数是递增
 *
 * @author houxiurong
 * @date 2019-09-28
 */
public class SemaphoreDemo2 {

    //实例化一个Semaphore对象
    private static volatile Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        //定义一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println("main thread start");

        //将线程A放入线程池
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " subA-1 over");
            semaphore.release();
        });

        //将线程B放入线程池
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " subA-2 over");
            semaphore.release();
        });

        //等待子线程执行完毕返回
        semaphore.acquire(2);
        System.out.println("A child thread over");


        //将线程B-1放入线程池
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " subB-1 over");
            semaphore.release();
        });

        //将线程B-2放入线程池
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " subB-2 over");
            semaphore.release();
        });

        //等待子线程执行完毕返回
        semaphore.acquire(2);
        System.out.println("B child thread over");

        //将线程C-1放入线程池
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " subC-1 over");
            semaphore.release();
        });

        //将线程C-2放入线程池
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " subC-2 over");
            semaphore.release();
        });

        //等待子线程执行完毕返回
        semaphore.acquire(2);
        System.out.println("C child thread over");

        //关闭线程池
        executorService.shutdown();
    }


    /**
     * main thread start
     * Thread[pool-1-thread-1,5,main] subA-1 over
     * Thread[pool-1-thread-2,5,main] subA-2 over
     * A child thread over
     * Thread[pool-1-thread-1,5,main] subB-1 over
     * Thread[pool-1-thread-2,5,main] subB-2 over
     * B child thread over
     * Thread[pool-1-thread-1,5,main] subC-1 over
     * Thread[pool-1-thread-2,5,main] subC-2 over
     * C child thread over
     */
}
