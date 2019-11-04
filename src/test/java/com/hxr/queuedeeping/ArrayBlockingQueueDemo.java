package com.hxr.queuedeeping;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有界数组[大小]方式实现的阻塞队列
 * -- offer 不阻塞向队列尾部插入一个元素,如果一个队列长度已满, 则忽略要插入的元素返回false,元素为null-返回NPE
 * -- put 队尾插入一个元素,队列已满则, 阻塞当前线程 直到队列有空闲插入成功后返回,终断后返回InterruptedException
 * <p>
 * -- take 队头删除一个元素,队列为空则 阻塞当前线程 直到队列有元素后返回元素,终断后返回InterruptedException
 * -- size 是安全精确的,因为使用是加了独占锁
 *
 * @author houxiurong
 * @date 2019-10-20
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        for (int i = 1; i <= 11; i++) {
            arrayBlockingQueue.offer("java" + i);
        }

        String poll = arrayBlockingQueue.poll(1, TimeUnit.SECONDS);
        System.out.println("pool=" + poll);
        System.out.println(arrayBlockingQueue);
        String take = arrayBlockingQueue.take();
        System.out.println(take);

        boolean elasticSearch = arrayBlockingQueue.offer("elasticSearch");
        System.out.println(elasticSearch);
        System.out.println("----" + arrayBlockingQueue);
    }
}
