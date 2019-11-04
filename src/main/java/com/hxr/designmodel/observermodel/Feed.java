package com.hxr.designmodel.observermodel;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现观察者模式
 *
 * @author houxiurong
 * @date 2019-09-13
 */
public class Feed implements Subject {

    /**
     * 观察者列表
     */
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObserver(String twitter) {
        observers.parallelStream().forEach(observer -> observer.notify(twitter));
    }

    public static void main(String[] args) {
        Feed feed = new Feed();

        //lambda方式实现
        feed.registerObserver((String twitter) -> {
            if (twitter != null && twitter.contains("money")) {
                System.out.println("NYTimes\nBig new in NY!\n" + twitter);
                System.out.println("------------------------------");
            }
        });
        //feed.registerObserver(new NYTimes());

        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());
        feed.notifyObserver("The queen said her favourite book is Java 8 in Action");
        feed.notifyObserver("People very like money");

    }
}
