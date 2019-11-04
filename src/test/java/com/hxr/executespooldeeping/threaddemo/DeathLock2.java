package com.hxr.executespooldeeping.threaddemo;

/**
 * synchronized死锁 分析
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class DeathLock2 implements Runnable {
    static Object o1 = new Object(), o2 = new Object();
    int flag = 1;

    @Override
    public void run() {
        System.out.println("flag=" + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //锁住o2
                synchronized (o2) {
                    System.out.println("o2");
                }
            }

        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //锁住o1
                synchronized (o1) {
                    System.out.println("flag=" + flag);
                }
            }
        }
    }

    public static void main(String[] args) {
        DeathLock2 deathLock1 = new DeathLock2();
        deathLock1.flag = 1;

        DeathLock2 deathLock2 = new DeathLock2();
        deathLock1.flag = 0;

        Thread t1 = new Thread(deathLock1, "线程1");
        Thread t2 = new Thread(deathLock2, "线程2");
        t1.start();
        t2.start();


    }
}
