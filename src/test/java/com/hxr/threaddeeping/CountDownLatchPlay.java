package com.hxr.threaddeeping;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用CountDownLatch模拟:
 * 斗地主的三个是否都进入房间
 *
 * @author houxiurong
 * @date 2019-09-18
 */
public class CountDownLatchPlay extends Thread {
    private static int count = 1;
    private final int id = count++;
    private CountDownLatch latch;

    public CountDownLatchPlay(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("【玩家" + id + "】已入场");
        //计数器减1
        latch.countDown();
    }

    public static void main(String[] args) {
        //初始化计数器
        CountDownLatch countDownLatch = new CountDownLatch(3);
        System.out.println("牌局开始, 等待玩家入场...");

        //启动玩家线程
        new CountDownLatchPlay(countDownLatch).start();
        new CountDownLatchPlay(countDownLatch).start();
        new CountDownLatchPlay(countDownLatch).start();

        //等待玩家都进入才开始
        try {
            countDownLatch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("玩家已到齐, 开始发牌...");
    }
}
