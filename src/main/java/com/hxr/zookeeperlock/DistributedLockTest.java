package com.hxr.zookeeperlock;

import java.util.concurrent.TimeUnit;

/**
 * DistributedLockTest
 *
 * @author houxiurong
 */
public class DistributedLockTest {
    static int threadNum = 500;

    /**
     * 业务代码
     */
    public static void bizHandleProcess(String orderId) throws InterruptedException {
        System.out.println("复杂计算任务开始");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(--threadNum);
        System.out.println("复杂计算任务结束");
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            ZooKeeperDistributedLock lock = null;
            try {
                lock = new ZooKeeperDistributedLock("127.0.0.1:2181", "test1");
                lock.lock();
                bizHandleProcess("mylock");
                System.out.println(Thread.currentThread().getName() + "正在运行");
            } catch (InterruptedException e) {
                throw new LockException(e);
            } finally {
                if (lock != null) {
                    lock.unlock();
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}