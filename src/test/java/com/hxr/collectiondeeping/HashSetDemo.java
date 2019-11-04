package com.hxr.collectiondeeping;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author houxiurong
 * @date 2019-09-04
 */
public class HashSetDemo {

    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println(hashSet.size());

        HashMap<Integer, String> hashMap = new HashMap<>();

        System.out.println(Collections.frequency(Lists.newArrayList(1, 2, 3, 4, 1, 2, 4), 3));
    }
}
