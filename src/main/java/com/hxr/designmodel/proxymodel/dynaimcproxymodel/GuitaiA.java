package com.hxr.designmodel.proxymodel.dynaimcproxymodel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理
 *
 * @author houxiurong
 */
public class GuitaiA implements InvocationHandler {

    private Object huojia;

    public GuitaiA(Object pingpai) {
        this.huojia = pingpai;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("销售开始 柜台是: " + this.getClass().getSimpleName());
        method.invoke(huojia, args);
        System.out.println("销售结束");
        System.out.println();
        return null;
    }

}