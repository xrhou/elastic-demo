package com.hxr.lockdeeping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author houxiurong
 * @date 2019-09-17
 */
public class CurrentTimeMillisClock {

    private volatile long now;

    private long now() {
        return now;
    }

    private CurrentTimeMillisClock() {
        this.now = System.currentTimeMillis();
        scheduleTick();
        Set set = new HashSet();
        Set linkedHashSet = new LinkedHashSet<>();
        List list = new ArrayList();

        /*
        //javac 语法树检查-语义分析和字节码检查 只有d=a+c是编译通过的
        int a = 1;
        boolean b = false;
        char c = 2;
        int d = a + c;
        int e = b + c;
        char f = a + c;
        */

    }

    private void scheduleTick() {
        new ScheduledThreadPoolExecutor(1, runnable -> {
            Thread thread = new Thread(runnable, "current-time-millis");
            thread.setDaemon(true);
            return thread;
        }).scheduleAtFixedRate(() -> {
            now = System.currentTimeMillis();
        }, 1, 1, TimeUnit.MILLISECONDS);
    }

    public static CurrentTimeMillisClock getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CurrentTimeMillisClock INSTANCE = new CurrentTimeMillisClock();
    }


    public static void main(String[] args) {
        System.out.println(CurrentTimeMillisClock.getInstance().now());
    }
}
