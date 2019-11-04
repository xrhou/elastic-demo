package com.hxr.executespooldeeping.threaddemo;

/**
 * 实现Runnable接口
 *
 * @author houxiurong
 * @date 2019-10-06
 */
public class ImplRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + "---wowoowowwo-" + i);
        }
    }
}
