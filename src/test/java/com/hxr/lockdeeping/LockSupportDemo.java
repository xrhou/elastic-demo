package com.hxr.lockdeeping;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport的作用是挂起和唤醒线程
 *
 * @author houxiurong
 * @date 2019-09-13
 */
public class LockSupportDemo {

   /* //当前线程未获取到许可证 线程被阻塞
    public static void main(String[] args) {
        System.out.println("start park!");
        LockSupport.park();

        System.out.println("滴滴滴滴。。。");
        LockSupport.unpark(Thread.currentThread());
        System.out.println("end park!");
    }*/

   /* public static void main(String[] args) {
        System.out.println("start park!");

        //当前线程获取许可证
        LockSupport.unpark(Thread.currentThread());

        //再次调用park方法,不阻塞线下
        LockSupport.park();
        System.out.println("滴滴。。。");

        System.out.println("end park!");
    }*/

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("child thread start park!");

            while (!Thread.currentThread().isInterrupted()) {
                //调用park方法,挂起线程
                LockSupport.park();
            }
            System.out.println("child thread unpark!");
        });

        //启动子线程
        thread.start();

        //让主线程sleep 1秒
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main thread start unpark!!!");

        //调用unpark方法让线程持有许可证,然后使得子线程park
        //LockSupport.unpark(thread);
        thread.interrupt();
    }


}
