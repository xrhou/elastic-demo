package com.hxr.jitcompile;

import java.util.ArrayList;

/**
 * 栈内存溢出 jvisualVM 查询 各个分代增涨情况
 *
 * @author houxiurong
 * @date 2019-09-24
 */
public class HeapTest {

    public static void main(String[] args) throws InterruptedException {
        //1Mb
        byte[] name = new byte[1024 * 100];
        ArrayList<byte[]> nameList = new ArrayList<>();
        int i = 0;
        while (true) {
            nameList.add(name);
            Thread.sleep(10);
            System.out.println("i=" + (i++));
        }
    }
}
