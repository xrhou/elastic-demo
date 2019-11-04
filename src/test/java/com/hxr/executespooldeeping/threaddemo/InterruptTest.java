package com.hxr.executespooldeeping.threaddemo;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 可以使用while的true功能给替换thread.stop
 *
 * @author houxiurong
 * @date 2019-10-06
 */
public class InterruptTest {

    //线程thread在sleep 1ms 后被中断,循环中线程检查到中断标记后退出循环
    public static void main0(String[] args) {
        Thread thread = new Thread(() -> {
            int i = 1;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("thread->" + Thread.currentThread().getName() + "=" + (i++) + " run");
            }
            System.out.println("thread->" + Thread.currentThread().getName() + "=" + i + " stop");
        });
        thread.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    private static AtomicBoolean running = new AtomicBoolean(true);

    //使用Thread.stop()会出现数据不同步，物理资源没及时关闭等危险，
    // 所以应该不用Thread.stop(),使用上述的方法替代它。
    public static void main(String[] args) {
        try {
            Thread t0 = new Thread(() -> {
                int i = 1;
                while (InterruptTest.running.get()) {
                    System.out.println("thread->" + Thread.currentThread().getName() + "=" + (i++) + " run");
                }
                System.out.println("thread->" + Thread.currentThread().getName() + "=" + (i++) + " stop");
            });

            t0.start();
            Thread.sleep(1000);
            InterruptTest.running.set(false);
        } catch (Throwable t) {
            System.out.println("Caught in main: " + t);
            t.printStackTrace();
        }
    }

}