package com.hxr.threaddeeping;

/**
 * Daemon守护线程和UserThread用户线程的区别:
 * Java中线程分为两类: 守护线程和用户线程
 * 只要有一个用户线程还没结束，正常情况先JVM就不会退出
 * 守护线程和用户线程的区别:
 * 最后一个用户线程结束时JVM会正常退出(守护线程是否结束不影响JVM的退出)
 *
 * @author houxiurong
 * @date 2019-08-31
 */
public class MainThreadOrDaemonDemo {


    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (; ; ) {
                //for (int i = 0; i < 1000; i++) {
                //System.out.println("=" + i);
            }
        });

        //设置子线程为守护线程
        //thread.setDaemon(true);
        //启动子线程
        thread.start();

        System.out.println("main thread is over");
    }
}
