package com.hxr.jmmdeeping;

/**
 * 验证 volatile的可见性 原子性
 *
 * @author houxiurong
 * @date 2019-11-01
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myData.add60();
            }
        }, "AAA").start();


        while (myData.number == 0) {

        }

        System.out.println("========================");

    }
}

class MyData {
    int number = 0;

    public void add60() {
        this.number = 60;
    }
}
