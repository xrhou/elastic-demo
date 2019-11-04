package com.hxr.autoproxymybatisspring;

import java.lang.reflect.Proxy;

/**
 * 模拟mybatis的session
 *
 * @author houxiurong
 * @date 2019-10-18
 */
public class MySqlSession {

    /**
     * 动态代理实现接口方法调用
     *
     * @param clazz 接口类
     * @return 返回动态代理接口的实现类
     */
    public static Object queryMapper(Class clazz) {
        Class<?>[] interfaces = new Class[]{clazz};
        return Proxy.newProxyInstance(MySqlSession.class.getClassLoader(),
                interfaces, new MyProxyInvokeHandler());
    }
}
