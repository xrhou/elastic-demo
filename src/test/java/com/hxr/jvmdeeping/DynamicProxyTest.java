package com.hxr.jvmdeeping;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理JVM演示
 *
 * @author houxiurong
 * @date 2019-09-15
 */
public class DynamicProxyTest {

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }


    /**
     * 动态代理的实现
     * InvocationHandler
     */
    static class DynamicProxy implements InvocationHandler {

        Object originObj;

        Object bind(Object originObj) {
            this.originObj = originObj;
            return Proxy.newProxyInstance(originObj.getClass().getClassLoader(),
                    originObj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("you are welcome!");
            return method.invoke(originObj, args);
        }
    }

    public static void main(String[] args) {
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }

}
