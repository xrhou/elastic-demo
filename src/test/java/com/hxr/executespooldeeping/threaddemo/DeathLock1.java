package com.hxr.executespooldeeping.threaddemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁 分析
 *
 * @author houxiurong
 * @date 2019-08-25
 */
public class DeathLock1 {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void deathLock() {
        Thread thread1 = new Thread(() -> {
            try {
                lock1.lock();
                TimeUnit.SECONDS.sleep(1);
                lock2.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                lock2.lock();
                TimeUnit.SECONDS.sleep(1);
                lock1.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.setName("myThread1");
        thread1.setName("myThread2");
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        deathLock();
    }

    /**
     * $jps -l
     * 47460 DeathLock
     * $jstack -l 47460
     *"Thread-1" #11 prio=5 os_prio=31 tid=0x00007ff4f1a83800 nid=0x4103 waiting on condition [0x0000700002266000]
     *    java.lang.Thread.State: WAITING (parking)
     * 	at sun.misc.Unsafe.park(Native Method)
     * 	- parking to wait for  <0x0000000795a463d8> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
     * 	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
     * 	at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
     * 	at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
     * 	at com.yibao.canaldemo.DeathLock.lambda$deathLock$1(DeathLock.java:32)
     * 	at com.yibao.canaldemo.DeathLock$$Lambda$2/1023487453.run(Unknown Source)
     * 	at java.lang.Thread.run(Thread.java:748)
     *
     *    Locked ownable synchronizers:
     * 	- <0x0000000795a46408> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
     *
     * "myThread2" #10 prio=5 os_prio=31 tid=0x00007ff4f1a10000 nid=0x3b03 waiting on condition [0x0000700002163000]
     *    java.lang.Thread.State: WAITING (parking)
     * 	at sun.misc.Unsafe.park(Native Method)
     * 	- parking to wait for  <0x0000000795a46408> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
     * 	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
     * 	at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
     * 	at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
     * 	at com.yibao.canaldemo.DeathLock.lambda$deathLock$0(DeathLock.java:22)
     * 	at com.yibao.canaldemo.DeathLock$$Lambda$1/2110121908.run(Unknown Source)
     * 	at java.lang.Thread.run(Thread.java:748)
     *
     *    Locked ownable synchronizers:
     * 	- <0x0000000795a463d8> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
     *
     *
     * Found one Java-level deadlock:
     * =============================
     * "Thread-1":
     *   waiting for ownable synchronizer 0x0000000795a463d8, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
     *   which is held by "myThread2"
     * "myThread2":
     *   waiting for ownable synchronizer 0x0000000795a46408, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
     *   which is held by "Thread-1"
     *
     * Java stack information for the threads listed above:
     * ===================================================
     * "Thread-1":
     * 	at sun.misc.Unsafe.park(Native Method)
     * 	- parking to wait for  <0x0000000795a463d8> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
     * 	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
     * 	at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
     * 	at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
     * 	at com.yibao.canaldemo.DeathLock.lambda$deathLock$1(DeathLock.java:32)
     * 	at com.yibao.canaldemo.DeathLock$$Lambda$2/1023487453.run(Unknown Source)
     * 	at java.lang.Thread.run(Thread.java:748)
     * "myThread2":
     * 	at sun.misc.Unsafe.park(Native Method)
     * 	- parking to wait for  <0x0000000795a46408> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
     * 	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
     * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
     * 	at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
     * 	at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
     * 	at com.yibao.canaldemo.DeathLock.lambda$deathLock$0(DeathLock.java:22)
     * 	at com.yibao.canaldemo.DeathLock$$Lambda$1/2110121908.run(Unknown Source)
     * 	at java.lang.Thread.run(Thread.java:748)
     *
     * Found 1 deadlock.
     *
     * 从mythread2中可以看出当前线程持有的[0x0000000795a463d8]锁,等待[0x0000000795a46408]
     */

}
