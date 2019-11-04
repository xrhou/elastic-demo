package com.hxr.syncdeeping;

/**
 * 可重入粒度测试：调用类中另外一个方法
 *
 * @author houxiurong
 * @date 2019-08-26
 */
public class SynchronizedOtherMethod11 {
    public synchronized void method1() {
        System.out.println("我是方法method1");
        method2();
    }

    private synchronized void method2() {
        System.out.println("我是方法method2");
    }

    public static void main(String[] args) {
        SynchronizedOtherMethod11 s = new SynchronizedOtherMethod11();
        s.method1();
    }
}
