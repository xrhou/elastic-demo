package com.hxr.executespooldeeping;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个A任务执行时分为A0-A1-A2顺序执行:
 * A0--> A1--> A2 -->A3执行,结束后再统一执行A总任务
 *
 * @author houxiurong
 * @date 2019-09-28
 */
public class CyclicBarrierDemo2 {

    //创建一个回环屏障实例,用户执行任务的最后一步
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //将线程A添加到线程池
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " A step1 ");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + " A step2 ");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + " A step3 ");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        //将线程B 添加到线程池
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " B step-1 ");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + " B step-2 ");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + " B step-3 ");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });


        //关闭线程池
        executorService.shutdown();
    }
    /**
     Thread[pool-1-thread-1,5,main] A step1
     Thread[pool-1-thread-2,5,main] B step-1
     Thread[pool-1-thread-2,5,main] B step-2
     Thread[pool-1-thread-1,5,main] A step2
     Thread[pool-1-thread-1,5,main] A step3
     Thread[pool-1-thread-2,5,main] B step-3
     */
}


