package com.hxr.queuedeeping;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先级的 无界 阻塞队列
 * 底层有个 平衡二叉树 来存储队头具有优先级的元素,直接遍历不保证队列元素顺序
 * -- offer 队列中插入一个元素,无界的直接返回true,底层是自动tryGrow自动扩容插入元素是平衡二叉树存储
 * -- put 底层调用的offer不阻塞
 * <p>
 * -- poll 队列内部根节点的元素,队列为空不阻塞返回null
 * -- peek 直接返回队列第一个元素 queue[0]
 * -- take 队列内部堆树根节点的元素,队列为 阻塞 等待其他元素的插入
 * <p>
 * -- remove 移除队列里面的一个元素,为空-1则返回false,removeAt(i)
 * -- size 加锁保证线程安全的 size前面加锁了 多线程下size是线程安全的
 *
 * @author houxiurong
 * @date 2019-10-20
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                priorityBlockingQueue.offer(i);
                priorityBlockingQueue.put(i);
                priorityBlockingQueue.peek();
            }
        }
        priorityBlockingQueue.offer(1);
        System.out.println(priorityBlockingQueue);

        Integer poll1 = priorityBlockingQueue.poll();
        System.out.println("poll1=" + poll1);

        Integer poll2 = priorityBlockingQueue.poll();
        System.out.println("poll2=" + poll2);

        boolean remove = priorityBlockingQueue.remove(1);

        ArrayList<Integer> list = Lists.newArrayList();
        priorityBlockingQueue.drainTo(list);
        System.out.println(list);

        //priorityBlockingQueue.offer(100);

    }
}
