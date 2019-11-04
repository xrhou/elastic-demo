package com.hxr.executespooldeeping;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池相关
 *
 * @author houxiurong
 * @date 2019-09-18
 */
public class ExecutorServerDemo {

    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     */
    public static void cachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(() -> System.out.println(index));
            //cachedThreadPool.invokeAll() //批量处理任务
        }
    }


    /**
     * 固定数量的线程池
     * 因为线程池大小为3，
     * 每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
     * 定长线程池的大小最好根据系统资源进行设置。
     * 如 Runtime.getRuntime().availableProcessors()。可参考PreloadDataCache。
     */
    public static void fixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                try {
                    System.out.println(index);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        fixedThreadPool.shutdownNow();
    }

    /**
     * 创建一个定长线程池，支持定时及周期性任务执行
     * ScheduledExecutorService比Timer更安全，功能更强大
     */
    public static void scheduledThreadPool() throws InterruptedException {
       /*
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(() -> {
            System.out.println("delay 3 second");
        }, 3, TimeUnit.SECONDS);*/

      /*  scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds, 每3秒执行一次");
            }
        }, 1, 3, TimeUnit.SECONDS);*/

        /**
         * scheduleWithFixedDelay 当前一个任务结束的时刻，开始计算间隔时间，
         * 如0秒开始执行第一次任务，任务耗时5秒，任务间隔时间3秒，
         * 那么第二次任务执行的时间是在第5+3=8,
         * 下一次5+2*3=11秒，
         * 下一次5+3*3=14秒
         *
         */
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        System.out.println("Current Time = " + new Date());
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            MyWorkerThread worker = new MyWorkerThread("do heavy processing");
            scheduledThreadPool.scheduleWithFixedDelay(worker, 5, 3, TimeUnit.SECONDS);
        }
        TimeUnit.SECONDS.sleep(30);

        scheduledThreadPool.shutdown();
        while (!scheduledThreadPool.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，
     * 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     */
    public static void singleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(() -> {
                try {
                    System.out.println(index);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        scheduledThreadPool();

        //Executors.newCachedThreadPool()

    }

}

