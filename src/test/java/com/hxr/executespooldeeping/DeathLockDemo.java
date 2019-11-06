package com.hxr.executespooldeeping;

import java.util.concurrent.TimeUnit;

/**
 * 死锁:
 * 两个及以上线程在执行任务过程中争夺资源而互相等待的现象，
 * 如果无外界的干涉那么他们是无法推进下去的
 * 死锁的解决方法:
 * jps -l 定位
 * 查看后台jvm情况
 * jstack pid  |grep 线程id  -A60多少行
 *
 * @author houxiurong
 * @date 2019-11-06
 */
public class DeathLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "threadAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "threadBB").start();
    }
}

/**
 * 持有死锁的线程模拟
 */
class HoldLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t自己持有锁" + lockA + "\t尝试获取锁" + lockB);
            //暂停一会
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有锁" + lockB + "\t尝试获取锁" + lockA);
            }
        }
    }


}
