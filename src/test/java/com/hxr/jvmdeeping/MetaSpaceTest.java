package com.hxr.jvmdeeping;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * java 8中元空间大小理解:
 * 使用了Metaspace，也是有OOM的风险的，但是由于Metaspace使用本机内存
 *
 * @author houxiurong
 * @date 2019-09-28
 */
public class MetaSpaceTest {

    public static void main(String[] args) {
        int i = 0;
        try {
            for (; ; ) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMObject.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(objects, args);
                    }
                });
                enhancer.create();
            }
        } catch (Exception e) {
            System.out.println("第" + i + "次发生异常");
        }
    }

    static class OOMObject {

    }
}
