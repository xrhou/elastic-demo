package com.hxr.jmmdeeping;

/**
 * 单例模式的复习
 *
 * @author houxiurong
 * @date 2019-11-01
 */
public class SingletonDemo {

    /**
     * 禁止指令重排
     */
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 初始化构造函数SingletonDemo()");
    }

    /*private static SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }*/

    /**
     * DCL双端检锁机制
     * 如果不加 volatile 不一定线程安全,会存在指令重排存在
     *
     * @return
     */
    private static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        /**
         * 单线程的时候返回是
         * main	 初始化构造函数SingletonDemo()
         * true
         * true
         * true
         */
        /*
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.instance);
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.instance);
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.instance);
        */

        /**
         * 多线程下的使用
         */
        for (int i = 1; i <= 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SingletonDemo.getInstance();
                }
            }, Thread.currentThread().getName() + "-" + i).start();
        }


    }


}
