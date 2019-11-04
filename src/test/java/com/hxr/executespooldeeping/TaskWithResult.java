package com.hxr.executespooldeeping;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Callable结果的执行程序
 *
 * @author houxiurong
 * @date 2019-09-18
 */
public class TaskWithResult implements Callable {
    private Integer id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    /**
     * 任务的具体过程,一旦任务传给ExecutorService的submit方法，
     * 则该方法自动在一个线程上执行。
     *
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        System.out.println("call()方法被自动调用,干活!,执行线程是:" +
                Thread.currentThread().getName());
        //模拟耗时的操作
        TimeUnit.SECONDS.sleep(3);
        return "call()方法被自动调用,任务的结果是：" + id + ",执行线程是:" +
                Thread.currentThread().getName();
    }
}
