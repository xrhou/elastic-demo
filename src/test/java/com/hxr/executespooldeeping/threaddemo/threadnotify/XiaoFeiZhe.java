package com.hxr.executespooldeeping.threaddemo.threadnotify;

/**
 * 消费者生成商品
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class XiaoFeiZhe implements Runnable {

    private Product product;

    public XiaoFeiZhe(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            synchronized (product) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i + ",消费者消费了" + product.getBrand() + "==" + product.getName());
            }
        }
    }
}
