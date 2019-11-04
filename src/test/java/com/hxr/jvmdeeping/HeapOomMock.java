package com.hxr.jvmdeeping;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 *
 * @author houxiurong
 * @date 2019-09-12
 */
public class HeapOomMock {

    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        boolean flag = true;
        while (flag) {
            try {
                i++;
                //每次增加一个1M大小的数组对象
                list.add(new byte[1024 * 1024]);
            } catch (Throwable e) {
                e.printStackTrace();
                flag = false;
                //记录运行的次数
                System.out.println("count=" + i);
            }
        }
    }
    /**
     * java.lang.OutOfMemoryError: Java heap space
     * 	at com.yibao.canaldemo.jvmdeeping.HeapOomMock.main(HeapOomMock.java:21)
     * count=1745
     * 共1.7G堆内存后内存溢出
     *
     * 如果指定初始化堆内存大小:
     * -Xms16M -Xmx16M -Xmn8M
     * java.lang.OutOfMemoryError: Java heap space
     * 	at com.yibao.canaldemo.jvmdeeping.HeapOomMock.main(HeapOomMock.java:22)
     * count=13
     */
}