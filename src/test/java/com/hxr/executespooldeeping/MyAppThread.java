package com.hxr.executespooldeeping;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自定义的线程池
 *
 * @author houxiurong
 * @date 2019-10-31
 */
public class MyAppThread extends Thread {
    public static final String DEFAULT_NAME = "MyAppThread";
    private static volatile boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger logger = Logger.getAnonymousLogger();

    public MyAppThread(Runnable runnable) {
        this(runnable, DEFAULT_NAME);
    }

    public MyAppThread(Runnable runnable, String name) {
        super(runnable, name + "-" + created.incrementAndGet());
        setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                logger.log(Level.SEVERE, "UNCAUGHT in thread " + t.getName());
            }
        });
    }

    @Override
    public void run() {
        //复制debug已确保一致的值
        boolean debug = debugLifecycle;
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                logger.log(Level.FINE, "Exiting " + getName());
            }
        }
    }

    public static boolean getDebug() {
        return debugLifecycle;
    }

    public static void setDebug(boolean debug) {
        debugLifecycle = debug;
    }

    public static int getThreadsCreated() {
        return created.get();
    }

    public static int getThreadAlive() {
        return alive.get();
    }
}
