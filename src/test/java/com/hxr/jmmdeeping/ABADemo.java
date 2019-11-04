package com.hxr.jmmdeeping;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题的产生和ABA问题的解决
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        System.out.println("================ABA问题产生======================");
        new Thread(() -> {
            //线程等待1秒
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet(100, 101);
            System.out.println(Thread.currentThread().getName() + "\t 第一次修改后实际值是:" + atomicReference.get());

            atomicReference.compareAndSet(101, 100);
            System.out.println(Thread.currentThread().getName() + "\t 第二次修改后实际值是:" + atomicReference.get());

        }, "t1").start();

        new Thread(() -> {
            //线程等待4秒
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet(100, 2019);
            System.out.println(Thread.currentThread().getName() + "\t当前实际值是:" + atomicReference.get());

        }, "t2").start();

        System.out.println("================解决ABA问题======================");
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号:" + stamp);

            //线程等待1秒
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第2次版本号:" + stampedReference.getStamp());

            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第3次版本号:" + stampedReference.getStamp() + "\t 当前实际值是:" + stampedReference.getReference());

        }, "t3").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号:" + stamp);
            //暂停t4线程4秒等待
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = stampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t修改是否成功:" + result +
                    "\t当前版本号:" + stampedReference.getStamp() + "\t当前实际值是:" + stampedReference.getReference());
        }, "t4").start();

    }
}
