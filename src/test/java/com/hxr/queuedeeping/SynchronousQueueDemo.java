package com.hxr.queuedeeping;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue同步队列 没有容量-同步队列生产一个,消费一个。
 * 与其他BlockingQueue不同,SynchronousQueue是一个不存储元素的BlockingQueue
 * 每一个 put 操作必须要等待一个 take 操作,否则不能继续添加元素,反之亦然
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\tput a");
                blockingQueue.put("a");

                System.out.println(Thread.currentThread().getName() + "\tput b");
                blockingQueue.put("b");

                System.out.println(Thread.currentThread().getName() + "\tput c");
                blockingQueue.put("c");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\tget " + blockingQueue.take());

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\tget " + blockingQueue.take());

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\tget " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
