package com.hxr.threaddeeping;

/**
 * 线程join的理解
 *
 * @author houxiurong
 * @date 2019-09-05
 */
public class ThreadJoinDemo {

    /**
     * 首先join() 是一个synchronized方法，里面调用了wait()，
     * 这个过程的目的是让持有这个同步锁的线程进入等待，那么谁持有了这个同步锁呢？
     * 答案是主线程，因为主线程调用了threadA.join()方法，相当于在threadA.join()代码这块写了一个同步代码块，
     * 谁去执行了这段代码呢，是主线程，所以主线程被wait()了。
     * 然后在子线程threadA执行完毕之后，JVM会调用lock.notify_all(thread);
     * 唤醒持有threadA这个对象锁的线程，也就是主线程，会继续执行。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("MainThread run start.");

        //启动一个子线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA run start.");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("threadA run finished.");
            }
        });
        threadA.start();

        System.out.println("MainThread join before");
        try {
            //调用join()
            threadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MainThread run finished.");
    }
    /**
     * MainThread run start.
     * threadA run start.
     * MainThread join before
     * MainThread run finished.
     * 不加join 输出顺序
     * threadA run finished.
     */

    /**
     * 加join的输出顺序
     * MainThread run start.
     * MainThread join before
     * threadA run start.
     * threadA run finished.
     * MainThread run finished.
     */
}
