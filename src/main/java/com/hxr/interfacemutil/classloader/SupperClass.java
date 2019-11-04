package com.hxr.interfacemutil.classloader;

/**
 * SupperClass
 *
 * @author houxiurong
 * @date 2019-09-28
 */
public class SupperClass {

    static {
        System.out.println("SupperClass init");
    }

    public static int value = 123;
}
