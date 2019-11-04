package com.hxr.executespooldeeping.threaddemo;

/**
 * 但如果把两句注释的语句加上，一开始lock被t0持有，
 * 一秒后t0使用了stop结束线程，这个时候t0将会释放lock，所以t1持有了lock，此时，t0继续尝试操作，就会抛出ThreadDeath，结果如下
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class StopThread {

    public static void main(String[] args) {
        final Object lock = new Object();
        try {
            Thread t0 = new Thread(() -> {
                try {
                    synchronized (lock) {
                        System.out.println("thread->" + Thread.currentThread().getName() + " acquire lock.");
                        // sleep for 3s
                        Thread.sleep(3000);
                        System.out.println("thread->" + Thread.currentThread().getName() + " release lock.");
                    }
                } catch (Throwable ex) {
                    System.out.println("Caught in run: " + ex);
                    ex.printStackTrace();
                }
            });

            Thread t1 = new Thread(() -> {
                synchronized (lock) {
                    System.out.println("thread->" + Thread.currentThread().getName() + " acquire lock.");
                }
            });

            t0.start();
            Thread.sleep(1000);
            t0.stop();
            t1.start();
            // 但如果把两句注释的语句加上，
            // 一开始lock被t0持有，一秒后t0使用了stop结束线程，
            // 这个时候t0将会释放lock，所以t1持有了lock，
            // 此时，t0继续尝试操作，就会抛出ThreadDeath，结果如下
        } catch (Throwable e) {
            System.out.println("Caught in main: " + e);
            e.printStackTrace();
        }
    }

}
