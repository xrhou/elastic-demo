package com.hxr.threaddeeping;

/**
 * 验证ThreadLocal是不能继承的
 *
 * @author houxiurong
 * @date 2019-08-31
 */
public class ThreadLocalDemo {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("hello world");

        Thread child = new Thread(() -> System.out.println("child:" + threadLocal.get()));
        child.start();

        System.out.println("main:" + threadLocal.get());
    }
}
