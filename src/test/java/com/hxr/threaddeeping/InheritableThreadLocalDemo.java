package com.hxr.threaddeeping;

/**
 * 可以继承的线程使用场景:
 * 1.上下文获取用户的登录信息
 * 2.中间件环境下使用的统一的Id追踪整个链路记录
 *
 * @author houxiurong
 * @date 2019-08-31
 */
public class InheritableThreadLocalDemo {

    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("hello world");

        Thread child = new Thread(() -> System.out.println("child:" + threadLocal.get()));
        child.start();
        System.out.println("main:" + threadLocal.get());

    }
}
