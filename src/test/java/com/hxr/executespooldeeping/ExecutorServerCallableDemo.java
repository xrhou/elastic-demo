package com.hxr.executespooldeeping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable任务执行有返回值,Runnable任务执行没有返回值。
 * 两者都能在ExecutorServer的execute和submit执行。
 *
 * @author houxiurong
 * @date 2019-09-18
 */
public class ExecutorServerCallableDemo {

    public static void main(String[] args) {
        //定义一个无界缓存线程池 最大线程数是Integer.MAX_VALUE=2^31-1
        ExecutorService executorService = Executors.newCachedThreadPool();

        //1.8新增的线程池方法是:Executors.newWorkStealingPool()->底层实现是ForkJoinPool
        //定义一个存放执行结果的Future的list
        List<Future<String>> resultList = new ArrayList<>();

        //创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future = executorService.submit(new TaskWithResult(i));
            //将任务执行结果存储到List中
            resultList.add(future);
        }


        Thread.getAllStackTraces();
        //获取callable线程池返回的结果,遍历任务结果
        for (Future<String> stringFuture : resultList) {
            try {
                //打印各个线程（任务）执行的结果
                System.out.println(stringFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
                //如果已经关闭，则调用没有其他作用。
                executorService.shutdown();
            }
        }

    }
}
