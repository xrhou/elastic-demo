package com.hxr.queuedeeping;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模型-传统版本
 * 一个初始值为0的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 * <p>
 * 1 线程  操作(方法)  资源类
 * 2 判断  干活       通知
 * 3 防止虚假唤醒机制
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class ProdConsTraditionDemo {

    public static void main(String[] args) {

        ShardData shardData = new ShardData();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shardData.increment();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shardData.decrement();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shardData.increment();
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shardData.decrement();
            }
        }, "DD").start();

    }

}

/**
 * 资源类
 */
class ShardData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 生产者+1
     */
    public void increment() {
        lock.lock();
        try {
            //1.判断 0!=0 -> false
            if (number != 0) { //虚假唤醒2个线程是OK 多个线程就出错if(number != 0)
                //等待，不生产
                condition.await();
            }

            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            //3.通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    /**
     * 消费者-1
     */
    public void decrement() {
        lock.lock();
        try {
            //1.判断 0!=0 -> false
            if (number == 0) {
                //等待，生产
                condition.await();
            }

            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            //3.通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
