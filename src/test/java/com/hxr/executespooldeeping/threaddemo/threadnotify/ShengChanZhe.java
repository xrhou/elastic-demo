package com.hxr.executespooldeeping.threaddemo.threadnotify;

/**
 * 生产者生成商品
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class ShengChanZhe implements Runnable {

    private Product product;

    public ShengChanZhe(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            synchronized (product) {
                if (i % 2 == 0) {
                    product.setBrand("Dove");
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    product.setName("Chocolate");
                } else {
                    product.setBrand("哈尔滨");
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    product.setName("啤酒");
                }
                System.out.println(i + ",生产者生产了" + product.getBrand() + "----" + product.getName());
            }
        }
    }
}
