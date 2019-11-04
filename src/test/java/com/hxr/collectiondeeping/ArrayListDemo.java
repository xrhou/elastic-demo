package com.hxr.collectiondeeping;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Iterator remove使用
 *
 * @author houxiurong
 * @date 2019-09-04
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        ArrayList all = new ArrayList();
        all.add("a");
        all.add("b");
        all.add("c");

        Iterator<String> iterator = all.iterator();
        while (iterator.hasNext()) {
            //读取当前集合中的数据元素
            String str = iterator.next();
            if ("b".equals(str)) {
                //使用集合对象 all的 remove(param) 方法后，迭代器的结构被破坏了，遍历停止了
                //all.remove(str);

                //使用迭代器 的 remove() 方法后，迭代器删除了当前读取的元素 “b”，
                // 并且继续往下遍历元素，达到了在删除元素时不破坏遍历的目的。
                iterator.remove();
            } else {
                System.out.println(str + " ");
            }
        }
        System.out.println("删除集合中b元素后的集合=" + all);

    }
}
