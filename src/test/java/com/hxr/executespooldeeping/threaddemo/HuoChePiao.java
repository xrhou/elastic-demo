package com.hxr.executespooldeeping.threaddemo;

/**
 * @author houxiurong
 * @date 2019-10-06
 */
public class HuoChePiao extends Thread {

    public HuoChePiao(String name) {
        super(name);
    }

    private int tickerNum = 10;

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (tickerNum > 0) {
                System.out.println("我在" + this.getName() + "买到了第" + tickerNum-- + "张票");
            }
        }
    }
}
