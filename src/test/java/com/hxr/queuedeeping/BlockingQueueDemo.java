package com.hxr.queuedeeping;

import java.util.List;

/**
 * 阻塞队列：
 * ArrayBlockingQueue:是一个基于数组的有界阻塞队列,此队列按照FIFO(先进先出)原则对元素进行排队
 * LinkedBlockingQueue:是一个基于链表结构的阻塞队列,此队列按照FIFO(先进先出)排序元素,吞吐量要高于ArrayBlockingQueue
 * SynchronousQueue:是一个存储元素的阻塞队列。每个插入操作必须等到另外一个线程调用移除操作，否则插入操作一直处于阻塞状态，
 * 吞吐量要高于。
 * <p>
 * 1.队列
 * <p>
 * 2.阻塞队列
 * >2.1阻塞队列好的一方面
 * >2.2不得不阻塞如何管理
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        List list=null;
        System.out.println(Integer.MAX_VALUE);
    }
}
