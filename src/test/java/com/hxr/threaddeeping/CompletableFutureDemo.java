package com.hxr.threaddeeping;

/**
 * 调用多个其它的系统的接口:
 * 如 商品详情页面这种需要从多个系统中查数据显示的接口
 * 用CompletableFuture合适，不然其他接口实现调用不如顺序同步执行时间效果好
 *
 * @author houxiurong
 * @date 2019-09-22
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {

    }


}
