package com.hxr.threaddeeping;

import java.util.LinkedList;
import java.util.Queue;

/**
 * DCL-禁止指令重排
 *
 * @author houxiurong
 * @date 2019-09-06
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance;

    /**
     * 双重检查锁实现单例模式
     *
     * @return SingletonInstance
     */
    public static SingletonDemo getInstance() {
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
        Queue<String> queue = new LinkedList<>();
        queue.offer("hello");
        System.out.println(queue.poll());
        System.out.println(queue.remove());
        System.out.println(queue.size());


    }
}
