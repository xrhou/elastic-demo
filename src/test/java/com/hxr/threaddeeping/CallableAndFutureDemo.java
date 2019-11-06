package com.hxr.threaddeeping;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * CallableAndFuture
 *
 * @author houxiurong
 * @date 2019-09-04
 */
public class CallableAndFutureDemo {

    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            Thread.sleep(3000);
            return new Random().nextInt(100);
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();

        try {
            Thread.sleep(1000);
            System.out.println("hello begin");
            System.out.println(futureTask.isDone());
            //futureTask.cancel(false);

            if (!futureTask.isCancelled()) {
                System.out.println(futureTask.get());
                System.out.println(futureTask.isDone());
                System.out.println("hello end");
            } else {
                System.out.println("cancel-");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
