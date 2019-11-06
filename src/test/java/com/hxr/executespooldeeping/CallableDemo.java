package com.hxr.executespooldeeping;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 多线程中,第三中获得多线程的方式
 *
 * @author houxiurong
 * @date 2019-11-05
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        Thread thread = new Thread(futureTask, "threadName");
        thread.start();
        System.out.println(Thread.currentThread().getName() + "==========");
        //建议一般放在main线程或者方法的最后
        //可以使用isDone()进行线程状态的判断
        boolean done = futureTask.isDone();

        Integer result1 = 100;

        Integer result2 = 0;
        while (done) {
            System.out.println("isDone=" + done);
        }
        result2 = futureTask.get();
        System.out.println("result=" + (result1 + result2));

        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}

/**
 * Callable线程具有有返回值
 */
class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("=======come in call ==========");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}
