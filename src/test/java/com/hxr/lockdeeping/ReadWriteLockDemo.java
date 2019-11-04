package com.hxr.lockdeeping;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁模拟缓存：
 * 多个线程同时读取一个资源类没有任何问题，所以为了满足并发量，读取和共享资源莺歌可以同时进行。
 * 但是如果有一个线程想去写这个资源，就不应该再有其他线程可以对改资源进行读或者写
 * 小总结:
 * -读-读能共享
 * -读-写不能共享
 * -写-写不能共享
 * <p>
 * 写入是：原子 + 独占,整个过程必须是一个完整的统一体,中间不能被打断中间不能被分割
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //5个线程写
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.putValue(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }

        //5个线程读
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.getValue(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 共享资源类
 */
class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    //读写锁
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void putValue(String key, Object object) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
            //暂停一会
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, object);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成:" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }

    }

    public void getValue(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 获取:" + key);
            //暂停一会
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 获取完成:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }

    }

    /**
     * 清楚缓存
     */
    public void cleanMap() {
        map.clear();
    }

}
