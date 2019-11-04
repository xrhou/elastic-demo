package com.hxr.collectiondeeping;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * SimpleDateFormat是线程不安全的,多线程下面会有问题的
 *
 * @author houxiurong
 * @date 2019-09-30
 */
public class SimpleDateFormatTest {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {

        //创建多个线程并启动
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    synchronized (simpleDateFormat) {
                        System.out.println(simpleDateFormat.parse("2019-09-30 21:30:21"));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        /**出现异常:
         * Exception in thread "Thread-3" java.lang.NumberFormatException: For input string: "20192019E"
         * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         * 	at java.lang.Long.parseLong(Long.java:589)
         * 	Mon Sep 30 21:30:21 CST 2019
         * Mon Sep 30 21:30:21 CST 2019
         */
    }


}
