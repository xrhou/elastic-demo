package com.hxr.executespooldeeping.threaddemo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * 1.5之后实现的有返回值的多线程实现方式
 * call方法是个泛型方法
 * 缺点是:启动线程比较繁琐
 *
 * @author houxiurong
 * @date 2019-10-06
 */
public class ImplCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return new Random().nextInt(100);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ImplCallable callable = new ImplCallable();

        //启动线程
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();

        //获取返回值
        System.out.println("是否结束=" + futureTask.isDone());
        Integer result = futureTask.get();
        System.out.println(result);
        System.out.println("是否结束=" + futureTask.isDone());

    }


}
