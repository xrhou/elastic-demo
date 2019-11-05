package com.hxr.queuedeeping;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 两个独占锁(takeLock,putLock)的有界 单向 链表 实现的 阻塞队列
 * 有界队列最大长度为:(2>>31-1)=21 4748 3647(21亿...)
 * -- offer 队尾插入一个元素,为空则插入,队列满了则丢弃当前元素返回false,插入元素为null则返回NPE
 * -- put 队尾插入一个元素,队列已满则 阻塞当前线程 直到队列有空闲插入成功后返回, 终断后返回 InterruptedException
 * <p>
 * -- poll 队列头部获取并移除元素,队列为空则返回null,此方法是非阻塞
 * -- peek 队列头部获取不移除元素,队列为空则返回null,此方法是非阻塞
 * -- take 队头删除一个元素,队列为空则,阻塞当前线程,直到队列有元素后返回元素,终断后返回InterruptedException
 * -- remove 移除队列里面的一个元素,为空则返回false
 *
 * @author houxiurong
 * @date 2019-10-20
 */
public class LinkedBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.offer("java");
        linkedBlockingQueue.put("ElasticSearch");
        System.out.println(linkedBlockingQueue);

        linkedBlockingQueue.poll();
        System.out.println("poll后:" + linkedBlockingQueue);

        String take = linkedBlockingQueue.take();
        System.out.println("take=" + take);
        System.out.println("take后:" + linkedBlockingQueue);

        linkedBlockingQueue.add("js");//offer
        boolean js = linkedBlockingQueue.remove("js");
        System.out.println("js:" + js);

        boolean contains = linkedBlockingQueue.contains("js");
        System.out.println("contains:" + contains);
        //System.out.println(0x7fffffff);//2 >>31 -1
        //System.out.println(2 << 31 - 1);
    }
}
