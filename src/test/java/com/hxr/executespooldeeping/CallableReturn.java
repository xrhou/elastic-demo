package com.hxr.executespooldeeping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 理解Callable具有返回值
 *
 * @author houxiurong
 * @date 2019-10-06
 */
public class CallableReturn {
    /**
     * 理解Callable具有返回值
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future> futureList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return Thread.currentThread().getName();
                }
            });
            futureList.add(future);
        }

        int i = 1;
        for (Future future : futureList) {
            String str = (String) future.get();
            System.out.println((i++) + " " + str);
        }
        //关闭线程池
        executorService.shutdown();
        System.out.println("执行结束");
    }
}
