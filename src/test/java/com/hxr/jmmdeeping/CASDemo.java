package com.hxr.jmmdeeping;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS(compare and swap) 比较交互
 * 线程的私有内存值和内存中的期望值相等,则修改为要更新的值;
 * 如果不一样,不修改返回false,说明内存中的值已经被别的线程修改了;
 *
 * @author houxiurong
 * @date 2019-11-01
 */
public class CASDemo {

    public static void main0(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current data:" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t current data:" + atomicInteger.get());

        int getAndIncrement = atomicInteger.getAndIncrement();//默认先获取后+1 -> i++

        AtomicInteger atomicInteger2 = new AtomicInteger(5);
        int addAndGet = atomicInteger2.addAndGet(1);//先加后获取 -> ++i

        System.out.println("getAndIncrement=" + getAndIncrement);
        System.out.println("addAndGet=" + addAndGet);
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println(new Random().nextInt(77778888));
                    }
                }
            }, String.valueOf(i)).start();
        }


    }
}
