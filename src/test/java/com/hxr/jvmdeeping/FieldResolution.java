package com.hxr.jvmdeeping;

/**
 * 类加载机制的-字段解析
 *
 * @author houxiurong
 * @date 2019-09-13
 */
public class FieldResolution {

    interface Inteface0 {
        int A = 0;
    }

    interface Inteface1 extends Inteface0 {
        int A = 1;
    }

    interface Inteface2 {
        int A = 2;
    }

    static class Patent implements Inteface1 {
        public static int A = 3;
    }

    static class Sub extends Patent implements Inteface2 {
        public static int A = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }

}
