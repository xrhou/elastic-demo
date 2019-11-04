package com.hxr.jvmdeeping;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于javac生成字节码测试
 *
 * @author houxiurong
 * @date 2019-09-17
 */
public class TestClass {

    private final static int m = 123;

    private List<Integer> list = new ArrayList<>();

    public int inc() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }

    class InnerClassMe {
        private double y;

        public int adder() {
            int x;
            try {
                x = 1;
                return x;
            } catch (Exception e) {
                x = 2;
                return x;
            } finally {
                x = 3;
            }
        }
    }


    public static void main(String[] args) {
         String str1="0123456789";
        System.out.println(str1.substring(5));
    }

}
