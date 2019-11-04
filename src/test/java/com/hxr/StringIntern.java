package com.hxr;

/**
 * @author houxiurong
 * @date 2019-08-25
 */
public class StringIntern {
    public static void main(String[] args) {
        String s1 = new StringBuilder("String").append("Test").toString();
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2.intern() == s2);

        //true
        //false

        //100万次的性能
        System.out.println(cost(1000000));
    }

    public static long cost(int num) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            String.valueOf(i).intern();
        }
        return System.currentTimeMillis() - start;
    }
    //491ms
    //17572 -XX:StringTableSize=1009
    //2040 -XX:StringTableSize=10009
    //400 -XX:StringTableSize=100009
    //342 -XX:StringTableSize=1000009
}
