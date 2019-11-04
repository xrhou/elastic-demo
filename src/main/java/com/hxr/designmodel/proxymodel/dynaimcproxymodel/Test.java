package com.hxr.designmodel.proxymodel.dynaimcproxymodel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author houxiurong
 * @date 2019-09-16
 */
public class Test {

    public static void main(String[] args) {
        MaotaiJiu maotaiJiu = new MaotaiJiu();
        WuliangyeJiu wuliangyeJiu = new WuliangyeJiu();
        Furongwang furongwang = new Furongwang();

        InvocationHandler proxy1 = new GuitaiA(maotaiJiu);
        InvocationHandler proxy2 = new GuitaiA(wuliangyeJiu);

        SellWine dynamic_1 = (SellWine) Proxy.newProxyInstance(MaotaiJiu.class.getClassLoader(),
                MaotaiJiu.class.getInterfaces(), proxy1);
        SellWine dynamic_2 = (SellWine) Proxy.newProxyInstance(WuliangyeJiu.class.getClassLoader(),
                WuliangyeJiu.class.getInterfaces(), proxy2);

        dynamic_1.mainJiu();
        dynamic_2.mainJiu();

        InvocationHandler proxy3 = new GuitaiA(furongwang);

        SellCigarette dynamic_3 = (SellCigarette) Proxy.newProxyInstance(Furongwang.class.getClassLoader(),
                Furongwang.class.getInterfaces(), proxy3);
        dynamic_3.sell("芙蓉王香烟");

        System.out.println("dynamicProxy1 class name:" + dynamic_1.getClass().getName());
        System.out.println("dynamicProxy2 class name:" + dynamic_2.getClass().getName());
        System.out.println("dynamicProxy3 class name:" + dynamic_3.getClass().getName());
    }
}
