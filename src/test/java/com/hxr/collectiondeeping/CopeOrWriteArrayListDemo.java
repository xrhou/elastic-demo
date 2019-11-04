package com.hxr.collectiondeeping;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发list的了解
 *
 * @author houxiurong
 * @date 2019-10-20
 */
public class CopeOrWriteArrayListDemo {

    public static void main0(String[] args) {
        CopyOnWriteArrayList<String> writeList = new CopyOnWriteArrayList<>();
        writeList.add("java");
        writeList.add("c");
        //修改对应数组下标位置的元素 如果找不到元素则报错 ArrayIndexOutOfBoundsException
        writeList.set(1, "C++");
        System.out.println(writeList);
        //具有弱一致性-一个线程在remove操作,另一个线程get时主内存中会有不一致的值
        String s = writeList.get(1);
        System.out.println(s);
        System.out.println(writeList);

        //删除对应位置的元素
        String remove = writeList.remove(0);
        System.out.println(remove);
        System.out.println(writeList);
    }

    //CopyOnWriteArrayList 弱一致性行验证
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();
        cowList.add("hello");//0
        cowList.add("world");
        cowList.add("java");
        cowList.add("one");
        cowList.add("a");

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                cowList.set(1, "houxiurong");
                cowList.remove("java");
                cowList.remove("a");
            }
        });

        //保证线程启动前获取主内存中的list
        Iterator<String> iterator = cowList.iterator();

        //启动线程执行修改cowList元素
        threadOne.start();

        //等待子线程执行完毕
        threadOne.join();

        //遍历list
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ",");
        }


    }
}
