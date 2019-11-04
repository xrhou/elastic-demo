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
public class SemaphoreDemo1 {

    //实例化一个Semaphore对象
    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        //定义一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        System.out.println("main thread start");

        //将线程A放入线程池
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " thread1 over");
            semaphore.release();
        });

        //将线程B放入线程池
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " thread2 over");
            semaphore.release();
        });

        //等待子线程执行完毕返回
        semaphore.acquire(2);

        System.out.println("all child thread over");

        //关闭线程池
        executorService.shutdown();
    }
}
