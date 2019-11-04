package com.hxr.jitcompile;

/**
 * 测试即时编译的过程
 *
 * @author houxiurong
 * @date 2019-09-19
 */
public class CompileTest {

    public static final int NUM = 100;

    public static int doubleValue(int i) {
        for (int j = 0; j < 100_000; j++) {
        }
        return i * 2;
    }

    public static long calcSum() {
        long sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            calcSum();
        }

        System.out.println(31 << 2 - 1);

    }
}
