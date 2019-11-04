package com.hxr.executespooldeeping.threaddemo.threadnotify.sync;

/**
 * 生产者生产商品
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class Producer implements Runnable {

    private Product product;

    public Producer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                product.setProduct("Dove", "Chocolate");
            } else {
                product.setProduct("哈尔滨", "啤酒");
            }
        }
    }
}
