package com.hxr.niodeeping;

import java.util.HashSet;
import java.util.Set;

/**
 * @author houxiurong
 * @date 2019-10-03
 */
public class Test {

    {
        int x = 10;
        System.out.println(x);
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
        System.out.println(set);
    }
}
