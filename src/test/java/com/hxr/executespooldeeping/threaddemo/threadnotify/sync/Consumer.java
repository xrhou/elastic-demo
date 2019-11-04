package com.hxr.executespooldeeping.threaddemo.threadnotify.sync;

/**
 * 消费者消费商品
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class Consumer implements Runnable {

    private Product product;

    public Consumer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            product.getProduct();
        }
    }
}
