package com.hxr.designmodel.proxymodel.dynaimcproxymodel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理-类的增强
 *
 * @author houxiurong
 * @date 2019-09-16
 */
@Slf4j
public class CglibDynamicProxy implements MethodInterceptor {

    /**
     * 提供对应的增强操作类
     */
    private Enhancer enhancer = new Enhancer();

    /**
     * 类增强
     *
     * @param klass
     * @return
     */
    public SellCigarette getProxyObject(Class klass) {
        // 设置所要增强的类的父类
        enhancer.setSuperclass(klass);
        // 设置回调对象
        enhancer.setCallback(this);
        // 创建对应的对象
        return (SellCigarette) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
        log.info("do before....");
        // 这里需要注意，由于methodProxy对象是增强后的Method对象，所以这里需要调用的
        // 是methodProxy父类的方法，也就是所要增强的类的方法，以实现原来的功能
        Object object = methodProxy.invokeSuper(o, objects);
        log.info("do after....");
        return object;
    }

    public static void main(String[] args) {
        SellCigarette sellCigarette = new Furongwang();
        CglibDynamicProxy proxy = new CglibDynamicProxy();
        SellCigarette proxyObject = proxy.getProxyObject(sellCigarette.getClass());
        proxyObject.sell("芙蓉王香烟");


    }
}
