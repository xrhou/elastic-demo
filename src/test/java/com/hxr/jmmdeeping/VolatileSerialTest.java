package com.hxr.jmmdeeping;

import java.util.HashMap;
import java.util.HashSet;

/**
 * JMM的内存有序性
 *
 * @author houxiurong
 * @date 2019-10-04
 */
public class VolatileSerialTest {

    //内存重排序,添加volatile关键字保证 内存屏障 的功能，防止指令重排序
    static volatile int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        HashSet<String> resultSet = new HashSet<>();
        HashMap<String, Integer> resultMap = new HashMap<>();

        for (int i = 0; i < 1000; i++) {
            x = 0;
            y = 0;
            resultMap.clear();
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    int a = y;
                    x = 1;
                    resultMap.put("a", a);
                }
            });

            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    int b = x;
                    y = 1;
                    resultMap.put("b", b);
                }
            });

            one.start();
            two.start();
            one.join();
            two.join();

            resultSet.add("a=" + resultMap.get("a") + ",  " + "b=" + resultMap.get("b"));
            System.out.println(resultSet);
        }
    }
}
