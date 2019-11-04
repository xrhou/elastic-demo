package com.hxr.zookeeperlock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 如果有一把锁，被多个人竞争，此时就会有多个人排队 第一个拿到锁的人会先执行然后释放锁，
 * 后面的每个人都会去监听排在自己前面的的那个人创建的node上，一旦某个人释放了锁，
 * 排在自己后面的人就回被zookeeper给watcher通知到，一旦被通知后，
 * 此时就获取锁,获取到了锁后就开始执行代码
 *
 * @author houxiurong
 * @date 2019-09-21
 */
public class ZooKeeperDistributedLock implements Lock, Watcher {

    private ZooKeeper zooKeeper;
    private String locksRoot = "/locks";
    private static final String SPLIT = "/";

    /**
     * 竞争的资源
     */
    private String productId;

    /**
     * 等待的前一个锁
     */
    private String waitLock;
    /**
     * 当前锁
     */
    private String currentLock;
    /**
     * 计数器
     */
    private CountDownLatch countDownLatch;
    private int sessionTimeout = 30_000;

    public ZooKeeperDistributedLock(String hostConfig, String productId) {
        this.productId = productId;
        try {
            //连接zk
            zooKeeper = new ZooKeeper(hostConfig, sessionTimeout, this);

            Stat stat = zooKeeper.exists(locksRoot, false);
            if (stat == null) {
                // 如果根节点不存在，则创建根节点
                zooKeeper.create(locksRoot, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException | InterruptedException | KeeperException e1) {
            throw new LockException(e1);
        }
    }

    /**
     * 实践监听器处理
     *
     * @param watchedEvent 实践监听器
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        /*if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            this.countDownLatch.countDown();
        }*/

        if (this.countDownLatch != null) {
            this.countDownLatch.countDown();
        }
    }

    /**
     * 获取分布式锁
     */
    @Override
    public void lock() {
        try {
            if (this.tryLock()) {
                System.out.println(Thread.currentThread().getName() + " " + productId + "获得了锁");
                return;
            } else {
                // 等待锁
                waitForLock(waitLock, sessionTimeout);
            }
        } catch (KeeperException | InterruptedException e) {
            throw new LockException(e);
        }
    }

    @Override
    public void lockInterruptibly() {
        this.lock();
    }

    /**
     * 尝试获取锁
     *
     * @return
     */
    @Override
    public boolean tryLock() {
        try {
            //传入的值是 locks+"/"+productId
            //假设productId=1 代表了一个商品的Id
            // locksRoot=locks 创建的znode为: /locks/1 + 000000 顺序临时节点
            ///locks/100000,/locks/100001,/locks/100002

            // 创建临时有序节点
            currentLock = zooKeeper.create(locksRoot + SPLIT + productId, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(currentLock + " 已经创建");


            //看看刚刚创建的节点是不是最小的节点
            // locks:[100000,100001,100002]
            List<String> locks = zooKeeper.getChildren(locksRoot, false);
            Collections.sort(locks);

            //加锁成功
            if (currentLock.equals(locksRoot + SPLIT + locks.get(0))) {
                //如果是最小节点,则获取到锁了
                return true;
            }

            //如果不是最小节点,找到比自己小1的节点
            int previousLockIndex = -1;
            for (int i = 0; i < locks.size(); i++) {
                if (currentLock.equals(locksRoot + "/" + locks.get(i))) {
                    previousLockIndex = i - 1;
                    break;
                }
            }
            //找到下一个节点设置为等待
            this.waitLock = locks.get(previousLockIndex);
        } catch (KeeperException | InterruptedException e) {
            throw new LockException(e);
        }
        return false;
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) {
        try {
            if (this.tryLock()) {
                return true;
            }
            return waitForLock(waitLock, timeout);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 等待获取锁
     *
     * @param waitNode 等待节点
     * @param waitTime 等待时间
     */
    private boolean waitForLock(String waitNode, long waitTime)
            throws KeeperException, InterruptedException {
        //注册监听器 释放锁后删掉
        Stat stat = zooKeeper.exists(locksRoot + SPLIT + waitNode, true);
        if (stat != null) {
            System.out.println(Thread.currentThread().getName() + "等待锁 " + locksRoot + SPLIT + waitNode);
            this.countDownLatch = new CountDownLatch(1);
            // 计数等待，若等到前一个节点消失，则precess 中进行 countDown，停止等待，获取锁
            this.countDownLatch.await(waitTime, TimeUnit.MICROSECONDS);
            this.countDownLatch = null;
            System.out.println(Thread.currentThread().getName() + " 等到了锁");
        }
        return true;
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        try {
            //删除节点 locks/100000
            //删除节点 locks/100001
            System.out.println("释放锁 " + currentLock);
            zooKeeper.delete(currentLock, -1);
            currentLock = null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
