package com.hxr.designmodel.observermodel;

/**
 * 观察者模式 接口
 *
 * @author houxiurong
 * @date 2019-09-13
 */
public interface Observer {

    /**
     * 通知消息
     *
     * @param twitter 消息体
     */
    void notify(String twitter);
}
