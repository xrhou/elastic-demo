package com.hxr.jmmdeeping;

/**
 * JMM内存模型的原子性
 * @author houxiurong
 * @date 2019-09-24
 */
public class VolatileAtomicTest {

    private static volatile int num = 0;

    private static void increase() {
        num++;//num=num+1
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }

        //10个线程全部执行完后再执行主线程
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(num);
    }
}
