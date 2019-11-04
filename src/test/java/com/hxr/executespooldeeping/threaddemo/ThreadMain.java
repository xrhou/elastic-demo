package com.hxr.executespooldeeping.threaddemo;

/**
 * @author houxiurong
 * @date 2019-10-06
 */
public class ThreadMain {

    public static void main0(String[] args) throws InterruptedException {
        Thread.currentThread().setName("the main--");
        Thread.sleep(1000);

        ThreadTest threadTest = new ThreadTest();
        threadTest.setName("ThreadTest");
        threadTest.setPriority(1);
        threadTest.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + i + ",优先级=" + Thread.currentThread().getPriority());
        }
    }

    public static void main1(String[] args) {
        HuoChePiao ticket1 = new HuoChePiao("窗口1");
        ticket1.start();

        HuoChePiao ticket2 = new HuoChePiao("窗口2");
        ticket2.start();

        HuoChePiao ticket3 = new HuoChePiao("窗口3");
        ticket3.start();
    }

    public static void main2(String[] args) {
        ImplRunnable implRunnable = new ImplRunnable();

        //使用了静态代理模式
        Thread thread = new Thread(implRunnable);
        thread.setName("ThreadImplRunnable");
        thread.start();

        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i);
        }
    }

    public static void main(String[] args) {
        RunnableTicket hcp = new RunnableTicket();
        Thread thread1 = new Thread(hcp, "窗口1");
        thread1.start();
        Thread thread2 = new Thread(hcp, "窗口2");
        thread2.start();
        Thread thread3 = new Thread(hcp, "窗口3");
        thread3.start();
    }
}
