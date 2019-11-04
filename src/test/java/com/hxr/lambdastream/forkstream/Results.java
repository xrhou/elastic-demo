package com.hxr.lambdastream.forkstream;

/**
 * 返回结果
 *
 * @author houxiurong
 * @date 2019-10-03
 */
public interface Results {

    /**
     * 返回结果
     *
     * @param key fork-key
     * @param <R> result
     * @return
     */
    <R> R get(Object key);
}