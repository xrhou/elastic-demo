package com.hxr.threaddeeping;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Runnable 和Callable的区别:
 * Runnable没有返回值，异常要自己内部消化
 * Callable有返回值的，经常和FutureTask一起配合使用。异常可以往外抛出
 *
 * @author houxiurong
 * @date 2019-09-04
 */
public class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() {
        int i;
        for (i = 0; i < 10; i += 2) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<>(callableDemo);
        new Thread(futureTask, "有返回值的线程").start();
        System.out.println(futureTask.isDone() + ",子线程的返回值是:" + futureTask.get());
    }
}
