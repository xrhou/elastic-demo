package com.hxr.lockdeeping;

import java.util.concurrent.locks.ReentrantLock;


/**
 * 可重入锁(递归锁)
 * 同一个线程外层函数获得锁之后,内层递归函数仍然能够获得该锁的代码.
 * 在同一个线程在外层方法获得锁的时候,在进入内层方法会自动获取锁.
 * <p>
 * 也就是说,线程可以进入任何一个它已经拥有的锁所同步着的代码块。
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class ReentrantLockDemo {

    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread2").start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();

        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");
        t3.start();
        t4.start();
    }
}

class Phone implements Runnable {

    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getId() + "-" + Thread.currentThread().getName() + "\t invoked sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getId() + "-" + Thread.currentThread().getName() + "\t ####invoked sendEmail");
    }


    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        // Exception in thread "t3" Exception in thread "t4" java.lang.IllegalMonitorStateException
        //lock.lock();
        try {
            getMethod();
            setMethod();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    private void getMethod() {
        System.out.println(Thread.currentThread().getId() + "-" + Thread.currentThread().getName() + "\t invoked getMethod");
    }

    private void setMethod() {
        System.out.println(Thread.currentThread().getId() + "-" + Thread.currentThread().getName() + "\t ###invoked setMethod");
    }


}
