package com.hxr.collectiondeeping;

import com.alibaba.fastjson.JSON;

import java.util.Vector;

/**
 * vector demo
 *
 * @author houxiurong
 * @date 2019-09-04
 */
public class VectorDemo {

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        System.out.println(JSON.toJSONString(vector));

    }
}
