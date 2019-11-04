package com.hxr.autoproxymybatisspring;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理-实例化接口对象
 *
 * @author houxiurong
 * @date 2019-10-18
 */
public class MyProxyInvokeHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("------------------------------------");
        System.out.println("connection db");
        Select annotation = method.getAnnotation(Select.class);
        if (annotation != null) {
            System.out.println("需要执行的sql:" + annotation.value()[0]);
        }

        if (method.getName().equals("toString")) {
            return proxy.getClass().getInterfaces()[0].getName();
        }

        return null;
    }
}
