package com.hxr.interfacemutil.classloader;

/**
 * 所有引用类的方式都不会被虚拟机初始化,此为被动引用
 *
 * @author houxiurong
 * @date 2019-09-28
 */
public class SubClass extends SupperClass {

    static {
        System.out.println("SubClass init");
    }

}
