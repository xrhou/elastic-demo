package com.hxr.executespooldeeping.threaddemo;

/**
 * 相比继承接口要好，java是单继承
 *
 * @author houxiurong
 * @date 2019-10-06
 */
public class RunnableTicket implements Runnable {

    private volatile int tickerNum = 10;

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (tickerNum > 0) {
                System.out.println("我在" + Thread.currentThread().getName() + "买到了第" + tickerNum-- + "张票");
            }
        }
    }
}
