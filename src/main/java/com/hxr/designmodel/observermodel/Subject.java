package com.hxr.designmodel.observermodel;

/**
 * 订阅主题
 *
 * @author houxiurong
 * @date 2019-09-13
 */
public interface Subject {

    /**
     * 注册观察者
     *
     * @param observer 观察者
     */
    void registerObserver(Observer observer);

    /**
     * 通知消息
     *
     * @param twitter 消息
     */
    void notifyObserver(String twitter);
}
