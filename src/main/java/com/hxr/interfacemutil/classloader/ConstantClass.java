package com.hxr.interfacemutil.classloader;

/**
 * 被动引用 常量池引用
 *
 * @author houxiurong
 * @date 2019-09-28
 */
public class ConstantClass {

    static {
        System.out.println("ConstantClass init");
    }

    public static final String HELLO_WORLD = "hello word";

}
