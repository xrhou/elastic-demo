package com.hxr;

/**
 * 递归调用-尾巴递归(尾-递法)
 *
 * @author houxiurong
 * @date 2019-10-02
 */
public class TailRecursiveMethod {
    /**
     * 递归调用-尾巴递归(尾-递法)
     *
     * @param n 阶乘位数
     * @return 1*2*3...*n
     */
    static long factorTailRecursive(long n) {
        return factorHelper(1, n);
    }

    private static long factorHelper(long acc, long n) {
        return n == 1 ? acc : factorHelper(acc * n, n - 1);
    }

    public static void main(String[] args) {
        System.out.println(factorTailRecursive(5));
        System.out.println(factorRecursive(5));
    }

    /**
     * 迭代求法
     *
     * @param n 阶乘数
     * @return
     */
    static long factorIterative(long n) {
        long r = 1;
        for (int i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    static long factorRecursive(long n) {
        return n == 1 ? 1 : n * factorIterative(n - 1);
    }


}
