package com.hxr.executespooldeeping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * MyThreadPoolDemo
 *
 * @author houxiurong
 * @date 2019-11-05
 */
public class MyThreadPoolDemo {


    public static void main(String[] args) {
        myThreadPoolExecutor();
    }

    /**
     * 手写线程池和决绝策略:
     * 线程池的大小配置取决两个方面:
     * 1.CUP密集-核心数-Runtime.getRuntime().availableProcessors()
     * 一般配置为cup数+1的线程池大小
     * 2.IO密集:cup核数 / 1-阻塞系数(0.8~0.9)
     * 如 8核CUP的配置 8/1-0.9=80给最大80个线程数
     *
     * 具体在环境中测试并发量,按照测试的稳定性来处理
     */
    private static void myThreadPoolExecutor() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy());//默认的抛出异常
                //new ThreadPoolExecutor.CallerRunsPolicy());//返回给调用者线程自己执行
                //new ThreadPoolExecutor.DiscardOldestPolicy());//丢掉线程池中等待最久的任务
                new ThreadPoolExecutor.DiscardPolicy());//不停的处理任务并且直接丢掉多余任务不抛出异常
        try {
            for (int i = 1; i <= 22; i++) {
                executorService.execute(() -> {
                    //调用执行业务的方法代码
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    /**
     * JDK版本的线程池
     */
    private static void threadPoolInit() {
        //一个一个单一的任务执行
        //ThreadPoolExecutor->LinkedBlockingQueue
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();

        //固定大小的任务线程-效率较高
        //ThreadPoolExecutor->LinkedBlockingQueue
        ExecutorService threadPool2 = Executors.newFixedThreadPool(5);

        //不固定大小的任务执行-绝对值是Integer.MAX=21亿多
        //ThreadPoolExecutor->SynchronousQueue
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        //ThreadPoolExecutor->DelayedWorkQueue
        ExecutorService threadPool4 = Executors.newScheduledThreadPool(5);

        //ForkJoinPool->ForkJoinPool(parallelism,ForkJoinPool.defaultForkJoinWorkerThreadFactory,null, true)
        ExecutorService threadPool5 = Executors.newWorkStealingPool(5);

        try {
            for (int i = 1; i <= 10; i++) {
                threadPool5.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t银行办理业务");
                });

                TimeUnit.MICROSECONDS.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool5.shutdown();
        }
    }


}
