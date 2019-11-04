package com.hxr.queuedeeping;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 单向链表CAS实现的无界非阻塞队列
 * 安全的方法有:
 * -- offer 队尾添加元素-同add
 * -- poll 对头删除一个元素,是彻底的删除
 * -- peek 对头查看一个元素,不从队列中删除元素,仅仅是head节点指向队列里面的第一个元素或者null
 * <p>
 * 不安全的方法:
 * -- size 是线程不安全的,size操作的时候有其他的线程offer原生或poll元素有可能取值不精确
 * -- remove 是线程不安全的,如果存在多个元素则删除第一个返回true,否则false
 * -- contain 是线程不安全的,判断队列里面是否包含对应的元素返回true,否则false
 *
 * @author houxiurong
 * @date 2019-10-20
 */
public class ConcurrentLinkQueueDemo {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
        concurrentLinkedDeque.offer("java");
        concurrentLinkedDeque.offer("mysql");
        concurrentLinkedDeque.offer("html");

        String pop = concurrentLinkedDeque.pop();//弹出一个队头元素并从队列中移除
        boolean add = concurrentLinkedDeque.add("elasticsearch");
        System.out.println("add=" + add);
        String poll = concurrentLinkedDeque.poll();//弹出一个队头元素并从队列中移除
        String peek = concurrentLinkedDeque.peek();//弹出一个队头元素不会从队列中移除
        int size = concurrentLinkedDeque.size();

        boolean html = concurrentLinkedDeque.remove("html");
        System.out.println(concurrentLinkedDeque + "," + pop + "," + poll + "," + peek + "," + size);
        System.out.println(concurrentLinkedDeque + "," + html);
        concurrentLinkedDeque.offer("html");//队尾添加一个元素和poll底层调用的是同一个方法
        boolean html1 = concurrentLinkedDeque.contains("html");
        System.out.println(html1);
        System.out.println(concurrentLinkedDeque.peek());
    }
}
