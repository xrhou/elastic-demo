package com.hxr.executespooldeeping.threaddemo.threadnotify.sync;

/**
 * 线程间的锁,使用同步代码块-类似StringBuffer内部类
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class Test {


    /***
     *   +--> [信号灯] <---+
     *   |                |
     *   |                |
     * [生产者]         [消费者]
     *  |                  |
     *  |                  |
     *   +-----> [商品] <--+
     */
    public static void main(String[] args) {
        //定义一个商品
        Product product = new Product();

        //生产者
        Producer producer = new Producer(product);

        //消费者
        Consumer consumer = new Consumer(product);

        //这里使用了静态代理设计模式, Thread相当于代理着
        new Thread(producer).start();
        new Thread(consumer).start();
    }

}
