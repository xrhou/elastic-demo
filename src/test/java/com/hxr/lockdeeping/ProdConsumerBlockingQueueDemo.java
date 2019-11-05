package com.hxr.lockdeeping;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程阻塞队列实现的生产者消费者模型
 * volatile/CAS/atomicInteger/BlockingQueue/线程交互/原子引用-复习
 * BlockingQueue
 *
 * @author houxiurong
 * @date 2019-11-05
 */
public class ProdConsumerBlockingQueueDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        //生产者线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t生产者线程启动");
            try {
                myResource.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Producer").start();

        //消费者线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t消费者线程启动");
            try {
                myResource.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("10秒钟后获得结束");

        //叫停生产
        myResource.stop();
    }
}

/**
 * 共享资源 通顺-适配-抽象-具体接口
 */
class MyResource {
    private volatile boolean FLAG = true;//默认开启 进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> blockingQueue;

    /**
     * 构造器注入-默认接口的构造器
     *
     * @param blockingQueue 接口
     */
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    /**
     * 生产者
     */
    public void producer() throws InterruptedException {
        String data;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";//++i
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
            }
            //每隔1秒生产个
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t老板叫停生产,表示FLAG=false,生产动作停止");
    }

    /**
     * 消费者
     */
    public void consumer() throws InterruptedException {
        String result;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t超过2秒未取到值,暂停消费");
            }
            System.out.println(Thread.currentThread().getName() + "\t从队列获取数据" + result + "停止消费");
        }
    }

    /**
     * 停用生产消费
     */
    public void stop() {
        this.FLAG = false;
    }
}
