package com.hxr.executespooldeeping.threaddemo.threadnotify.sync;

import java.sql.Connection;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * @author houxiurong
 * @date 2019-10-07
 */
public class Test2 {
    public static void main(String[] args) {
      /*  Thread t = Thread.currentThread();
        t.getPriority();
        t.setPriority(10);

        System.out.println(t.getThreadGroup().getParent());*/

        ScheduledExecutorService singleThreadScheduledExecutor =
                Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread("newSingleThreadScheduledExecutor");
                        return t;
                    }
                });
        //Executors.unconfigurableScheduledExecutorService();
        Executors.newWorkStealingPool(1);

        ThreadLocal<Connection> connect=new ThreadLocal<Connection>(){
            @Override
            protected Connection initialValue() {
                return super.initialValue();
            }
        };

    }
}
