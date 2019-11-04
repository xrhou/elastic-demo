package com.hxr.executespooldeeping;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个任务拆分为两个线程执行,线程执行到同一状态后再一起执行结束
 * |A|
 * /   \
 * /     \
 * A0   A1
 * \     /
 * \   /
 * |A|
 *
 * @author houxiurong
 * @date 2019-09-28
 */
public class CyclicBarrierDemo1 {


    //所有子线程全部到达屏障后执行的任务
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            //子线程全部执行完毕后要执行的任务
            System.out.println(Thread.currentThread() + " task1 merge result");
        }
    });

    public static void main(String[] args) {
        //创建一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //将线程A0添加到线程池
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " task1-1开始执行");
                System.out.println(Thread.currentThread() + " task1 enter in barrier");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " enter task1 out barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        //将线程A1添加到线程池
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " task1-2开始执行");
                System.out.println(Thread.currentThread() + " task2 enter in barrier");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + " enter task2 out barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        //关闭线程池
        executorService.shutdown();
    }


}
