package com.hxr.syncdeeping;

/**
 * 可重入锁的粒度测试，递归调用自己的方法
 * 同一个方法锁是可重入的
 *
 * @author houxiurong
 * @date 2019-08-26
 */
public class SynchronizedRecursion10 {

    public static void main(String[] args) {
        SynchronizedRecursion10 synchronizedRecursion10 = new SynchronizedRecursion10();
        synchronizedRecursion10.method();
    }

    int a = 0;

    private synchronized void method() {
        System.out.println("这是method1,a = " + a);
        if (a == 0) {
            a++;
            method();
        }
    }

}
