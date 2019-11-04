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
public class CyclicBarrierDemo3 {

    //创建一个回环屏障实例,用户执行任务的最后一步
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(1);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

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

        //关闭线程池
        executorService.shutdown();

        System.out.println("a".hashCode() & 15);
    }
}


