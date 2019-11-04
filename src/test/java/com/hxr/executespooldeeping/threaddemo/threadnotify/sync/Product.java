package com.hxr.executespooldeeping.threaddemo.threadnotify.sync;

/**
 * 商品
 * 模拟线程间的通信
 * 对象的锁 锁池(synchronized) 等待池(wait notify notifyAll)
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class Product {

    private String brand;

    private String name;

    //信号灯的功能 如果有商品 消费者才消费 否则生产者生产
    //默认没有商品 要生产者生成
    private boolean flag = false;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized void setProduct(String brand, String name) {
        //flag==true
        //如果有商品 则生产者线程等待一会
        if (flag == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.setBrand(brand);
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setName(name);

        //生产结束 信号标记为true
        System.out.println("生产者生产了" + this.getBrand() + "----" + this.getName());
        flag = true;
        //有商品了,通知其他线程开始作业
        notify();
    }

    public synchronized void getProduct() {
        //如果没有商品 则消费者线程等待
        //flag==false
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //有商品,开始消费
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费者消费了" + this.getBrand() + "==" + this.getName());

        //消费结束 通知消费者
        flag = false;
        notify();
    }
}
