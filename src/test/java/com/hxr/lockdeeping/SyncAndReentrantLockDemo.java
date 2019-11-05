package com.hxr.lockdeeping;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 和 Lock的区别:
 * 一、原始构成:
 * 1.synchronized是java的关键字属于JVM层面
 * monitorenter(底层是monitor对象来完成,
 * 其实waite/notify等方法都依赖monitor对象只有在同步块或方法中才能调用wait/notify等方法
 * monitorexit(有2个是为了程序不管异常都退出-释放锁)
 * 2.Lock是一个具体的类(java.util.concurrent.locks.Lock)
 * 二、使用方法:
 * synchronized不需要手动去释放锁，当synchronized代码块执行完成后系统会自动让线程释放对锁的占用
 * ReentrantLock需要用户手动去释放锁，若没有手动释放锁就回导致出现死锁的现象
 * 需要lock和unlock方法成对配合try/finally语句块完成。
 * 三、等待是否可中断
 * synchronized 不可中断，除非程序抛出异常或者正常运行结束
 * ReentrantLock 可以中断：
 * 1.设置超时方法 tryLock(Long timeout,TimeUnit unit)
 * 2.lockInterrupt()放代码块中，调用interrupt()方法可以中断
 * 四、加锁是否公平
 * synchronized 默认是非公平锁
 * ReentrantLock 默认也是非公平锁，但是可以构造方法中传boolean可以设置true公平，false非公平
 * 五、锁是否可以绑定多个Condition条件
 * synchronized 不能绑定多个条件
 * ReentrantLock 用来实现分组唤醒的线程们，可以精确唤醒，而不是像 synchronized 要么随机唤醒一个线程要么全部唤醒所有线程
 *
 * @author houxiurong
 * @date 2019-11-05
 * <p>
 * 题目：
 * 多线程之间要求按照顺序调用，实现 A->B->C三个线程按照顺序启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 接着再
 * AA打印5次，BB打印10次，CC打印15次
 * ---每个线程来10轮
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        }, "CC").start();

        System.out.println("================================");

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                shareResource.print5();
            }, "AA").start();

            new Thread(() -> {
                shareResource.print10();
            }, "BB").start();

            new Thread(() -> {
                shareResource.print15();
            }, "CC").start();
        }
    }
}

/**
 * 共享资源
 */
class ShareResource {
    private int number = 1;//A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //判断 干活 通知三步走
    public void print5() {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                c1.await();
            }
            //A干活5次
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 2;//修改标记位
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //判断 干活 通知三步走
    public void print10() {
        lock.lock();
        try {
            //判断
            while (number != 2) {
                c2.await();
            }
            //B干活10次
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //判断 干活 通知三步走
    public void print15() {
        lock.lock();
        try {
            //判断
            while (number != 3) {
                c3.await();
            }
            //C干活15次
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
