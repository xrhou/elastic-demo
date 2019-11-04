package com.hxr.zookeeperlock;

/**
 * @author houxiurong
 * @date 2019-09-21
 */
public class LockException extends RuntimeException {

    public LockException(String e) {
        super(e);
    }

    public LockException(Exception e) {
        super(e);
    }
}
