package com.hxr.threaddeeping;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 多线程下原子类ABA问题Demo
 *
 * @author houxiurong
 * @date 2019-09-04
 */
public class ABAQuestionDemo {

    public static void abaReference() {
        System.out.println("=======ABA问题========");
        AtomicReference<String> reference = new AtomicReference<>("A");

        Thread thread1 = new Thread(() -> {
            //获取期望值
            String expect = reference.get();
            //打印期望值
            System.out.println(Thread.currentThread().getName() + "---expect:" + expect);
            try {
                //doOtherSomething
                Thread.sleep(20);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            //打印实际值
            System.out.println(Thread.currentThread().getName() + "---actual:" + reference.get());

            //CAS操作
            Boolean result = reference.compareAndSet("A", "X");
            System.out.println(Thread.currentThread().getName() + "---result:" + result + ",===>final reference :" + reference.get());
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //进行ABA操作
            System.out.print(Thread.currentThread().getName() + "----change: " + reference.get());
            reference.compareAndSet("A", "B");
            System.out.print(" --> B");
            reference.compareAndSet("B", "A");
            System.out.println(" --> A");
        });
        thread2.start();

    }

    private static void abaAtomicStampedReference() {
        //AtomicStampedReference的方法汇总：
        System.out.println("=========AtomicStampedReference的方法汇总：");
        //构造方法：AtomicStampedReference<>(V initialRef, int initialStamp)
        System.out.println("构造方法:AtomicStampedReference<>(V initialRef, int initialStamp)");
        AtomicStampedReference<String> stampedReference = new AtomicStampedReference<>("David", 1);
        System.out.println("获取应用对象值:" + stampedReference.getReference());
        System.out.println("获取对象的版本戳:" + stampedReference.getStamp());

        //set(V newReference, int newStamp):无条件的重设引用和版本戳的值
        stampedReference.set("Joke", 0);
        System.out.println("set(V newReference, int newStamp):无条件的重设引用和版本戳的值---[reference:"
                + stampedReference.getReference() + ",stamp:" + stampedReference.getStamp() + "]");

        stampedReference.attemptStamp("Joke", 11);
        System.out.println("attemptStamp(V expectedReference, int newStamp):如果引用为期望值，则重设版本戳---[reference:"
                + stampedReference.getReference() + ",stamp:" + stampedReference.getStamp() + "]");

        //compareAndSet(V expectedReference,V newReference,int expectedStamp,int newStamp)
        System.out.println("compareAndSet(V expectedReference,V newReference,int expectedStamp,int newStamp):" +
                "\n如果引用为期望值且版本戳正确，则赋新值并修改版本戳:");
        System.out.println("第一次：" + stampedReference.compareAndSet("Joke", "Tom", 11, 12));
        System.out.println("第二次：" + stampedReference.compareAndSet("Tom", "Grey", 11, 12));
        System.out.println("weakCompareAndSet不再赘述");
        //System.out.println("第一次：" + stampedReference.weakCompareAndSet("Joke", "Tom", 11, 12));
        //System.out.println("第二次：" + stampedReference.weakCompareAndSet("Tom", "Grey", 11, 12));

        int[] stampHolder = new int[10];
        String refer = stampedReference.get(stampHolder);
        System.out.println("get(int[] stampHolder):获取引用和版本戳,stampHolder[0]持有版本戳---" +
                "\n[reference=" + refer + ",stamp=" + stampHolder[0] + "].");

    }

    public static void main(String[] args) {
        abaReference();
        //abaAtomicStampedReference();
    }
}
