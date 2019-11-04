package com.hxr.collectiondeeping;

import com.alibaba.fastjson.JSON;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author houxiurong
 * @date 2019-09-05
 */
public class HashMapTreeMapDemo {

    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "a");
        hashMap.put(3, "c");
        hashMap.put(2, "b");
        System.out.println(JSON.toJSONString(hashMap));

        TreeMap<Integer, String> treeMap = new TreeMap<>(Comparator.reverseOrder());
        treeMap.put(1, "a");
        treeMap.put(3, "c");
        treeMap.put(2, "b");
        System.out.println(JSON.toJSONString(treeMap));


    }
}
