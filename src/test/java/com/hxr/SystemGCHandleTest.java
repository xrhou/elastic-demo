package com.hxr;

/**
 * @author houxiurong
 * @date 2019-09-14
 */
public class SystemGCHandleTest {

    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();


    }

}
