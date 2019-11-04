package com.hxr.executespooldeeping.threaddemo.account;

/**
 * 测试取钱是否同步过程
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class Test {

    public static void main0(String[] args) {
        AccountThread at = new AccountThread();
        //使用了静态代理模式
        Thread thread0 = new Thread(at, "老板");
        thread0.start();
        Thread thread1 = new Thread(at, "小三");
        thread1.start();
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            main0(args);
        }
    }


}
