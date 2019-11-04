package com.hxr.threaddeeping;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程不安全的计数
 *
 * @author houxiurong
 * @date 2019-08-31
 */
public class ThreadNotSafeCount {

    private Long value;//此处可以使用
    AtomicLong v;
    Object o;


    public Long getCount() {
        return value;
    }

    public void increment(Long value) {
        ++value;
    }
}
