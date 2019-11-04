package com.hxr.jmmdeeping;

/**
 * JMM内存模型的可见性
 *
 * @author houxiurong
 * @date 2019-09-24
 */
public class VolatileVisualTest {

    private static volatile boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        //thread 0
        new Thread(() -> {
            System.out.println("等待数据...");
            while (!initFlag) {
            }
            System.out.println("数据加载success...");
        }).start();

        Thread.sleep(200);

        //thread 1
        new Thread(() -> {
            preparedInitData();
        }).start();

    }

    public static void preparedInitData() {
        System.out.println("数据开始加载...");
        initFlag = true;
        System.out.println("数据结束加载...");
    }
}
