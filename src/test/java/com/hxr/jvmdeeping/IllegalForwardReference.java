package com.hxr.jvmdeeping;

/**
 * 非法向前引用
 *
 * @author houxiurong
 * @date 2019-09-13
 */
public class IllegalForwardReference {

    static {
        i = 0;
        //非法向前引用
        //System.out.println(i);
    }

    static int i = 1;
}
