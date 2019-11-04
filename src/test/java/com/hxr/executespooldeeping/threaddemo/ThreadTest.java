package com.hxr.executespooldeeping.threaddemo;

/**
 * 验证线程抢占
 *
 * @author houxiurong
 * @date 2019-10-06
 */
public class ThreadTest extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName() + " " + i + ",优先级=" + this.getPriority());
        }
    }
}
