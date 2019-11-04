package com.hxr.executespooldeeping.threaddemo.threadnotify;

/**
 * 线程间的锁,同步不代码块
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class Test {

    public static void main(String[] args) {
        Product product = new Product();
        ShengChanZhe shengChanZhe = new ShengChanZhe(product);
        new Thread(shengChanZhe).start();

        XiaoFeiZhe xiaoFeiZhe = new XiaoFeiZhe(product);
        new Thread(xiaoFeiZhe).start();
    }

}
